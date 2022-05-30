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

        //System.out.println("HansPeter");

        //System.out.println(createCustomer("Hans", "Peter", "Haribo&123", "J7 26", 68159, 123456, date, 1));
    }
    public static String getPassword(int userid) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "SELCET password FROM user WHERE userid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, userid);
        ps.executeUpdate();
        ps.close();
        return "success";
    }

    public static String createCustomer(String lastname, String firstname, String nonHashedPassword, String address, int plz, int salt, Date bday, int typ) throws SQLException{
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

        ps.setString(1, lastname);
        ps.setString(2, firstname);
        ps.setString(3, Krypto.getHash(nonHashedPassword, Integer.toString(salt)));
        ps.setInt(4, salt);
        ps.setString(5, address);
        ps.setInt(6, plz);
        ps.setDate(7, sqlDate);
        ps.setString(7, bday.toString());
        ps.setInt(8, typ);

        ps.executeUpdate();
        ps.close();

        return "success";
    }
}