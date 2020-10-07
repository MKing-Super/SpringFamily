<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 2020/9/29
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
</head>
<style>
    #loginByCode{
        display: none;
    }
    table{
        width: 400px;
        height: 300px;
    }
</style>
<script type="text/javascript">
    $(function () {

        //去注册页面
        $("#register").click(function () {
            window.location.href="${pageContext.request.contextPath}/register/register";
        })
        //密码登陆，去主页面
        $("#login").click(function () {
            var mobile_phone = $("#mobile_phone").val();
            var password = $("#password").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/login/login",
                type:"post",
                dataType:"json",
                data:{"mobile_phone":mobile_phone,"password":password},
                success:function (msg) {
                    if (msg){
                        alert("登陆成功~");
                        window.location.href="${pageContext.request.contextPath}/main/main";
                    }else {
                        alert("登陆失败")
                    }
                },
                error:function () {
                    alert("返回数据失败")
                }
            })
        })
        //选择登陆的方式
        $(".sel").click(function () {
            var sel = $(this).val();
            if (sel == "select01"){
                $("#loginByPassword").css("display","block");
                $("#loginByCode").css("display","none");
            }else if (sel == "select02"){
                $("#loginByPassword").css("display","none");
                $("#loginByCode").css("display","block");
            }
        })
        //获取手机验证码
        //获取验证码
        $("#getcode").click(function () {
            var mobile_phone = $("#mobile_phone").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/login/getcode",
                type:"post",
                dataType:"json",
                data:{"mobile_phone":mobile_phone},
                success:function (msg) {
                    alert(msg)
                },
                error:function () {
                    alert("返回数据失败")
                }
            })
        })
        //手机验证码登陆，去主页面
        $("#login2").click(function () {
            var mobile_phone = $("#mobile_phone").val();
            var code = $("#code").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/login/logincode",
                type:"post",
                dataType:"json",
                data:{"mobile_phone":mobile_phone,"code":code},
                success:function (msg) {
                    if (msg){
                        alert("登陆成功~");
                        window.location.href="${pageContext.request.contextPath}/main/main";
                    }else {
                        alert("登陆失败")
                    }
                },
                error:function () {
                    alert("返回数据失败")
                }
            })
        })

    })
</script>
<body style="background-color: #ffeeba">
    <div id="top" style="height: 40px;background-color: #fcf8e3">
        <input type="button" value="用户注册" id="register">
    </div>
    <div id="main" style="margin: 0 auto" >
        <h1 style="text-align: center;font-style: italic;color: #efa2a9">Welcome~</h1>
        <div id="side" style="float: left;margin-left: 100px;margin-top: 100px">
            <img src="${pageContext.request.contextPath}/images/place01.jpg" width="800">
        </div>
        <div id="log" style="float: left">
            ${msg}
            <%--  通过密码登陆  --%>
            <table id="loginByPassword" align="center" style="text-align: center;margin-top: 180px">
                <tr>
                    <th>输入手机号</th>
                    <td><input type="text" name="mobile_phone" id="mobile_phone"></td>
                </tr>
                <tr>
                    <th>输入密码</th>
                    <td><input type="password" name="password" id="password"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" value="登陆" id="login"></td>
                </tr>
            </table>
            <%--  通过手机验证码登陆 --%>
            <table id="loginByCode" style="text-align: center;text-align: center;margin-top: 180px">
                <tr>
                    <th>输入手机号</th>
                    <td><input type="text" name="mobile_phone" id="mobile_phone2"></td>
                </tr>
                <tr>
                    <th><input type="button" value="获取验证码" id="getcode"></th>
                    <td><input type="text" name="code" id="code"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" value="登陆" id="login2"></td>
                </tr>
            </table>

            <input type="radio" name="sel" value="select01" class="sel" checked>用户密码登陆
            <input type="radio" name="sel" value="select02" class="sel">手机验证码登陆
        </div>

    </div>

</body>
</html>
