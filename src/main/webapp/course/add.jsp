<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center;padding-top: 50px;line-height: 50px">
    <form id="addCourseFrom" method="post">
        功课名：<input name="title" class="easyui-textbox" data-options="iconCls:'icon-search'"><br>
        是否必修：<select name="marking" class="easyui-combobox" data-options="width:170">
                <option value="是">是</option>
                <option value="否">否</option>
              </select><br>
        时间：<input name="date" type="text" name="date" class="easyui-datebox">
    </form>
</div>