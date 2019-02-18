<%@ page import="java.util.Map" %>
<%@ page import="cn.lutljs.domain.User" %>
<%@ page import="org.apache.commons.beanutils.BeanUtils" %>
<%@ page import="cn.lutljs.dao.UserDao" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <script>

    </script>
</head>
<body>
<h2>Hello World!</h2>
<%

%>
<form action="/loginServlet" target="_blank" method="post" id="login_from">
    <label for="username">用户名</label><input id="username" name="username">
    <label for="password">密码</label><input id="password" name="password">
    <input type="submit" value="submit">
</form>

</body>
</html>
