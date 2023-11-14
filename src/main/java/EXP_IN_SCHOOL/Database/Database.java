package EXP_IN_SCHOOL.Database;

import java.sql.*;

public class Database {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://192.168.56.104:26000/tpch?ApplicationName=app1";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "omm1";
    static final String PASS = "opengauss123!";

    public static void main(String[] args) {
        System.out.println("java版本号：" + System.getProperty("java.version"));
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            System.out.println("实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;

            /*sql = "insert into nation (n_nationkey ,n_name, n_regionkey) values (25,'South " +
                    "Korea',3)";
            System.out.println("插入结果为： " + stmt.executeUpdate(sql));*/

            //sql = "update nation set n_regionkey= 2 where n_nationkey=25";
            //System.out.println("更新结果为： " + stmt.executeUpdate(sql));

            sql = "delete from nation where n_nationkey=25";
            System.out.println("删除结果为： " + stmt.executeUpdate(sql));
            sql = "select n_nationkey ,n_name, n_regionkey, n_comment from nation";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.print("nationkey  name    regionkey   comment\n");
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int nationkey = rs.getInt("n_nationkey");
                String name = rs.getString("n_name");
                int regionkey = rs.getInt("n_regionkey");
                String comment = rs.getString("n_comment");
                System.out.printf(" %-5d %-15s %-5d %s\n", nationkey, name.trim(), regionkey,
                        comment);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}