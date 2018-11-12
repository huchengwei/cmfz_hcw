<%@page isELIgnored="false" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#findCourse").datagrid({
            url:"${pageContext.request.contextPath}/course/findByPage",
            columns:[[
                {field:'cxf',title:'cxf',checkbox:true},
                {field:'id',title:'Id',width:100},
                {field:'title',title:'功课名',width:100},
                {field:'marking',title:'是否必修',width:100},
                {field:'date',title:'时间',width:100}
            ]],
            fitColumns:true,
            striped:true,
            ctrlSelect:true,
            pagination:true,
            toolbar:"#tbCourse"
        })
    })
    function remove() {
        var rows = $("#findCourse").datagrid("getSelections");
        if(rows.length<=0){
            $.messager.show({title:"提示",msg:"至少选择一行！！！"});
        }else{
            var id=[];
            for (var i = 0; i <rows.length ; i++) {
                id.push(rows[i].id);
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/course/delete",
                type:"POST",
                traditional:true,
                data:{ids:id},
                success:function (result) {
                    $.messager.show({title:"提示",msg:"删除成功！！"});
                    $("#findCourse").datagrid("reload");

                },
                error:function () {
                    $.messager.show({title:"提示",msg:"删除失败！！"});
                    $("#findCourse").datagrid("reload");
                }
            })
        }
    }
    function add() {
        $("#addCourse").dialog({
            href:"${pageContext.request.contextPath}/course/add.jsp",
            draggable:false,
            buttons:[{
                iconCls:'icon-save',
                text:'添加',
                handler:function(){
                    $("#addCourse").dialog("close");
                    $("#addCourseFrom").form("submit",{
                        url:"${pageContext.request.contextPath}/course/add",
                        success:function (result) {
                            var parseJSON = $.parseJSON(result);
                            if(parseJSON.success){
                                $.messager.show({title:'提示',msg:parseJSON.success});
                            }else {
                                $.messager.show({title:'提示',msg:parseJSON.error});
                            }
                            $("#addCourse").dialog('close');
                            $("#findCourse").datagrid("reload");
                        }
                    });
                }
            },{
                iconCls:'icon-no',
                text:'关闭',
                handler:function(){
                    $("#addCourse").dialog("close");
                }
            }],
        })
    }
</script>
<table id="findCourse" class="easyui-datagrid" data-options="fit:true"></table>
<div id="tbCourse">
    <a href="javascript:;" class="easyui-linkbutton" onclick="add();" data-options="iconCls:'icon-add',plain:false">添加</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="update();" data-options="iconCls:'icon-add',plain:false">修改</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="remove();" data-options="iconCls:'icon-add',plain:false">删除</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="sevn();" data-options="iconCls:'icon-add',plain:false">保存</a>
</div>
<div id="addCourse" data-options="title:'功课添加',width:600,height:400,iconCls:'icon-add'">
</div>