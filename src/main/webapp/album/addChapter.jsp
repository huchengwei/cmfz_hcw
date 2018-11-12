<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center;padding-top: 50px;line-height: 50px">
    <form id="addChapterFrom" method="post" enctype="multipart/form-data">
        <input type="hidden" name="album.id" value="${param.id}">
        章节名：<input name="title" class="easyui-textbox" data-options="iconCls:'icon-search'"><br>
        章节：<input name="pic" class="easyui-filebox" data-options="buttonText:'请选择文件',accept:'audio/mpeg'"><br>
        时间：<input name="date" type="text" name="date" class="easyui-datebox"><br>
    </form>
</div>