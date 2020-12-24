$(function () {
    $("#sub").click(function () {
        $.ajax({
            url:"/code/generator",
            dataType:"json",
            type:"post",
            data:{
                "userName":$("#userName").val(),
                "password":$("#password").val(),
                "ipAndPort":$("#ipAndPort").val(),
                "databaseName":$("#databaseName").val(),
                "tableName":$("#tableName").val(),
                "packagesName":$("#packagesName").val()
            },
            success:function (data) {
                alert("代码生成成功~");
            },
            error:function (date) {
                alert("AJAX返回数据失败！！！");
            }
        })
    })
})