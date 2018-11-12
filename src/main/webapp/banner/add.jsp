<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center;padding-top: 50px;line-height: 50px">
    <form id="addBannerFrom" method="post" enctype="multipart/form-data">
        标题：<input name="title" class="easyui-textbox" data-options="iconCls:'icon-search'"><br>
        状态：<select name="status" class="easyui-combobox" data-options="width:170">
                <option value="启用">启用</option>
                <option value="禁用">禁用</option>
              </select><br>
        图片：<input name="imagePath" class="easyui-filebox" data-options="buttonText:'请选择图片',accept:'image/jpeg,image/png,image/gif'"><br>
        时间：<input name="date" type="text" name="date" class="easyui-datebox">
    </form>
</div>