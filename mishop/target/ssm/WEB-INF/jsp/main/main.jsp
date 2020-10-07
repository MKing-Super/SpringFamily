<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 2020/9/29
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示界面</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
</head>
<style>
    #head{
        width: 1510px;
        height: 120px;
    }
    #nav{
        width: 1510px;
        height: 40px;
        border: 1px solid yellowgreen;
        background-color: 	#DCDCDC;
    }
    td{
        text-align: center;
    }
    #mid{
        width: 1226px;
        height: 520px;
        text-align: center;
        margin:0 auto;
        background-size: 1226px 520px;
        background-image: url("/images/loop01.jpg");
    }

    #side{
        width: 20%;
        height: 520px;
        border: 1px solid red;
        background: dimgrey;
        /*设置透明度*/
        filter: alpha(opacity=70);
        -moz-opacity: 0.7;
        opacity: 0.7;
    }
    #main{
        width: 100%;
        border: 1px solid red;
    }
    #foot{
        width: 1510px;
        height: 50px;
        background: yellowgreen;
        float: left;
    }
    .product{
        width: 400px;
    }
</style>
<script type="text/javascript">
    //图片轮播
    var int = self.setInterval("clock()",2000);
    var i = 2;
    function clock(){
        document.getElementById("mid").style.backgroundImage="url(\"/images/loop0"+i+".jpg\")";
        i++;
        if (i==6){
            i=1;
        }
    }

    $(function () {
        //显示所有商品的信息
        $.ajax({
            url:"${pageContext.request.contextPath}/products/showallproducts",
            type:"post",
            dataType:"json",
            data:{},
            success:function (msg) {
                ///alert(msg[0].image.image_url)
                for (var i=0;i<msg.length;i++){
                    $("#main").append(

                        "<div height='400px' style='border:1px solid #000;float: left' id='product"+msg[i].product_id+"' class='product'>"+
                        "<img width='60%' src=" + "${pageContext.request.contextPath}" + msg[i].image.image_url + " id='"+msg[i].product_id+"' onclick='buy(this)'>" +"<br/>"+
                            msg[i].product_name+"<br/>" +
                            "￥："+msg[i].price+"<br/>"+
                        "</div>"
                    )
                }
            },
            error:function () {
                alert("返回数据失败")
            }
        })

        //去查看“首页”
        $("#now").click(function () {
            alert("正在建设中...")
        })

        //去查看“分类”
        $("#sort").click(function () {
            alert("正在建设中...")
        })

        //去查看“星球”
        $("#star").click(function () {
            alert("正在建设中...")
        })


        //去查看“购物车”
        $("#shoppingCart").click(function () {
            window.location.href="${pageContext.request.contextPath}/main/shoppingCart";
        })

        //去查看“我的”
        $("#mine").click(function () {
            window.location.href="${pageContext.request.contextPath}/main/mine";
        })

    })

    //点击商品图片，跳转到购买页面
    function buy(m) {
        var id = m.id;
        window.location.href="${pageContext.request.contextPath}/products/tobuy/"+id;
    }
</script>
<body>
<div id="head">
    <img src="${pageContext.request.contextPath}/images/main_top_picture.jpg" width="100%">
</div>
<div id="nav">
    <table align="center" width="80%">
        <tr>
            <td id="now">首页</td>
            <td id="sort">分类</td>
            <td id="star">星球</td>
            <td id="shoppingCart">购物车</td>
            <td id="mine">我的</td>
        </tr>
    </table>
</div>
<div id="mid">
    <div id="side" style="float:left;" align="center">
        <table border="1px" height="100%" style="color: white">
            <tr>
                <td>手机 电话卡</td>
            </tr>
            <tr>
                <td>电视 盒子</td>
            </tr>
            <tr>
                <td>笔记本 显示器</td>
            </tr>
            <tr>
                <td>家电 插线板</td>
            </tr>
            <tr>
                <td>出行 穿戴</td>
            </tr>
            <tr>
                <td>智能 路由器</td>
            </tr>
            <tr>
                <td>电源 配件</td>
            </tr>
            <tr>
                <td>耳机 箱包</td>
            </tr>
        </table>
    </div>
    <div id="main" style="float:left;">

    </div>

</div>
<div id="foot">
    <img src="${pageContext.request.contextPath}/images/footer.jpg" align="center" width="1510px" height="250px">
</div>

</body>
</html>
