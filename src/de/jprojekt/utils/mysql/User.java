package de.jprojekt.utils.mysql;

import de.jprojekt.utils.Checks;
import de.jprojekt.utils.Krypto;

import java.sql.*;
import java.util.Date;

public class User {
    public static String getPassword(String uid) throws SQLException {
        String query = "SELECT password FROM user WHERE userid=?;";
        return Mysql.getStringFromDB(uid, query);
    }

    public static int setPassword(String uid, String password) throws SQLException{
        String query = "UPDATE user SET password=? WHERE userid=?";
        String hPassword = Krypto.getHash(password, Integer.toString(getSalt(uid)));
        return Mysql.setString(uid, query, hPassword);
    }

    public static String getAddress(String uid) throws SQLException {
        String query = "SELECT address FROM user WHERE userid=?;";
        return Mysql.getStringFromDB(uid, query);
    }

    public static int setAddress(String uid, String address) throws SQLException{
        String query = "UPDATE user SET address=? WHERE userid=?";
        return Mysql.setString(uid, query, address);
    }

    public static int getPlz(String uid) throws SQLException {
        String query = "SELECT plz FROM user WHERE userid=?;";
        return Mysql.getIntFromDB(uid, query);
    }

    public static int setPlz(String uid, int plz) throws SQLException{
        String query = "UPDATE user SET address=? WHERE userid=?";
        return Mysql.setInt(uid, query, plz);
    }

    public static Date getBday(String uid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "SELECT bday FROM user WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            return new Date();
        }else {
            Date bday = new Date();
            while (rs.next()) {
                bday = rs.getDate(1);
            }
            ps.close();
            return bday;
        }
    }

    public static int setBday(String uid, java.sql.Date bday) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "UPDATE user SET bday=? WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setDate(1, bday);
        ps.setString(2, uid);
        return ps.executeUpdate();
    }

    public static int getTyp(String uid) throws SQLException {
        String query = "SELECT typ FROM user WHERE userid=?;";
        return Mysql.getIntFromDB(uid, query);
    }

    public static int setTyp(String uid, int typ) throws SQLException{
        String query = "UPDATE user SET typ=? WHERE userid=?";
        return Mysql.setInt(uid, query, typ);
    }

    public static String getFirstname(String uid) throws SQLException{
        String query = "SELECT firstname FROM user WHERE userid=?;";
        return Mysql.getStringFromDB(uid, query);
    }

    public static int setFirstname(String uid, String firstname) throws SQLException{
        String query = "UPDATE user SET firstname=? WHERE userid=?";
        return Mysql.setString(uid, query, firstname);
    }

    public static String getLastname(String uid) throws SQLException{
        String query = "SELECT lastname FROM user WHERE userid=?;";
        return Mysql.getStringFromDB(uid, query);
    }

    public static int setLastname(String uid, String lastname) throws SQLException{
        String query = "UPDATE user SET lastname=? WHERE userid=?";
        return Mysql.setString(uid, query, lastname);
    }

    public static String getUserid(String username) throws SQLException{
        String query = "SELECT userid FROM user WHERE username=?;";
        return Mysql.getStringFromDB(username, query);
    }

    public static String getUsername(String uid) throws SQLException{
        String query = "SELECT username FROM user WHERE userid=?;";
        return Mysql.getStringFromDB(uid, query);
    }

    public static int setUsername(String uid, String username) throws SQLException{
        String query = "UPDATE user SET username=? WHERE userid=?";
        return Mysql.setString(uid, query, username);
    }

    public static int getSalt(String uid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "SELECT salt FROM user WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            return 1;
        }else {
            int salt = 0;
            while (rs.next()) {
                salt = rs.getInt(1);
            }
            ps.close();
            return salt;
        }
    }

    public static boolean exists(String uid) throws SQLException {
        boolean usernameExists;
        String result = "";
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "SELECT username FROM user WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            result = rs.getString(1);
        }
        ps.close();
        if(result.length() == 0){
            return false;
        }else {
            return true;
        }

    }

    public static int createUser(String username, String lastname, String firstname, String nonHashedPassword, String address, int plz, Date bday, int typ) throws SQLException{
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "INSERT INTO user (userid, username, lastname, firstname, password, salt, address, plz, bday, typ) VALUES (UUID(), ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        if(!Checks.isName(lastname)){
            return 400;
        }
        if(!Checks.isName(firstname)){
            return 401;
        }
        if(!Checks.isPassword(nonHashedPassword)){
            return 402;
        }
        if(Checks.isNull(address)){
            return 403;
        }
        if(!Checks.isPLZ(plz)){
            return 404;
        }

        java.sql.Date sqlDate = new java.sql.Date(bday.getTime());
        int salt = 10000000 + (int)(Math.random() * ((99999999 - 10000000) + 1));

        ps.setString(1, username);
        ps.setString(2, lastname);
        ps.setString(3, firstname);
        ps.setString(4, Krypto.getHash(nonHashedPassword, Integer.toString(salt)));
        ps.setInt(5, salt);
        ps.setString(6, address);
        ps.setInt(7, plz);
        ps.setDate(8, sqlDate);
        ps.setInt(9, typ);

        int rows = ps.executeUpdate();
        ps.close();

        return rows;
    }

    public static int deleteUser(String uid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "DELETE FROM user WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        return ps.executeUpdate();
    }

}
