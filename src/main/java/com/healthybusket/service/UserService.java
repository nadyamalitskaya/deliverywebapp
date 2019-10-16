package com.healthybusket.service;

import com.healthybusket.domen.Role;
import com.healthybusket.domen.User;
import com.healthybusket.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Такой пользователь не найден. Проверьте введенные данные или создайте новый аккаунт!");
        }
        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.COURIER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));//пароль зашифрован при регистрации пользователя
        userRepo.save(user);
        sendMessage(user);
        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "%s!\n" +
                            "Вітаємо тебе в нашій команді кур'єрів!" +
                            "Підписуйся на наш інстаграмм, щоб першим дізнаватися найцікавіші новини:" +
                            " https://www.instagram.com/etozhenadechka/?hl=ru/ \n",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Код активації", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }
        user.setActivationCode(null);

        userRepo.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public void updateProfile(User user, String password, String phone, String address,
                              String numberofpasport, String email) {
        String userPhone = user.getPhonenumber();
        boolean isPhoneChanged = (phone != null && !phone.equals(userPhone)) || (userPhone != null && !userPhone.equals(phone));
        if (isPhoneChanged) {
            user.setPhonenumber(phone);
        }

        String userAddress = user.getAddress();
        boolean isAddressChanged = (address != null && !address.equals(userAddress)) || (userAddress != null && !userAddress.equals(address));
        if (isAddressChanged) {
            user.setAddress(address);
        }

        String userNumberOfPassport = user.getNumberofpasport();
        boolean isNumberOfPassportChanged = (numberofpasport != null && !numberofpasport.equals(userNumberOfPassport))
                || (userNumberOfPassport != null && !userNumberOfPassport.equals(numberofpasport));
        if (isNumberOfPassportChanged) {
            user.setNumberofpasport(numberofpasport);
        }

        String userEmail = user.getEmail();
        //проверим что имейл изменился
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) || (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged) {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {//проверяем, что установили пароль
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepo.save(user);
        if (isEmailChanged) {
            sendMessage(user);
        }
    }
}
