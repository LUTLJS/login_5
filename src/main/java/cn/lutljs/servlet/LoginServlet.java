package cn.lutljs.servlet;

import cn.lutljs.dao.UserDao;
import cn.lutljs.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1:setting characterEncoding
        req.setCharacterEncoding("utf-8");
        //get checkcode from request
        String login_checkcode = req.getParameter("verificationCode");
        //get checkcode from session-checkcode
        String checkcode = (String) req.getSession().getAttribute("verificationCode");
        req.getSession().invalidate();
        if (login_checkcode!=null && login_checkcode.length()>0){
            if (login_checkcode.equalsIgnoreCase(checkcode)){
                //2:getting username and password
                Map<String, String[]> map = req.getParameterMap();
                //3:encapsulate username and password into User object
                User loginUser = new User();
                try {
                    BeanUtils.populate(loginUser,map);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                //4:call UserDao.login to get User from DataBase
                User user = new UserDao().login(loginUser);
                //5:check if user is null
                if (user==null){
                    //failed to login
                    resp.setContentType("text/html;charset=utf8");
           /* resp.getWriter().write("<script>alert('登录失败，用户名或密码错误，请重新登录。');" +
                    "location.href=\"http://localhost:8080/index.jsp\";" +
                    "</script>");*/
//                    req.setAttribute("msg","用户名或密码错误，请重新输入");
//                    req.getRequestDispatcher("/index.jsp").forward(req,resp);
//            resp.sendRedirect(req.getContextPath()+"/index.jsp");
            req.getRequestDispatcher("/loginFailed.jsp").forward(req,resp);
                }else {
                    //logged in!
//            req.setAttribute("user",user);
                    ServletContext servletContext = req.getServletContext();
                    servletContext.setAttribute("user",user);
//                    req.setAttribute("msg","用户名或密码错误，请重新输入");
//                    req.getRequestDispatcher("/index.jsp").forward(req,resp);
                    resp.sendRedirect(req.getContextPath()+"/loggedIn.jsp");
//            req.getRequestDispatcher("/loggedIn.jsp").forward(req,resp);
                }
            }else {
                //login_checkcode is not correct
                //redirect to index.jsp
                req.setAttribute("msg","验证码错误，请重新输入");
//                resp.sendRedirect(req.getContextPath()+"/index.jsp");
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("msg","请填写验证码");
//            resp.sendRedirect(req.getContextPath()+"/index.jsp");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }
}
