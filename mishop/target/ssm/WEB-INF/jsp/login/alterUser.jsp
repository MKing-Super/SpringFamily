<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 2020/9/30
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>补全用户信息</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        //修改信息
        $("#alter").click(function () {
            var username = $("#username").val();
            var password = $("#password").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/register/alterUser",
                type:"post",
                dataType:"json",
                data:{"username":username,"password":password},
                success:function (msg) {
                    if (msg){
                        window.setTimeout(go, 2000);
                        alert(msg+"2秒后跳转到用户登陆页面")
                    }else {
                        alert("失败")
                    }

                },
                error:function () {
                    alert("返回信息失败")
                }
            })
        })

    })


    //去修改客户信息的页面
    function go() {
        window.location.href="${pageContext.request.contextPath}/login/login";
    }
</script>
<body>
    <table>
        <tr>
            <td>用户名</td>
            <td>
                <input type="text" name="username" id="username">
            </td>
        </tr>
        <tr>
            <td>手机号</td>
            <td>${ALTER_USER.mobile_phone}</td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="text" name="password" id="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" value="修改" id="alter"></td>
        </tr>
    </table>
</body>
</html>
