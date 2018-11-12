<%@page isELIgnored="false" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#findGuru").datagrid({
            url:"${pageContext.request.contextPath}/guru/findByPage",
            columns:[[
                {field:'cxf',title:'cxf',checkbox:true},
                {field:'id',title:'Id',width:100},
                {field:'name',title:'法名',width:100},
                {field:'headPic',title:'头像',width:100,formatter: function(value,row,index){
                        var img="<img src='${pageContext.request.contextPath}"+value+"' style='height:100px'>";
                        return img;
                }},
                {field:'status',title:'状态',width:100},
            ]],
            fitColumns:true,
            striped:true,
            ctrlSelect:true,
            pagination:true,
            toolbar:"#tbGuru"
        })
    })
    function remove() {
        var rows = $("#findGuru").datagrid("getSelections");
        if(rows.length<=0){
            $.messager.show({title:"提示",msg:"至少选择一行！！！"});
        }else{
            var id=[];
            for (var i = 0; i <rows.length ; i++) {
                id.push(rows[i].id);
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/guru/delete",
                type:"POST",
                traditional:true,
                data:{ids:id},
                success:function (result) {
                    $.messager.show({title:"提示",msg:"删除成功！！"});
                    $("#findGuru").datagrid("reload");

                },
                error:function () {
                    $.messager.show({title:"提示",msg:"删除失败！！"});
                    $("#findGuru").datagrid("reload");
                }
            })
        }
    }
    function add() {
        $("#addGuru").dialog({
            href:"${pageContext.request.contextPath}/guru/add.jsp",
            draggable:false,
            buttons:[{
                iconCls:'icon-save',
                text:'添加',
                handler:function(){
                    $("#addGuru").dialog("close");
                    $("#addGuruFrom").form("submit",{
                        url:"${pageContext.request.contextPath}/guru/add",
                        success:function (result) {
                            var parseJSON = $.parseJSON(result);
                            if(parseJSON.success){
                                $.messager.show({title:'提示',msg:parseJSON.success});
                            }else {
                                $.messager.show({title:'提示',msg:parseJSON.error});
                            }
                            $("#addGuru").dialog('close');
                            $("#findGuru").datagrid("reload");
                        }
                    });
                }
            },{
                iconCls:'icon-no',
                text:'关闭',
                handler:function(){
                    $("#addGuru").dialog("close");
                }
            }],
        })
    }
</script>
<table id="findGuru" class="easyui-datagrid" data-options="fit:true"></table>
<div id="tbGuru">
    <a href="javascript:;" class="easyui-linkbutton" onclick="add();" data-options="iconCls:'icon-add',plain:false">添加</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="update();" data-options="iconCls:'icon-add',plain:false">修改</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="remove();" data-options="iconCls:'icon-add',plain:false">删除</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="sevn();" data-options="iconCls:'icon-add',plain:false">保存</a>
</div>
<div id="addGuru" data-options="title:'上师添加',width:600,height:400,iconCls:'icon-add'">
</div>