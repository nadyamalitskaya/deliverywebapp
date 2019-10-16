<#import "parts/common.ftl" as c>
<@c.page>
   <h3>Список користувачів</h3>

    <table class ="table">
        <thead>
        <tr>
            <th scope="col">Логін</th>
            <th scope="col">Роль</th>
            <th scope="col"> Редагувати</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr scope="row">
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">Змінити</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>