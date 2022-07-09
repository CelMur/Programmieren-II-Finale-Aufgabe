package de.jprojekt.utils.mysql;

import java.sql.*;

public class Mysql {

    static String url = "jdbc:mysql://nuernberg.jkristof.de:6033/sql11496325";
    static String user = "sql11496325";
    static String pass = "VGmRxNrWr5";

    public static void createDB() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        Statement stm = con.createStatement();

        String queryUser = "CREATE TABLE IF NOT EXISTS user (userid varchar(255), lastname varchar(255), firstname varchar(255), password varchar(255), salt int, address varchar(255), plz int, bday DATE, typ int, PRIMARY KEY (userid));";
        String queryBanker = "CREATE TABLE IF NOT EXISTS banker (bankerid varchar(255), customerid varchar(255), PRIMARY KEY (bankerid));";
        String queryCustomer = "CREATE TABLE IF NOT EXISTS customer (customerid varchar(255), bankerid varchar(255), accountid varchar(255), PRIMARY KEY (customerid));";
        String queryAccount = "CREATE TABLE IF NOT EXISTS account (accountid int, balance bigint, typ int, customerid varchar(255), name varchar(255), maxdebt bigint, locked int, PRIMARY KEY (accountid));";
        String queryTransactions = "CREATE TABLE IF NOT EXISTS transactions (transactionid varchar(255), accountidfrom varchar(255), accountidto varchar(255), balance bigint, date DATE, PRIMARY KEY (transactionid));";
        String queryTmp = "CREATE TABLE IF NOT EXISTS tmp (uuid varchar(255));";

        stm.addBatch(queryUser);
        stm.addBatch(queryBanker);
        stm.addBatch(queryCustomer);
        stm.addBatch(queryAccount);
        stm.addBatch(queryTransactions);
        stm.addBatch(queryTmp);
        stm.executeBatch();
        stm.close();

        System.out.println("Datenbank erfolgreich angelegt, falls noch nicht vorhanden.");
    }

    public static void testClass() throws Exception {
        java.util.Date date = new java.util.Date();

        try {
            System.out.println(DBUser.createUser("Hans", "Peter", "Haribo&123", "J7 26", 68159, date, 1));
            System.out.println(DBUser.getPassword("ed899d45-fbcc-11ec-b8e1-0242ac180002"));
            System.out.println(DBUser.exists("test"));
            System.out.println(DBUser.exists("bf983ea3-fef2-11ec-b8e1-0242ac180002"));
            System.out.println(DBUser.getLastname("bf983ea3-fef2-11ec-b8e1-0242ac180002"));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static String getStringFromDB(String uid, String query) throws Exception {
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            throw new Exception("Keinen Eintrag gefunden");
        }else {
            String str = "";
            while (rs.next()) {
                str = rs.getString(1);
            }
            ps.close();
            return str;
        }
    }

    public static int getIntFromDB(String uid, String query) throws Exception {
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            throw new Exception("Keinen Eintrag gefunden");
        }else {
            int num = 0;
            while (rs.next()) {
                num = rs.getInt(1);
            }
            ps.close();
            return num;
        }
    }

    public static long getLongFromDB(String accountid, String query) throws Exception {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, accountid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            throw new Exception("Keinen Eintrag gefunden");
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

    public static String createNewUUID() throws Exception {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query1a = "DELETE FROM tmp WHERE 1=1;";
        String query1b = "INSERT INTO tmp (uuid) VALUES (UUID());";
        PreparedStatement pa = con.prepareStatement(query1a);
        PreparedStatement pb = con.prepareStatement(query1b);
        pa.executeUpdate();
        pa.close();
        pb.executeUpdate();
        pb.close();

        String query = "SELECT uuid FROM tmp WHERE 1=1;";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            throw new Exception("Keinen Eintrag gefunden");
        }else {
            String str = "";
            while (rs.next()) {
                str = rs.getString(1);
            }
            ps.close();
            return str;
        }
    }

}
