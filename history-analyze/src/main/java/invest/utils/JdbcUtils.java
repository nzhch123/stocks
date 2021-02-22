package invest.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {



    public static final String url = " jdbc:oracle:thin:@172.18.9.24:1521:ORCL";
    public static final String user = "scott";
    public static final String password = "tiger";

    //static静态代码块，只加载一次
    static{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            throw new IllegalArgumentException("建立数据库连接参数异常");
        }
    }


    //获取数据库链接
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, password);

    }

    //释放资源
    public static void release(Connection conn,PreparedStatement pst,ResultSet rs){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(pst != null){
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
