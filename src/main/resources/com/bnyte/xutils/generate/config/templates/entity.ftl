package ${packageName};

import java.io.Serializable;
<#-- 引包 -->
<#compress>
<#if imports?? && imports?size gt 0>
    <#list imports as import>
        import ${import};
    </#list>
</#if>
</#compress>

public class ${className} implements Serializable {

    <#--属性列表-->
    private static final long serialVersionUID = 1L;
<#if tableDataList?? && tableDataList?size gt 0>
    <#list tableDataList as tableData>

    /**
     * ${tableData.columnComment}
     */
    private ${tableData.columnType} ${tableData.columnName};
    </#list>
</#if>

<#--构造器-->
    public ${className} () {
    }

    public ${className} (<#rt>
    <#if tableDataList?? && tableDataList?size gt 0>
        <#list tableDataList as tableData>
            <#if tableData_index == tableDataList?size - 1>
                ${tableData.columnType} ${tableData.columnName}<#t>
            <#else>
                ${tableData.columnType} ${tableData.columnName}, <#t>
            </#if>
        </#list>
    </#if>
    ) {<#lt>
    <#if tableDataList?? && tableDataList?size gt 0>
        <#list tableDataList as tableData>
        this.${tableData.columnName} = ${tableData.columnName};
        </#list>
    </#if>
    }

<#-- 读写方法列表 -->
<#if tableDataList?? && tableDataList?size gt 0>
    <#list tableDataList as tableData>
    public ${tableData.columnType} ${tableData.readName} () {
        return this.${tableData.columnName};
    }

    public void ${tableData.writeName} (${tableData.columnType} ${tableData.columnName}) {
        this.${tableData.columnName} = ${tableData.columnName};
    }

    </#list>
</#if>
    <#-- toString方法 -->
    public String toString () {
        return "${className}{" +
        <#if tableDataList?? && tableDataList?size gt 0>
            <#list tableDataList as tableData>
            <#if tableData_index == tableDataList?size - 1>
                "${tableData.columnName}='" + ${tableData.columnName} + "'" +
                "}";
            </#if>
            <#if tableData_index != tableDataList?size - 1>
                "${tableData.columnName}='" + ${tableData.columnName} + "', " +
            </#if>
            </#list>
        </#if>
    }

}
