package cn.lutljs.dao;

import cn.lutljs.domain.User;
import cn.lutljs.utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

    private final JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource()) ;

    public User login(User loginUser){
        String sql="select * from user where username = ? and password = ?";
        User user;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), loginUser.getUsername(), loginUser.getPassword());
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
        return user;
    }
}
