package ${packageName};

<#-- 引包 -->
<#compress>
    <#if imports?? && imports?size gt 0>
        <#list imports as import>
            import ${import};
        </#list>
    </#if>
</#compress>


@Service
public class ${className} implements ${parent} {

}