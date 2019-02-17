package cn.lutljs.test;

import cn.lutljs.dao.UserDao;
import cn.lutljs.domain.User;
import org.junit.Assert;

public class UserDaoTest {

    @org.junit.Test
    public void login() {
        User lisi = new UserDao().login(new User("lisi", "13"));
        Assert.assertNull(lisi);
    }
}