<#import "parts/common.ftl" as c>
<@c.page>
<h3>Зміна ролі користувача та його логіну</h3>

    <form action = "/user" method = "post">
        <div >
        <input type = "text" name = "username" class="form-control" value= "${user.username}">
        </div>
        <p></p>
        <#list roles as role>
            <div class="form-check">
              <label > <input type="checkbox"  name = "${role}"
                          ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type = "hidden" value ="${user.id}" name = "id">
        <input type = "hidden" value = "${_csrf.token}" name = "_csrf">
        <p></p>
    <button type = "submit" class="btn btn-primary"> Зберегти </button>
    </form>
</@c.page>