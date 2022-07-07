package de.jprojekt.utils.mysql;

import java.sql.*;

public class Mysql {

    static String url = "jdbc:mysql://nuernberg.jkristof.de:6033/sql11496325";
    static String user = "sql11496325";
    static String pass = "VGmRxNrWr5";

    public static void createDB() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        Statement stm = con.createStatement();

        String queryUser = "CREATE TABLE IF NOT EXISTS user (userid varchar(255), username varchar(255), lastname varchar(255), firstname varchar(255), password varchar(255), salt int, address varchar(255), plz int, bday DATE, typ int);";
        String queryBanker = "CREATE TABLE IF NOT EXISTS banker (bankerid varchar(255));";
        String queryCustomer = "CREATE TABLE IF NOT EXISTS customer (customerid varchar(255), bankerid varchar(255), accountid varchar(255));";
        String queryAccount = "CREATE TABLE IF NOT EXISTS account (accountid int, balance bigint, typ int, customerid varchar(255), name varchar(255), maxdebt bigint, locked int);";
        String queryTransactions = "CREATE TABLE IF NOT EXISTS transactions (transactionid varchar(255), accountidfrom varchar(255), accountidto varchar(255), balance bigint, date DATE);";

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

        System.out.println(User.createUser("unique","Hans", "Peter", "Haribo&123", "J7 26", 68159, date, 1));
        System.out.println(User.getPassword("ed899d45-fbcc-11ec-b8e1-0242ac180002"));
        System.out.println(User.exists("test"));
        System.out.println(User.exists("86afefbd-fc8e-11ec-b8e1-0242ac180002"));
        System.out.println(User.getLastname("86afefbd-fc8e-11ec-b8e1-0242ac180002"));
    }

    public static String getStringFromDB(String uid, String query) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            return "";
        }else {
            String str = "";
            while (rs.next()) {
                str = rs.getString(1);
            }
            ps.close();
            return str;
        }
    }

    public static int getIntFromDB(String uid, String query) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            return 0;
        }else {
            int num = 0;
            while (rs.next()) {
                num = rs.getInt(1);
            }
            ps.close();
            return num;
        }
    }

    public static long getLongFromDB(String accountid, String query) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, accountid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            return 0;
        }else {
            long num = 0;
            while (rs.next()) {
                num = rs.getLong(1);
            }
            ps.close();
            return num;
        }
    }

    public static int setLong(String accountid, String query, long param) throws SQLException{
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setLong(1, param);
        ps.setString(2, accountid);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

    public static int setString(String uid, String query, String param) throws SQLException{
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, param);
        ps.setString(2, uid);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

    public static int setInt(String uid, String query, int param) throws SQLException{
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, param);
        ps.setString(2, uid);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

}
