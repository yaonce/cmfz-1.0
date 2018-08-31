<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    var $add = $('#addChapter');
    $.ajax({
        url: "${pageContext.request.contextPath}/album/show",
        type: "post",
        dataType: "json",
        success: function (data) {
            $.each(data, function (index, first) {
                $add.form
            })
        }
    })

    $add.form({
        url: '${pageContext.request.contextPath}/chapter/add',
        onSubmit: function () {
            var v = $add.form('validate');
            return v;
        },
    });
</script>
<form id="addChapter" method="post" enctype="multipart/form-data">
    <div>
        <label>名字:</label>
        <input class="easyui-textbox" type="text" name="title" value="名字"/>
    </div>
    <div>
        <label>大小:</label>
        <input class="easyui-textbox" type="text" name="size" value="大小"/>
    </div>
    <div>
        <label>音频:</label>
        <input class="easyui-textbox" type="text" name="audioPath" value="音频"/>
    </div>
    <div>
        <label>时长:</label>
        <input class="easyui-textbox" type="text" name="duration" value="10:00"/>
    </div>
    <div>
        <label>专辑名:</label>
        <input class="easyui-textbox" type="text" name="albumId" value="1"/>
    </div>

</form>

