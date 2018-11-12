<%@page isELIgnored="false" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#findAlbum").treegrid({
            url:"${pageContext.request.contextPath}/album/findByPage",
            idField:'id',
            treeField:'title',
            columns:[[
                {field:'title',title:'标题',width:100},
                {field:'bulk',title:'章节大小',width:100,},
                {field:'downPath',title:'下载路径',width:100},
                {field:'duration',title:'章节时长',width:100},
            ]],
            fitColumns:true,
            striped:true,
            ctrlSelect:true,
            pagination:true,
            toolbar:"#tbAlbum",
        })
    })
    function downloadChapter() {
        var treegrid = $("#findAlbum").treegrid("getSelected");
        if(treegrid==null){
            $.messager.show({title:'提示',msg:"请选择一个音频进行下载！！！"});
        }else if(treegrid.downPath==null){
            $.messager.show({title:'提示',msg:"请选择一个音频进行下载！！！"});
        }else{
            $("#download").attr("href","${pageContext.request.contextPath}/chapter/download?openStyle=attachment&fileName="+treegrid.downPath);
        }
    }
    function addAlbum() {
        $("#addAlbum").dialog({
            href:"${pageContext.request.contextPath}/album/add.jsp",
            draggable:false,
            buttons:[{
                iconCls:'icon-save',
                text:'添加',
                handler:function(){
                    $("#addAlbum").dialog("close");
                    $("#addAlbumFrom").form("submit",{
                        url:"${pageContext.request.contextPath}/album/add",
                        success:function (result) {
                            var parseJSON = $.parseJSON(result);
                            if(parseJSON.success){
                                $.messager.show({title:'提示',msg:parseJSON.success});
                            }else {
                                $.messager.show({title:'提示',msg:parseJSON.error});
                            }
                            $("#addAlbum").dialog("close");
                            $("#findAlbum").treegrid("reload");
                        }
                    });
                }
            },{
                iconCls:'icon-no',
                text:'关闭',
                handler:function(){
                    $("#addAlbum").dialog("close");
                }
            }],
        })
    }
    function addChapter() {
        var treegrid = $("#findAlbum").treegrid("getSelected");
        if(treegrid==null){
            $.messager.show({title:'提示',msg:"请选择一个专辑进行添加！！！"})
        }else if(treegrid.downPath!=null){
            $.messager.show({title:'提示',msg:"请选择专辑进行添加！！！"});
        }else{
            $("#addChapter").dialog({
                href:"${pageContext.request.contextPath}/album/addChapter.jsp?id="+treegrid.id,
                draggable:false,
                buttons:[{
                    iconCls:'icon-save',
                    text:'添加',
                    handler:function(){
                        $("#addChapter").dialog("close");
                        $("#addChapterFrom").form("submit",{
                            url:"${pageContext.request.contextPath}/chapter/add",
                            success:function (result) {
                                var parseJSON = $.parseJSON(result);
                                if(parseJSON.success){
                                    $.messager.show({title:'提示',msg:parseJSON.success});
                                }else {
                                    $.messager.show({title:'提示',msg:parseJSON.error});
                                }
                                $("#addChapter").dialog("close");
                                $("#findAlbum").treegrid("reload");
                            }
                        });
                    }
                },{
                    iconCls:'icon-no',
                    text:'关闭',
                    handler:function(){
                        $("#addChapter").dialog("close");
                    }
                }],
            })
        }

    }
</script>
<table id="findAlbum" class="easyui-treegrid" data-options="fit:true"></table>
<div id="tbAlbum">
    <a href="javascript:;" class="easyui-linkbutton" onclick="asdasd();" data-options="iconCls:'icon-add',plain:false">专辑详情</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="addAlbum();" data-options="iconCls:'icon-add',plain:false">添加专辑</a>
    <a href="javascript:;" class="easyui-linkbutton" onclick="addChapter();" data-options="iconCls:'icon-add',plain:false">添加章节</a>
    <a href="javascript:;" id="download" class="easyui-linkbutton" onclick="downloadChapter();" data-options="iconCls:'icon-add',plain:false">下载音频</a>
</div>
<div id="addAlbum" data-options="title:'专辑添加',width:600,height:400,iconCls:'icon-add'">
</div>
<div id="addChapter" data-options="title:'章节添加',width:600,height:400,iconCls:'icon-add'">
</div>