<%@ page import="java.util.Date" %>
<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    request.setAttribute("path", request.getContextPath());
    Date date = new Date();
    pageContext.setAttribute("date", date);

%>
<head>
    <title>持名法州后台管理中心</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    <script type="text/javascript" src="script/jquery.js"></script>
    <script type="text/javascript" src="script/common.js"></script>
    <script type="text/javascript">

        $(function () {
            //点击更换验证码：
            $("#captchaImage").click(function () {//点击更换验证码
                var src = $(this).prop("src");
                $(this).prop("src", src + "?date=${date}");
            });

            //  form 表单提交
            $("#loginForm").bind("submit", function () {
                var username = $("input[name=username]").val();
                var password = $("input[name=password]").val();
                var myCode = $("input[name=myCode]").val();
                $.ajax({
                    type: "post",
                    url: '${path}/admin/login',
                    data: "username=" + username + "&password=" + password + "&myCode=" + myCode,
                    success: function (message) {
                        if (message == true)
                            location.href = "main/main-delete.jsp";
                    }
                })
                return false;
            });
        });
    </script>
</head>
<body>

<div class="login">
    <form id="loginForm" action="${path}/main/main.jsp" method="post">

        <table>
            <tbody>
            <tr>
                <td width="190" rowspan="2" align="center" valign="bottom">
                    <img src="img/header_logo.gif"/>
                </td>
                <th>
                    用户名:
                </th>
                <td>
                    <input type="text" name="username" class="text" value="xxx" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <th>
                    密&nbsp;&nbsp;&nbsp;码:
                </th>
                <td>
                    <input type="password" name="password" class="text" value="xxx" maxlength="20" autocomplete="off"/>
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <th>验证码:</th>
                <td>
                    <input type="text" id="enCode" name="myCode" class="text captcha" maxlength="4" autocomplete="off"/>
                    <img id="captchaImage" class="captchaImage" src="${path}/admin/code" title="点击更换验证码"/>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit"
                                                                                                        class="loginButton"
                                                                                                        value="登录">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>