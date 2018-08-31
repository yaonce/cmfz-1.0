<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(function () {
        //功能键
        var toolbar = [{
            text: "增加",
            iconCls: 'icon-add',
            handler: function () {
                $('#dd').dialog({
                    title: "保存",
                    buttons: [{
                        text: '保存',
                        handler: function () {
                            $("#ff").submit();
                        }
                    }, {
                        text: '关闭',
                        handler: function () {
                            $("#dd").dialog("close");
                        }
                    }],
                });
                $('#ff').form({
                    text: '增加',
                    onSubmit: function () {
                        //表单验证
                        var v = $(this).form('validate');
                        return v;
                    },
                    success: function (data) {
                        //成功后执行
                        $("#dd").dialog("close");
                        $("#dg").datagrid("load");
                    }
                });
            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-error',
            handler: function () {
                var row = $('#dg').edatagrid('getSelected');
                if (row == null) {
                    alert("请选择一个");
                } else {
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    // 销毁指定的行
                    $('#dg').edatagrid('destroyRow', [index]);
                }
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                //选中项
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    $("#dg").edatagrid("editRow", index)
                } else {
                    alert("请先选中行");
                }

            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-add',
            handler: function () {
                $("#dg").edatagrid('saveRow');
            }
        }, '-', {
            text: "刷新",
            iconCls: 'icon-add',
            handler: function () {
                $("#dg").edatagrid('reload');
            }
        }]


        //数据展示
        $('#dg').edatagrid({
            url: "${pageContext.request.contextPath}/banner/queryAll",
            updateUrl: "${pageContext.request.contextPath}/banner/update",
            destroyUrl: "${pageContext.request.contextPath}/banner/delete",
            columns: [[
                {
                    field: 'id', title: '编号', width: 100, editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'title', title: '标题', width: 100, editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'imgPath', title: '图片路径', width: 100, align: 'right', editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'description', title: '描述', width: 100, align: 'right', editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'status', title: '状态', width: 100, align: 'right', editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {field: 'createDate', title: '创建时间', width: 100, align: 'right'}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            toolbar: toolbar,
            view: detailview,
            pagination: true,
            pageSize: 3,
            pageList: [3, 5, 7, 9],
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.createDate + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }


        });
    })
</script>

<table id="dg"></table>
<div style="display: none">
    <div id="dd">
        <form action="${pageContext.request.contextPath}/banner/add" method="post" id="ff"
              enctype="multipart/form-data">
            图片标题:<input type="text" name="title" class="easyui-textbox" data-options="width:200" value="标题"><br/>
            图片描述:<input type="text" name="description" class="easyui-textbox" data-options="width:200" value="描述"><br/>
            图片状态:<input type="text" name="status" class="easyui-textbox" data-options="width:200" value="Y"><br/>
            图片上传:<input class="easyui-filebox" style="width:300px" name="img"><br/>
        </form>
    </div>
</div>