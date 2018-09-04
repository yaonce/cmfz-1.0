<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $('#look').form('load', '${pageContext.request.contextPath}/album/queryById?id= ${param.id}');
</script>
<form id="look" method="post">
    <div>
        <label>名字:</label>
        <input class="easyui-textbox" type="text" name="title" readonly/>
    </div>
    <div>
        <label>个数:</label>
        <input class="easyui-textbox" type="text" name="count" readonly/>
    </div>
    <div>
        <label>评分:</label>
        <input class="easyui-textbox" type="text" name="score" readonly/>
    </div>
    <div>
        <label>作者:</label>
        <input class="easyui-textbox" type="text" name="author" readonly/>
    </div>
    <div>
        <label>播音:</label>
        <input class="easyui-textbox" type="text" name="broadCast" readonly/>
    </div>
    <div>
        <label>简介:</label>
        <input class="easyui-textbox" type="text" name="brife" readonly/>
    </div>
    <div>
        <label>发布时间:</label>
        <input class="easyui-textbox" type="text" name="publicDate" readonly/>
    </div>
    <div>
        <label>创建时间:</label>
        <input class="easyui-textbox" type="text" name="createDate" readonly/>
    </div>
    <div>
        <label>状态:</label>
        <input class="easyui-textbox" type="text" name="status" readonly/>
    </div>
    <div>
        <label>封面:</label>
        <img src="${pageContext.request.contextPath}/upload/${param.corverImg}" height="100" width="100">
    </div>
</form>


