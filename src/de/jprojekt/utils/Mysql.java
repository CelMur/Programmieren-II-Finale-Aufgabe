package de.jprojekt.utils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mysql {

    static String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11496325";
    static String user = "sql11496325";
    static String pass = "VGmRxNrWr5";

    public static void createDB() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        Statement stm = con.createStatement();

        String queryUser = "CREATE TABLE IF NOT EXISTS user (userid varchar(255), lastname varchar(255), firstname varchar(255), password varchar(255), salt int, address varchar(255), plz int, bday DATE, typ int);";
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

        System.out.println(createCustomer("Hans", "Peter", "Haribo&123", "J7 26", 68159, date, 1));
        System.out.println(getPassword("b48c7cd3-dff8-11ec-ab47-06470e5e03a1"));
    }
    public static String getPassword(String userid) throws SQLException {
        String password = "";
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT password FROM user WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, userid);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            password = rs.getString(1);
        }
        ps.close();
        return password;
    }

    public static int getSalt(String userid) throws SQLException {
        int salt = 1;
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "SELECT salt FROM user WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, userid);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            salt = rs.getInt(1);
        }
        ps.close();
        return salt;
    }

    public static String createCustomer(String lastname, String firstname, String nonHashedPassword, String address, int plz, Date bday, int typ) throws SQLException{
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "INSERT INTO user (userid, lastname, firstname, password, salt, address, plz, bday, typ) VALUES (UUID(), ?, ?, ?, ?, ?, ?, ?, ?);";
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

        ps.setString(1, lastname);
        ps.setString(2, firstname);
        ps.setString(3, Krypto.getHash(nonHashedPassword, Integer.toString(salt)));
        ps.setInt(4, salt);
        ps.setString(5, address);
        ps.setInt(6, plz);
        ps.setDate(7, sqlDate);
        ps.setInt(8, typ);

        ps.executeUpdate();
        ps.close();

        return "success";
    }
}