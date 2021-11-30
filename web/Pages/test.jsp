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
    <link rel="shortcut icon" href="../resource/img/favicon.png" >
</head>
<body>

<p id = 1234>十分抱歉，这个功能来不及完成了。</p>
<p onclick="f()">点击我，回到主页吧。</p>
<script>
    function f() {
        location.href = '../Home.jsp'
    }
</script>
</body>
</html>
