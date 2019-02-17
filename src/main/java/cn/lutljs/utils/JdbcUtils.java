package cn.lutljs.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    //1:read config file
    private static DataSource ds=null;
    static {
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            Properties p = new Properties();
            p.load(is);
            ds = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //2:get dataSource
    public static DataSource getDataSource(){
        return ds;
    }
    //3:get Connection
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
