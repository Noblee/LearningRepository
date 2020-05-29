package rpc.server;

import java.sql.*;

public class Repository {
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/bank?"
            + "user=root&password=mcmjiayou&useUnicode=true&characterEncoding=UTF8";
    Repository()  {
        try {
            // 动态加载mysql驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    UserAccData get(String username){

        try {
            String sql;
            Statement stmt = conn.createStatement();
            // executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            sql = "select * from userdata where username = \""+username+"\";";
            ResultSet rs = stmt.executeQuery(sql);
            UserAccData userAccData = new UserAccData();
            while (rs.next()) {
                if(rs.getString(1)==null){
                    return null;
                }else {
                    userAccData.setUsername(rs.getString(1));
                    userAccData.setPassword(rs.getString(2));
                    userAccData.setDeposit(rs.getDouble(3));
                    return userAccData;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    boolean put(String username, UserAccData userAccData){
        try{
        String sql;
        Statement stmt = conn.createStatement();
        sql = "INSERT INTO bank.userdata (username, password, deposit) VALUES ('"+username+"', '"+userAccData.getPassword()+"', '"+userAccData.getDeposit()+"');";
        int result = stmt.executeUpdate(sql);
            return result != -1;
    }
        catch (SQLException e){
        e.printStackTrace();
    }
        return false;
    }
}
