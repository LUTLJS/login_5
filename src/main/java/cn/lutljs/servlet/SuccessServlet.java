package cn.lutljs.servlet;

import cn.lutljs.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    }
}
