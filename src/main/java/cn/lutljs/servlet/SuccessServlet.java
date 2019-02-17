package cn.lutljs.servlet;

import cn.lutljs.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
//        User user = (User) req.getAttribute("user");
        ServletContext servletContext = req.getServletContext();
        User user = (User) servletContext.getAttribute("user");
        resp.getWriter().write("Logged in! Welcome "+user.getUsername()+"!"+
                "<br>"+"<a href='download.html'>点我跳转到下载图片页面</a>"
                );
        //显示用户的上次登录时间
        Cookie[] cookies = req.getCookies();
        boolean flag=false;
        if (cookies!=null && cookies.length>0){
            for (Cookie cookie : cookies) {
                if ("lastTime".equals(cookie.getName())){
                    flag=true;
                    //已经登录过
                    //将上次登录时间显示到客户端
                    String value = cookie.getValue();
                    value=URLDecoder.decode(value,"utf-8");
                    resp.getWriter().write("<br>欢迎回来！您上次的登录时间为"+value);
                    //覆盖掉当前的cookie
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format_date = simpleDateFormat.format(date);
                    //处理日期格式中的空格，对format_date进行编码
                    format_date=URLEncoder.encode(format_date,"utf-8");
                    cookie.setValue(format_date);
                    cookie.setMaxAge(60*60*24*30);
                    resp.addCookie(cookie);
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
            resp.addCookie(lastTime);
            resp.getWriter().write("<br>欢迎您首次登录！");
        }
    }
}
