<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Логін : </label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="Введіть логін"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Пароль: </label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Введите пароль"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Пароль:</label>
                <div class="col-sm-6">
                    <input type="password" name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           placeholder="Повторіть пароль" />
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Телефон: </label>
                <div class="col-sm-6">
                    <input type="number" name="phone" value="<#if user??>${user.phone}</#if>"
                           class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           placeholder="Введіть телефон"/>
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Паспорт: </label>
                <div class="col-sm-6">
                    <input type="text" name="numberofpasport" value="<#if user??>${user.numberofpasport}</#if>"
                           class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           placeholder="Введіть номер паспорта"/>
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Адреса: </label>
                <div class="col-sm-6">
                    <input type="text" name="address" value="<#if user??>${user.address}</#if>"
                           class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           placeholder="Введіть адресу"/>
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Email: </label>
                <div class="col-sm-6">
                    <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="Введіть email"/>
                <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit"> <#if isRegisterForm>Вступити в наші ряди<#else>Авторизуватися</#if> </button>
        <#if !isRegisterForm><a href="/registration"><br>Створити акаунт </a></#if>
    </form>
</#macro>

<#macro logout>

    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Вийти</button>
    </form>

</#macro>