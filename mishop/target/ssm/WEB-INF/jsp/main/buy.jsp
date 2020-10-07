<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 2020/10/3
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购买页面</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
</head>
<style>
    #main{
        width: 1226px;
        border: 1px solid red;
        margin:0 auto;
        float: left;
    }
    #side{
        width: 30%;
        border: 1px solid blue;
        float: left;
    }
    #msg{
        width: 69%;
        border: 1px solid black;
        float: left;
    }
</style>
<script type="text/javascript">
    $(function () {
        var product_id = ${BUY_PRODUCT_ID};
        var userid = "${USER_SESSION.userid}";
        //alert(userid)
        //获取商品的基本信息
        $.ajax({
            url:"${pageContext.request.contextPath}/products/findproduct",
            type:"post",
            dataType:"json",
            data:{"product_id":product_id},
            success:function (msg) {
                $("#side").html("<img width='95%' src=" + "${pageContext.request.contextPath}" + msg[0].image.image_url + " id='"+msg[0].product_id+"' onclick='buy(this)'>");
                $("#product_name").html("<h2>"+msg[0].product_name+"</h2>");
                $("#product_price").html("<h2>"+msg[0].price+"</h2>" +
                    "<input type='text' name='price' value='"+msg[0].price+"' style='display: none'>");

            },
            error:function () {
                alert("返回数据失败1")
            }
        })
        //获取商品类型的信息
        $.ajax({
            url:"${pageContext.request.contextPath}/productmodel/productmodel",
            type:"post",
            dataType:"json",
            data:{"product_id":product_id},
            success:function (msg) {

                for (var i=0;i<msg.length;i++){
                    //设置属性名称
                    $("#details").append(
                        "<tr>" +
                            "<td>" +
                                "<h4>"+msg[i].model_name+"</h4>" +
                                "<input type='text' name='userProductModelDatas["+i+"].userid' value='"+userid+"' style='display: none'>"+
                                "<input type='text' name='userProductModelDatas["+i+"].productid' value='"+product_id+"' style='display: none'>"+
                                "<input type='text' name='userProductModelDatas["+i+"].modelid' value='"+msg[i].model_id+"' style='display: none'>"+
                            "</td>" +
                        "</tr>"
                    )
                    for (var j=0;j<msg[i].modelData.length;j++){
                        //设置属性名
                        $("#details").append(
                            "<tr>" +
                                "<td>"+
                                    msg[i].modelData[j].data_value+
                                    "<input type='radio'  name='userProductModelDatas["+i+"].modeldataid' value='"+msg[i].modelData[j].data_id+"'>" +
                                "</td>" +
                            "</tr>"
                        )
                    }

                }

            },
            error:function () {
                alert("返回数据失败2")
            }
        })



    })
</script>
<body>

    <div id="main" >
        <div id="side">

        </div>
        <div id="msg">
            <form action="${pageContext.request.contextPath}/shoppingCart/addshoppingcart" method="get">
                <table id="details">
                    <tr>
                        <td id="product_name"></td>
                    </tr>
                    <tr>
                        <td id="product_price"></td>
                    </tr>
                    <tr>
                        <td id="totalnumver">
                            购买数量<input type="number" name="totalnumber"/>
                        </td>
                    </tr>

                </table>
                <input type="submit" value="加入购物车" id="but">
            </form>

        </div>
    </div>
</body>
</html>
