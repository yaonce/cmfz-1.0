<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    var $add = $('#addAlubm');
    $add.form({
        url: '${pageContext.request.contextPath}/album/add',
        onSubmit: function () {
            var v = $add.form('validate');
            return v;
        },
        success: function (data) {

        }
    });
</script>
<form id="addAlubm" method="post" enctype="multipart/form-data">
    <div>
        <label>名字:</label>
        <input class="easyui-textbox" type="text" name="title" value="专辑名"/>
    </div>
    <div>
        <label>个数:</label>
        <input class="easyui-textbox" type="text" name="count" value="1"/>
    </div>
    <div>
        <label>封面:</label>
        <input class="easyui-filebox" name="imgPath">
    </div>
    <div>
        <label>评分:</label>
        <input class="easyui-textbox" type="text" name="score" value="10.0"/>
    </div>
    <div>
        <label>作者:</label>
        <input class="easyui-textbox" type="text" name="author" value="作者"/>
    </div>
    <div>
        <label>播音:</label>
        <input class="easyui-textbox" type="text" name="broadCast" value="播音"/>
    </div>
    <div>
        <label>简介:</label>
        <input class="easyui-textbox" type="text" name="brife" value="简介"/>
    </div>
    <div>
        <label>发布时间:</label>
        <input class="easyui-datebox" type="text" name="publicDate"/>
    </div>
    <div>
        <label>状态:</label>
        <input class="easyui-textbox" type="text" name="status" value="Y"/>
    </div>
</form>

