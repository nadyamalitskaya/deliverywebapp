<#import "parts/common.ftl" as c>

<@c.page>
    ${message?ifExists}
    <h5>${username}</h5>
    <form  method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Пароль: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Введіть пароль"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Номер телефону: </label>
            <div class="col-sm-6">
                <input type="text" name="phone" class="form-control" placeholder="Введіть номер телефону" value= "${phone!''}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Адреса: </label>
            <div class="col-sm-6">
                <input type="text" name="address" class="form-control" placeholder="Введіть адресу" value= "${address!''}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Паспортні дані: </label>
            <div class="col-sm-6">
                <input type="text" name="numberofpasport" class="form-control"
                       placeholder="Введіть паспортні дані" value="${numberofpasport!''}"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"  />
        <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Email: </label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="Введіть email" value="${email!''}"/>
                </div>
            </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"  />
        <button class="btn btn-primary" type="submit">Зберегти</button>
    </form>
</@c.page>