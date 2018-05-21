<%--
  Created by IntelliJ IDEA.
  User: Marlon Wang
  Date: 2017/1/16
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
hello world!<br>
hello,${user.name} !<br>
hello,${user.age} !
<%--<form action="/world">--%>
    <%----%>
<%--</form>--%>
<script src="http://localhost:8080/jquery-1.11.1.min.js"></script>
<script>
    $.ajax({
        url: "http://localhost:8080/generic/greeting/hi",
        type: 'POST',
        data: {name:"bwang"}
    });
</script>
<%--<button onclick="var a = "aaa"></button>--%>
</body>
</html>
