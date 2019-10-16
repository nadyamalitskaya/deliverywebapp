<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??> <#--для неавторизованного пользователя не существует сессии-->
        <div class="alert alert-danger" role="alert">
            Такой пользователь не найден. Проверьте введенные данные или создайте новый аккаунт!
        </div>
    </#if>
    <#if message??> <#--для неавторизованного пользователя не существует сессии-->
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>
    <@l.login "/login" false/>
</@c.page>