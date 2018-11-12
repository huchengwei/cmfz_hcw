<%@page isELIgnored="false" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#findBanner").datagrid({
            url:"${pageContext.request.contextPath}/banner/findByPage",
            columns:[[
                {field:'cxf',title:'cxf',checkbox:true},
                {field:'id',title:'Id',width:100},
                {field:'title',title:'标题',width:100},
                {field:'status',title:'状态',width:100},
                {field:'imgPath',title:'路径',width:100},
                {field:'date',title:'时间',width:100}
            ]],
            view: detailview,
            detailFormatter: function(index, row){
                return "<table><tr>" +
                    "<td rowspan=2 style='border:0'><img src='${pageContext.request.contextPath}" + row.imgPath + "' style='height:100px;'></td>" +
                    "<td style='border:0'>" +
                    "<p>标题: " + row.title + "</p>" +
                    "<p>时间: " + row.date + "</p>" +
                    "</td>" +
                    "</tr></table>";
            },
            fitColumns:true,
            striped:true,
            ctrlSelect:true,
            pagination:true,
            toolbar:"#tbBanner"
        })
    })
    function remove() {
        var rows = $("#findBanner").datagrid("getSelections");
        if(rows.length<=0){
            $.messager.show({title:"提示",msg:"至少选择一行！！！"});
        }else{
            var id=[];
            for (var i = 0; i <rows.length ; i++) {
                id.push(rows[i].id);
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/banner/delete",
                type:"POST",
                traditional:true,
                data:{ids:id},
                success:function (result) {
                    $.messager.show({title:"提示",msg:"删除成功！！"});
                    $("#findBanner").datagrid("reload");

                },
                error:function () {
                    $.messager.show({title:"提示",msg:"删除失败！！"});
                    $("#findBanner").datagrid("reload");
                }
            })
        }
    }
    function add() {
        $("#addBanner").dialog({
            href:"${pageContext.request.contextPath}/banner/add.jsp",
            draggable:false,
            buttons:[{
                iconCls:'icon-save',
                text:'添加',
                handler:function(){
                    $("#addBanner").dialog("close");
                    $("#addBannerFrom").form("submit",{
                        url:"${pageContext.request.contextPath}/banner/add",
                        success:function (result) {
                            var parseJSON = $.parseJSON(result);
                            if(parseJSON.success){
                                $.messager.show({title:'提示',msg:parseJSON.success});
                            }else {
                                $.messager.show({title:'提示',msg:parseJSON.error});
                            }
                            $("#addBanner").dialog('close');
                            $("#findBanner").datagrid("reload");
                        }
                    });
                }
            },{
                iconCls:'icon-no',
                text:'关闭',
                handler:function(){
                    $("#addBanner").dialog("close");
                }
            }],
        })
    }
</script>
<table id="findBanner" class="easyui-datagrid" data-options="fit:true"></table>
<div id="tbBanner">
    <a href="javascript:;" class="easyui-linkbutton" onclick="add();" data-options="iconCls:'icon-add',plain:false">添加</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="update();" data-options="iconCls:'icon-add',plain:false">修改</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="remove();" data-options="iconCls:'icon-add',plain:false">删除</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="sevn();" data-options="iconCls:'icon-add',plain:false">保存</a>
</div>
<div id="addBanner" data-options="title:'图片添加',width:600,height:400,iconCls:'icon-add'">
</div>