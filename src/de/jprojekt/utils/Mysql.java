package de.jprojekt.utils;

import java.sql.*;

public class Mysql {

    static String url = "jdbc:mysql://10.1.0.8:3306/javaprojekt";
    static String user = "javaprojekt";
    static String pass = "3Q01YVYUc/Kuvr@K";

    public static void createDB() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        Statement stm = con.createStatement();

        String queryUser = "CREATE TABLE IF NOT EXISTS user (userid int, lastname varchar(255), firstname varchar(255), password varchar(255), address varchar(255), plz int, bday text(255), typ int);";
        String queryBanker = "CREATE TABLE IF NOT EXISTS banker (bankerid int);";
        String queryCustomer = "CREATE TABLE IF NOT EXISTS custumer (customerid int, bankerid int, accountid int);";
        String queryAccount = "CREATE TABLE IF NOT EXISTS account (accountid int, balance bigint, typ int, customerid int, name varchar(255), maxdebt bigint);";
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

    public static String createCustomer(String lastname, String firstname, String password, String address, int plz) throws SQLException{
        Connection con = DriverManager.getConnection(url, user, pass);
        String query = "INSERT INTO customer (lastname, firstname, password, address, plz) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        if(!Checks.isName(lastname)){
            return "error_lastname";
        }
        if(!Checks.isName(firstname)){
            return "error_firstname";
        }
        if(!Checks.isPassword(password)){
            return "error_password";
        }
        if(Checks.isNull(address)){
            return "error_address";
        }
        if(!Checks.isPLZ(plz)){
            return "error_plz";
        }

        ps.setString(1, lastname);
        ps.setString(2, firstname);
        ps.setString(3, Krypto.getHash(password, "hans"));
        ps.setString(4, address);
        ps.setInt(5, plz);

        ps.executeUpdate();
        ps.close();

        return "success";
    }
}