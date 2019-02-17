package cn.lutljs.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieTestServlet")
public class CookieTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取所有的cookie
        Cookie[] cookies = request.getCookies();
        boolean flag=false;
        if (cookies!=null && cookies.length>0){
            //判断是否存在名为lastTime的cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastTime")){
                    flag=true;
                    //不是第一次访问
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = simpleDateFormat.format(date);
                    //对format进程url编码
                    format = URLEncoder.encode(format, "utf-8");
                    String value = cookie.getValue();
                    //对format进行解码
                    value=URLDecoder.decode(value,"utf-8");
                    response.setContentType("text/html;charset=utf8");
                    response.getWriter().write("欢迎回来，您上次的访问时间为："+value);
                    cookie.setValue(format);
                    cookie.setMaxAge(60*60*24*30);
                    //覆盖掉原来的cookie
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        if (!flag){
            //是第一次访问
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(date);
            format=URLEncoder.encode(format,"utf-8");
            Cookie cookie = new Cookie("lastTime",format);
            cookie.setMaxAge(60*60*24*30);
            response.setContentType("text/html;charset=utf8");
            response.addCookie(cookie);
            response.getWriter().write("欢迎您首次登录！");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
