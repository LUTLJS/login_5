package cn.lutljs.servlet;

import cn.lutljs.dao.UserDao;
import cn.lutljs.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else {
            //logged in!
            req.setAttribute("user",user);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }

    }
}
