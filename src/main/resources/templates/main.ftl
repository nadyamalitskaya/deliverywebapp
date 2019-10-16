<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}"
                       placeholder="Введіть им'я">
                <button type="submit" class="btn btn-primary ml-2">Знайти замовника</button>
            </form>
        </div>
    </div>

    <#if isAdmin>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Додати нову інформацію
        </a>
        <div class="collapse <#if info??>show</#if>" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                               value="<#if info??>${info.name}</#if>" name="name" placeholder="Им`я"/>
                        <#if nameError??>
                            <div class="invalid-feedback">
                                ${nameError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control ${(addressError??)?string('is-invalid', '')}"
                               value="<#if info??>${info.address}</#if>" name="address" placeholder="Адреса"/>
                        <#if addressError??>
                            <div class="invalid-feedback">
                                ${addressError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control ${(phoneNumberError??)?string('is-invalid', '')}"
                               value="<#if info??>${info.phoneNumber}</#if>" name="phoneNumber" placeholder="Телефон"/>
                        <#if phoneNumberError??>
                            <div class="invalid-feedback">
                                ${phoneNumberError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="time" class="form-control ${(timeError??)?string('is-invalid', '')}"
                               value="<#if info??>${info.time}</#if>" name="time" placeholder="На коли"/>
                        <#if timeError??>
                            <div class="invalid-feedback">
                                ${timeError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <div class="custom-file">
                            <input type="file" name="file" id="customFile">
                            <label class="custom-file-label" for="customFile">Додати зображення</label>
                        </div>
                    </div>

                    <br>
                    <div class="row">
                        <div class="col">
                            <#list foodPacks as foodPack>
                                <span>${foodPack.title}</span>
                                <span class="col">
                                    <input type="number" class="form-control" name="amounts" value="0" min="0"
                                           max="${foodPack.amount}">
                             Кількість в наявності:
                                    ${foodPack.amount} <br>
                                </span>
                                <br>
                            </#list>
                        </div>
                    </div>
                    <br>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Додати</button>
                    </div>
                </form>
            </div>
        </div>
    </#if>
    <div class="card-columns">
    <#list infos as info>
        <div class="card border-dark my-3">
            <#if info.filename??>
                <img src="/img/${info.filename}" height="500" width="500" class="card-img-top">
            </#if>
            <div class="m-2"
            <p>Им'я замовника: ${info.name}<br>
                Адреса: ${info.address}<br>
                Номер телефону: ${info.phoneNumber}<br>
                На який час: ${info.time}<br>
                <#if info.date??>Дата замовлення: ${info.date}<br></#if>
                Продукти: <br>
                <#list info.getDeliveryOrderHasFoodPacks() as pack>
                    <#if pack.amount != 0>
                        ${pack.food_pack.title} - ${pack.amount} </br>
                    </#if>
                </#list>
                <br>
                Загальна ціна: ${info.getTotalCost()}
            </p>
            <#if isAdmin>
                <button type="button" disabled="disabled" class="btn btn-secondary btn-block">Взяти на доставку</button>
            </#if>
            <#if !isAdmin>
                <form method="get" action="/main" name="deliveryId" onsubmit="${info.id}">
                    <input type="hidden" name="deliveryId" value="${info.id}"/>
                    <button type="submit" value="submit" class="btn btn-success btn-block">Взяти на доставку</button>
                </form>
            </#if>

        </div>
        </div>
    <#else>
        No orders
    </#list>

    </div>
</@c.page>