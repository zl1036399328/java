package com.zl;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zl.utils.JDBCUtils;
import com.zl.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * JDBC相关代码，包含JDBC的简单使用，批处理，数据库连接池C3P0和德鲁伊，Apache的DBUtils使用
 */
public class JDBC {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "haohao");
        preparedStatement.setString(2, "123");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("登陆成功");
        }else {
            System.out.println("登陆失败");
        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();

        // while (resultSet.next()) {
        //     int id = resultSet.getInt(1);
        //     String username = resultSet.getString(2);
        //     String pwd = resultSet.getString(3);
        //     String birthday = resultSet.getString(4);
        //     System.out.println(id + "\t" + username + "\t" + pwd + "\t" + birthday);
        // }
    }

    /**
     * 测试JDBC工具类JDBCUtils
     */
    @Test
    public void testConn() {
        Connection connection = JDBCUtils.getConnection();

        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "haohao1");
            preparedStatement.setString(2, "123");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println("登陆成功");
            }else {
                System.out.println("登陆失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        JDBCUtils.close(resultSet, preparedStatement, connection);
    }

    /**
     * 批处理(支持批处理需要url加上参数：?rewriteBatchedStatements=true)
     */
    @Test
    public void Batch() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into user values (null,?,?,?)");
        long start = System.currentTimeMillis();
        for (int i = 0; i <= 50000; i++) {
            ps.setString(1, "zhanglei");
            ps.setString(2, "zhanglei");
            ps.setString(3, "1996-01-01");
            ps.addBatch();
            if (i % 1000 == 0) {
                ps.executeBatch();
                ps.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("批处理耗时：" + (end - start) + "毫秒");
        JDBCUtils.close(null, ps, connection);
    }

    /**
     * c3p0连接池，使用jdbc配置文件
     */
    @Test
    public void testC3p0_01() throws IOException, PropertyVetoException, SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\jdbc.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        
        comboPooledDataSource.setInitialPoolSize(10);
        comboPooledDataSource.setMaxPoolSize(50);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("c3p0连接mysql 5000次耗时：" + (end - start) + "毫秒");
    }

    /**
     * c3p0连接池，使用c3p0配置文件
     */
    @Test
    public void testC3p0_02() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //如果使用指定名称的配置，参数加上c3p0配置文件中named-config指定的name
        // ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("otherc3p0");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("c3p0连接mysql 5000次耗时：" + (end - start) + "毫秒");
    }

    /**
     * 德鲁伊连接池
     */
    @Test
    public void testDruid() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\druid.properties"));

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Connection connection = dataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("druid连接mysql 500000次耗时：" + (end - start) + "毫秒");
    }

    /**
     * 测试德鲁伊连接池工具类
     */
    @Test
    public void testDruidUtils(){
        Connection connection = null;
        String sql = "SELECT * from user where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString("username");
                String password = rs.getString("password");
                String birthday = rs.getString("birthday");
                System.out.println(id + "\t" + username + "\t" + password + "\t" + birthday);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilsByDruid.close(rs, preparedStatement, connection);
        }
    }

    /**
     * 测试Apache的DBUtils工具类（返回多个对象）
     */
    @Test
    public void testDbutilsList() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "SELECT * from user WHERE id <= ?";
        List<User> list = queryRunner.query(connection, sql, new BeanListHandler<>(User.class), 5);
        for (User user : list) {
            System.out.println(user);
        }
        JDBCUtilsByDruid.close(null, null, connection);
    }

    /**
     * 测试Apache的DBUtils工具类（返回单个对象）
     */
    @Test
    public void testDbutilsSingle() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "SELECT * from user WHERE id = ?";
        User user = queryRunner.query(connection, sql, new BeanHandler<>(User.class), 5);
        System.out.println(user);
        JDBCUtilsByDruid.close(null, null, connection);
    }

    /**
     * 测试Apache的DBUtils工具类（返回单行单列）
     */
    @Test
    public void testDbutilsScalar() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "SELECT username from user WHERE id = ?";
        Object obj = queryRunner.query(connection, sql, new ScalarHandler(), 5);
        System.out.println(obj);
        JDBCUtilsByDruid.close(null, null, connection);
    }

    /**
     * 测试Apache的DBUtils工具类（实现dml）
     */
    @Test
    public void testDML() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        
        // String sql = "update user set username = ? WHERE id = ?";
        // String sql = "insert into user values (null, ?, ?, ?)";
        String sql = "delete from user where id = ?";
        int rowNun = queryRunner.update(connection, sql, 4);
        System.out.println(rowNun > 0 ? "执行成功" : "执行没有影响到表");

        JDBCUtilsByDruid.close(null, null, connection);
    }
}
