package de.jprojekt.utils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mysql {

    static String url = "jdbc:mysql://nuernberg.jkristof.de:6033/sql11496325";
    static String user = "sql11496325";
    static String pass = "VGmRxNrWr5";

    public static void createDB() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        Statement stm = con.createStatement();

        String queryUser = "CREATE TABLE IF NOT EXISTS user (userid varchar(255), username varchar(255), lastname varchar(255), firstname varchar(255), password varchar(255), salt int, address varchar(255), plz int, bday DATE, typ int);";
        String queryBanker = "CREATE TABLE IF NOT EXISTS banker (bankerid int);";
        String queryCustomer = "CREATE TABLE IF NOT EXISTS custumer (customerid int, bankerid int, accountid int);";
        String queryAccount = "CREATE TABLE IF NOT EXISTS account (accountid int, balance bigint, typ int, customerid int, name varchar(255), maxdebt bigint, locked int);";
        String queryTransactions = "CREATE TABLE IF NOT EXISTS transactions (accountidfrom int, accountidto int, balance bigint, date text(255));";

        stm.addBatch(queryUser);
        stm.addBatch(queryBanker);
        stm.addBatch(queryCustomer);
        stm.addBatch(queryAccount);
        stm.addBatch(queryTransactions);
        stm.executeBatch();
        stm.close();

        System.out.println("Datenbank erfolgreich angelegt, falls noch nicht vorhanden.");
    }

    public static void testClass() throws SQLException {
        java.util.Date date = new java.util.Date();
        date.getTime();

        System.out.println(createCustomer("unique","Hans", "Peter", "Haribo&123", "J7 26", 68159, date, 1));
        System.out.println(getPassword("ed899d45-fbcc-11ec-b8e1-0242ac180002"));
        System.out.println(usernameExists("test"));
        System.out.println(usernameExists("unique"));
    }
    public static String getPassword(String userid) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT password FROM user WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, userid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            return "";
        }else {
            String password = "";
            while (rs.next()) {
                password = rs.getString(1);
            }
            ps.close();
            return password;
        }
    }

    public static String getUUID(String username) throws SQLException{
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT userid FROM user WHERE username=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            return "";
        }else {
            String uuid = "";
            while (rs.next()) {
                uuid = rs.getString(1);
            }
            ps.close();
            return uuid;
        }
    }

    public static String getUsername(String uuid) throws SQLException{
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT username FROM user WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uuid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            return "";
        }else {
            String username = "";
            while (rs.next()) {
                username = rs.getString(1);
            }
            ps.close();
            return username;
        }
    }

    public static int getSalt(String userid) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT salt FROM user WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, userid);
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

    public static boolean usernameExists(String username) throws SQLException {
        boolean usernameExists;
        String result = "";
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT username FROM user WHERE username=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
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

    public static String createCustomer(String username, String lastname, String firstname, String nonHashedPassword, String address, int plz, Date bday, int typ) throws SQLException{
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "INSERT INTO user (userid, username, lastname, firstname, password, salt, address, plz, bday, typ) VALUES (UUID(), ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        if(!Checks.isName(lastname)){
            return "error_lastname";
        }
        if(!Checks.isName(firstname)){
            return "error_firstname";
        }
        if(!Checks.isPassword(nonHashedPassword)){
            return "error_password";
        }
        if(Checks.isNull(address)){
            return "error_address";
        }
        if(!Checks.isPLZ(plz)){
            return "error_plz";
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

        ps.executeUpdate();
        ps.close();

        return "success";
    }
}