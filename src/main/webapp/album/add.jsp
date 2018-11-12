<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center;padding-top: 50px;line-height: 50px">
    <form id="addAlbumFrom" method="post" enctype="multipart/form-data">
        专辑名：<input name="title" class="easyui-textbox" data-options="iconCls:'icon-search'"><br>
        封面：<input name="pic" class="easyui-filebox" data-options="buttonText:'请选择图片',accept:'image/jpeg,image/png,image/gif'"><br>
        作者：<input name="author" class="easyui-textbox" data-options="iconCls:'icon-search'"><br>
        播音：<input name="broadCast" class="easyui-textbox" data-options="iconCls:'icon-search'"><br>
        时间：<input name="date" type="text" name="date" class="easyui-datebox"><br>
        简介：<input name="brief" class="easyui-textbox" data-options="height:150,multiline:true"><br>
    </form>
</div>