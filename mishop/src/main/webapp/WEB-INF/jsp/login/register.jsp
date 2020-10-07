<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 2020/9/29
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        //注册中
        $("#register").click(function () {
            var mobile_phone = $("#mobile_phone").val();
            var code = $("#code").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/register/register",
                type:"post",
                dataType:"json",
                data:{"mobile_phone":mobile_phone,"code":code},
                success:function (msg) {
                    if (msg){
                        window.setTimeout(go, 2000);
                        alert(msg+"注册成功~2秒后跳转到修改用户信息页面")
                    }else{
                        alert(msg)
                    }
                },
                error:function () {
                    alert("返回数据失败")
                }
            })
        })
        //获取验证码
        $("#getcode").click(function () {
            var mobile_phone = $("#mobile_phone").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/register/code",
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
    })

    //去修改客户信息的页面
    function go() {
        window.location.href="${pageContext.request.contextPath}/register/alterUser";
    }






</script>
<body>
    <form>
        <table>
            <tr>
                <td>请输入手机号</td>
                <td><input type="text" name="mobile_phone" id="mobile_phone"></td>
            </tr>
            <tr>
                <td><input type="button" value="点击此处获取验证码" id="getcode"></td>
                <td><input type="text" name="code" id="code"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="button" value="注册" id="register"></td>
            </tr>
        </table>
    </form>
</body>
</html>
