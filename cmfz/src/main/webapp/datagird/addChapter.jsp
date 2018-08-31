<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    var $add = $('#addChapter');

    $add.form({
        url: '${pageContext.request.contextPath}/chapter/add',
        onSubmit: function () {
            var v = $add.form('validate');
            return v;
        },
        success: function (data) {

            $('#tree').treegrid('load');
        }
    });
</script>
<form id="addChapter" method="post" enctype="multipart/form-data">
    <div>
        <label>名字:</label>
        <input class="easyui-textbox" type="text" name="title" value="名字"/>
    </div>
    <div>
        <label>音频:</label>
        <input class="easyui-filebox" name="audio" style="width:300px">
    </div>
    <input class="easyui-combobox" name="albumId" data-options="
        valueField: 'id',
        textField: 'title',
        url: '${pageContext.request.contextPath}/album/show',"/>

</form>

