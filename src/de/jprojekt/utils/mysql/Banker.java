package de.jprojekt.utils.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Banker {

    public static int createBanker(String uid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "INSERT INTO banker (bankerid) VALUES (?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }
    public static int deleteBanker(String uid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "DELETE FROM banker WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

}
