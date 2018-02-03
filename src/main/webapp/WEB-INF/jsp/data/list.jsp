<%--
  Created by IntelliJ IDEA.
  User: 8102
  Date: 2018/1/4
  Time: 下午 08:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${userList}</h1>
<c:forEach items="${userList}" var="i">
    ${i.username}:${i.password}<br>
</c:forEach>
</body>
</html>
