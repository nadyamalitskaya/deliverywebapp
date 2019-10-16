<#import "parts/common.ftl" as c>
<@c.page>
    <div class="card-columns">
    <#list infos as info>
        <div class="card border-dark my-3">
            <#if info.filename??>
                <img src="/img/${info.filename}" height="500" width="500" class="card-img-top">
            </#if>
            <div class="m-2"
            <p>Им'я замовника: ${info.name}</br>
                Адреса: ${info.address}</br>
                Номер телефону: ${info.phoneNumber}</br>
                На який час: ${info.time}</br>
                Продукти:
                <#list info.getDeliveryOrderHasFoodPacks() as pack>
                    <#if pack.amount != 0>
                        ${pack.food_pack.title} - ${pack.amount} </br>
                    </#if>
                </#list>
                <br>
                Загальна ціна: ${info.getTotalCost()}
                <br>
                Кур'єр: ${info.getUser().getUsername()}
            </p>
            <form action="/orderlist" method="get">
                <button type="submit" name="order" value="${info.getId()}" class="btn btn-dark btn-block">${info.getStatus().getStatus_name()}</button>
            </form>

        </div>
        </div>
    <#else>
        Завершених замовлень немає.
    </#list>

    </div>
</@c.page>