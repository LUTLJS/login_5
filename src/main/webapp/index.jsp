<%@ page import="java.util.Map" %>
<%@ page import="cn.lutljs.domain.User" %>
<%@ page import="org.apache.commons.beanutils.BeanUtils" %>
<%@ page import="cn.lutljs.dao.UserDao" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <script>
        window.onload=function(){
            document.getElementById("checkcode_img").onclick=function () {
                this.src="checkCodeServlet?time="+new Date().getTime();
            }
        };

    </script>
    <style>
        #msg{
            color: #000000;
            font-size: large;
        }
    </style>
</head>
<body>
<h2>Hello World!</h2>
<%

%>
<span id="msg"><%= (request.getAttribute("msg"))==null? "":request.getAttribute("msg")%></span>
<form action="/loginServlet" target="_blank" method="post" id="login_from">
    <label for="username">用户名</label><input id="username" name="username">
    <label for="password">密码</label><input id="password" name="password">
    <label for="checkcode">验证码</label><input id="checkcode" name="checkcode">
    <img src="/checkCodeServlet" id="checkcode_img">
    <input type="submit" value="submit">
</form>

</body>
</html>
