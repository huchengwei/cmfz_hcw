<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center;padding-top: 50px;line-height: 50px">
    <form id="addGuruFrom" method="post" enctype="multipart/form-data">
        法名：<input name="name" class="easyui-textbox" data-options="iconCls:'icon-search'"><br>
        状态：<select name="status" class="easyui-combobox" data-options="width:170">
                <option value="启用">启用</option>
                <option value="禁用">禁用</option>
              </select><br>
        头像：<input name="pic" class="easyui-filebox" data-options="buttonText:'请选择图片',accept:'image/jpeg,image/png,image/gif'"><br>
    </form>
</div>