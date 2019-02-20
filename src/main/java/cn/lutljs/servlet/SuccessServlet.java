package cn.lutljs.servlet;

import cn.lutljs.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1:setting characterEncoding
        resp.setContentType("text/html;charset=utf8");
        //2:get attribute user
        ServletContext servletContext = req.getServletContext();
        User user = (User) servletContext.getAttribute("user");
        resp.getWriter().write("Logged in! Welcome "+user.getUsername()+"!"+
                "<br>"+"<a href='download.html'>点我跳转到下载图片页面</a>"
                );
        //显示用户的上次登录时间
        Cookie[] cookies = req.getCookies();
        boolean flag=false;
        if (cookies!=null && cookies.length>0){
            //判断是否存在名为lastTime的cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastTime")){
                    flag=true;
                    //不是第一次访问
                    resp.getWriter().write("欢迎回来，您上次的访问时间为："+URLDecoder.decode(cookie.getValue(),
                            "utf-8"));
                    cookie.setValue(getEncodedCurrentTime());
                    cookie.setMaxAge(60*60*24*30);
                    //覆盖掉原来的cookie
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        if (!flag){
            //是第一次访问
            Cookie cookie = new Cookie("lastTime",getEncodedCurrentTime());
            cookie.setMaxAge(60*60*24*30);
            resp.addCookie(cookie);
            resp.getWriter().write("欢迎您首次登录！");
        }
    }
    public static String getEncodedCurrentTime() throws UnsupportedEncodingException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return URLEncoder.encode(simpleDateFormat.format(date),"utf-8");
    }
}
