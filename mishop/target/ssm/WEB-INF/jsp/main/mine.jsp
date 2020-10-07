<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 2020/10/4
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
</head>
<style>
    body{
        margin:0 auto;
    }
    #afteredit{
        display: none;
    }
</style>
<script type="text/javascript">
    $(function () {
        //点击编辑用户
        $("#edituser").click(function () {
            //alert("222")
            $("#beforeedit").hide();
            $("#afteredit").show();

        })
        //保存用户修改的信息
        $("#keep").click(function () {

            var mobile_phone = "${USER_SESSION.mobile_phone}";
            var username = $("#username").val();
            var password = $("#password").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/user/alteruser",
                type: "post",
                dataType: "json",
                data:{"mobile_phone":mobile_phone,"username":username,"password":password},
                success:function (msg) {
                    window.location.reload();
                },
                error:function () {
                    alert("返回数据失败")
                }
            })

        })





        //用户退出
        $("#logout").click(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/login/logout",
                type:"post",
                dataType:"json",
                data:{},
                success:function (msg) {
                    if(msg){
                        alert("已退出")
                        window.location.reload();
                    }
                },
                error:function () {
                    alert("返回数据失败")
                }
            })
        })

    })
</script>
<body>
    <div style="height: 200px">
        <div style="float: left"><h2>welcome~ ${USER_SESSION.username}</h2></div>
        <div style="float:right;"><input type="button" value="退出登陆" id="logout"></div>
    </div>
    <div align="center">
        <table border="1px" id="beforeedit">
            <tr>
                <td>用户ID</td>
                <td>${USER_SESSION.userid}</td>
            </tr>
            <tr>
                <td>用户名称</td>
                <td>${USER_SESSION.username}</td>
            </tr>
            <tr>
                <td>手机号（登陆账号）</td>
                <td>${USER_SESSION.mobile_phone}</td>
            </tr>
            <tr>
                <td>密码</td>
                <td>${USER_SESSION.password}</td>
            </tr>
            <tr>
                <td colspan="2"><input type="button" value="编辑用户信息" id="edituser"></td>
            </tr>
        </table>


        <table border="1px" id="afteredit">
            <tr>
                <td>用户ID</td>
                <td>${USER_SESSION.userid}</td>
            </tr>
            <tr>
                <td>用户名称</td>
                <td><input type="text" name="username" id="username"></td>
            </tr>
            <tr>
                <td>手机号（登陆账号）</td>
                <td>${USER_SESSION.mobile_phone}</td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password" id="password"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="button" value="保存" id="keep"></td>
            </tr>
        </table>
    </div>
    <a href="${pageContext.request.contextPath}/main/main">返回主页面</a>
</body>
</html>
