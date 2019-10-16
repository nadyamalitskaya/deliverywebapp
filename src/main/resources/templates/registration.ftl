<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

    <div class = "mb-1"> <h3>Створення нового акаунта</h3> </div>
    ${message?ifExists}
    <@l.login "/registration" true/>
    <div class = "mt-4"> <h6>На пошту вам прийде вітальний лист та ссилка на нашу соціальну мереєу Instagram!</h6></div>
</@c.page>