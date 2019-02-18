<%@ page import="cn.lutljs.domain.User" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: Jimmy
  Date: 2019/2/18
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<%
    User user = (User) request.getServletContext().getAttribute("user");
%><h1>
    登录成功！欢迎<%= user.getUsername() %>
</h1>
<a href='download.html'>点我跳转到下载图片页面</a>
<%
    //显示用户的上次登录时间
    Cookie[] cookies = request.getCookies();
    %><%! boolean flag=false;%><%
    if (cookies!=null && cookies.length>0){
        for (Cookie cookie : cookies) {
            if ("lastTime".equals(cookie.getName())){
                flag=true;
                //已经登录过
                //将上次登录时间显示到客户端
                %><br>欢迎回来！您上次的登录时间为"<%= URLDecoder.decode(cookie.getValue(),"utf-8") %><%
                //覆盖掉当前的cookie
                String format_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                //处理日期格式中的空格，对format_date进行编码
                cookie.setValue(URLEncoder.encode(format_date,"utf-8"));
                cookie.setMaxAge(60*60*24*30);
                response.addCookie(cookie);
                break;
            }
        }
    }
    if (!flag){
        //没来过，没登录过
        //创建cookie
        String format_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Cookie lastTime = new Cookie("lastTime", URLEncoder.encode(format_date, "utf-8"));
        lastTime.setMaxAge(60*60*24*30);
        response.addCookie(lastTime);
        %><br>欢迎您首次登录!<%
    }
%>
</body>
</html>
