package com.healthybusket.domen;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DeliveryOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Это поле не может быть пустым!")
    protected String name;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Это поле не может быть пустым!")
    private String address;

    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "Это поле не может быть пустым!")
    private String phoneNumber;

    @Column(name = "time", nullable = false)
    @NotBlank(message = "Это поле не может быть пустым!")
    private String time;

    @Column(name = "filename")
    private String filename;

    @Column(name="date", nullable = true)
    private Date date;

    @Column(name = "user_id", nullable = true)
    private Long user_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "order")
    private Set<DeliveryOrderHasFoodPack> deliveryOrderHasFoodPacks = new HashSet<>();

    @Column(name = "status_id")
    private Long status_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private OrderStatus status;

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public void setStatus_id(Status status) {
        this.status_id = status.getStatusId();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Transient
    private ArrayList<Integer> amounts = new ArrayList<>();

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(ArrayList<Integer> amounts) {
        this.amounts = amounts;
    }

    public Iterable<DeliveryOrderHasFoodPack> getDeliveryOrderHasFoodPacks() {
        return deliveryOrderHasFoodPacks;
    }

    public Iterable<String> getFoodPackTitles() {
        ArrayList titleList = new ArrayList();
        deliveryOrderHasFoodPacks.forEach(item -> titleList.add(item.getFood_pack().getTitle() +
                " " + item.getAmount()));
        return titleList;
    }

    public void setDeliveryOrderHasFoodPacks(Set<DeliveryOrderHasFoodPack> deliveryOrderHasFoodPacks) {
        this.deliveryOrderHasFoodPacks = deliveryOrderHasFoodPacks;
    }

    public int getTotalCost() {

        return deliveryOrderHasFoodPacks.stream().mapToInt(item -> (int) (item.getAmount() * item.getFood_pack().getCost())).sum();
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DeliveryOrder() {
    }

    public DeliveryOrder(String name, String address, String phoneNumber, String time) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.time = time;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
