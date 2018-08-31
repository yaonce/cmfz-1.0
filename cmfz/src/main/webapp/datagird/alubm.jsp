<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $('#tree').treegrid({
        url: '${pageContext.request.contextPath}/album/queryAll',
        idField: 'id',
        treeField: 'title',
        columns: [[
            {field: 'title', title: '名字', width: 60},
            {field: 'audioPath', title: '下载路径', width: 60},
            {field: 'duration', title: '章节大小', width: 80},
            {field: 'size', title: '时长', width: 80}
        ]],
        fit: true,
        fitColumns: true,
        pagination: true,
        pageSize: 2,
        pageList: [2, 4, 6, 8, 10],
        toolbar: [{
            text: '专辑详情',
            iconCls: 'icon-edit',
            handler: function () {
                var treegrid = $('#tree').treegrid('getSelected');
                if (typeof treegrid.id == 'number') {
                    $('#details').dialog({
                        title: '专辑详情',
                        width: 400,
                        height: 200,
                        href: '${pageContext.request.contextPath}/datagird/details.jsp?id=' + treegrid.id,
                    });
                } else {
                    $.messager.alert('警告', '请选择专辑');
                }

            }
        }, '-', {
            text: '添加专辑',
            iconCls: 'icon-help',
            handler: function () {
                $('#details').dialog({
                    title: "添加专辑",
                    width: 400,
                    buttons: [{
                        text: '保存',
                        handler: function () {
                            $('#addAlubm').submit();
                            $('#details').dialog('close')
                        }
                    }, {
                        text: '关闭',
                        handler: function () {
                            $('#details').dialog('close')
                        }
                    }],

                    href: "${pageContext.request.contextPath}/datagird/addAlubm.jsp",
                })
            }
        }, '-', {
            text: '添加章节',
            iconCls: 'icon-help',
            handler: function () {
                $('#details').dialog({
                    title: "添加章节",
                    width: 400,
                    buttons: [{
                        text: '保存',
                        handler: function () {
                            $('#addChapter').submit();
                            $('#details').dialog('close')
                        }
                    }, {
                        text: '关闭',
                        handler: function () {
                            $('#details').dialog('close')
                        }
                    }],

                    href: "${pageContext.request.contextPath}/datagird/addChapter.jsp",
                })
            }
        }, '-', {
            text: '下载音频',
            iconCls: 'icon-help',
            handler: function () {
                alert('编辑按钮')
            }
        }]


    });


</script>
<table id="tree"></table>
<div id="details">Dialog Content.</div>

