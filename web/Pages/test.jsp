<%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/10/27
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <script src="../extraLib/jQuery/jquery.js"></script> <%-- jQuery库 --%>
    <script type="text/javascript" src = ../extraLib/layer/layer.js></script>
</head>
<body>

<p id = 1234>如果您点击我，我会消失。</p>
<p onclick="f()">点击我，我会消失。</p>
<p>也要点击我哦。</p>
<script>
    function f() {
        layer.alert("不能为空！\r\n", {
            title: '提示框',
            icon: 0,
        });
    }
</script>
<script>
    if(typeof jQuery == 'undefined'){
        window.alert("没有jquery");
    }
</script>
</body>
</html>
