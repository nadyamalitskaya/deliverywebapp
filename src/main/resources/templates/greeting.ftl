<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <div class="text-center">
       <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
 <h5>Ласкаво просимо :)</h5>
    <div>Роби доставку за допомогою нашого зручного додатка!</div>
         <#if !user??>  <a href="/login"><br>Поїхали!</a></#if>
        </div>
    </div>
</@c.page>