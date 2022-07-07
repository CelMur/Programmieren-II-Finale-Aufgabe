package de.jprojekt.utils.mysql;

import java.sql.*;

public class Account {

    public static int createAccount(String customerid, String name, int typ) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "INSERT INTO account (accountid, balance, typ, customerid, name, maxdebt, locked) VALUES (UUID(),?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, 0);
        ps.setInt(2, typ);
        ps.setString(3, customerid);
        ps.setString(4, name);
        ps.setInt(5, 0);
        ps.setInt(6,0);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

    public static long getBalance(String accountid) throws SQLException{
        String query = "SELECT balance FROM account WHERE accountid=?;";
        return Mysql.getLongFromDB(accountid, query);
    }

    public static int setBalance(String accountid, long balance) throws SQLException{
        String query = "UPDATE account SET balance=? WHERE accountid=?";
        return Mysql.setLong(accountid, query, balance);
    }

    public static int getTyp(String accountid) throws SQLException{
        String query = "SELECT typ FROM account WHERE accountid=?;";
        return Mysql.getIntFromDB(accountid, query);
    }

    public static int setTyp(String accountid, int typ) throws SQLException{
        String query = "UPDATE account SET typ=? WHERE accountid=?";
        return Mysql.setLong(accountid, query, typ);
    }

    public static String getName(String accountid) throws SQLException{
        String query = "SELECT name FROM account WHERE accountid=?;";
        return Mysql.getStringFromDB(accountid, query);
    }

    public static int setName(String accountid, String name) throws SQLException{
        String query = "UPDATE account SET name=? WHERE accountid=?";
        return Mysql.setString(accountid, query, name);
    }

    public static long getMaxdebt(String accountid) throws SQLException{
        String query = "SELECT maxdebt FROM account WHERE accountid=?;";
        return Mysql.getLongFromDB(accountid, query);
    }

    public static int setMaxdebt(String accountid, long maxdebt) throws SQLException{
        String query = "UPDATE account SET maxdebt=? WHERE accountid=?";
        return Mysql.setLong(accountid, query, maxdebt);
    }

    public static boolean getLocked(String accountid) throws SQLException{
        String query = "SELECT locked FROM account WHERE accountid=?;";
        int locked = Mysql.getIntFromDB(accountid, query);
        if(locked == 0){
            return false;
        }else {
            return true;
        }
    }

    public static int setLocked(String accountid, int locked) throws SQLException{
        String query = "UPDATE account SET locked=? WHERE accountid=?";
        return Mysql.setLong(accountid, query, locked);
    }
    public static int deleteAccount(String accountid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "DELETE FROM account WHERE accountid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, accountid);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

}