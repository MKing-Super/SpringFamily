<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 2020/9/30
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
</head>
<script type="text/javascript">

    $(function () {
        //打开页面时，直接进行查询
        $.ajax({
            url:"${pageContext.request.contextPath}/shoppingCart/showproducts",
            type:"post",
            dataType:"json",
            data:{},
            success:function (msg) {
                for (var i=0;i<msg.length;i++){
                    $("#shoppingcart").append(
                        "<tr id='s'"+i+">"+
                            "<td id='product_id_"+msg[i].shoppingcart_id+"'>"+msg[i].product.product_id+"</td>"+
                            "<td>"+msg[i].product.product_name+"</td>"+
                            "<td>"+msg[i].product.price+"</td>"+
                            "<td>"+msg[i].product.description+"</td>"+
                            "<td>"+msg[i].product.selected+"</td>"+
                            "<td>"+msg[i].totalnumber+"</td>"+
                            "<td>"+msg[i].totalprice+"</td>"+
                            "<td><input type='button' id='"+msg[i].shoppingcart_id+"' value='删除' onclick='deletemsg(this)'></td>"+
                        "</tr>"
                    )
                }
            },
            error:function () {
                alert("返回数据失败1")
            }
        })
        
        

    });

    //删除一条信息
    function deletemsg(del) {
        var product_id = $("#"+"product_id_"+del.id).text();
        //alert(product_id);
        $.ajax({
            url:"${pageContext.request.contextPath}/shoppingCart/deletemsg",
            type: "post",
            dataType: "json",
            data: {"shoppingcart_id":del.id,"productid":product_id},
            success:function (msg) {
                if (msg){
                    alert("删除成功");
                    location.reload();
                }else{
                    alert("删除失败")
                }
            },
            error:function () {
                alert("数据返回失败2")
            }
        })
    }
</script>
<body>
    <div>
        <a href="${pageContext.request.contextPath}/main/main">返回首页</a><br/>
        welcome${USER_SESSION.getUsername()}
    </div>
    <div>
        <table id="shoppingcart" border="1px">
            <tr>
                <td>产品ID</td>
                <td>产品名称</td>
                <td>产品价格</td>
                <td>产品描述</td>
                <td>产品状态</td>
                <td>购买数量</td>
                <td>总花费</td>
                <td>操作</td>
            </tr>
        </table>
    </div>

</body>
</html>
