package de.jprojekt.utils.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCustomer {

    public static int createCustomer(String uid, String bankerid, String accountid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "INSERT INTO customer (customerid, bankerid, accountid) VALUES (?,?,?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        ps.setString(2, bankerid);
        ps.setString(3, accountid);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

    public static String getBankerid(String uid) throws Exception {
        String query = "SELECT bankerid FROM customer WHERE customerid=?;";
        return Mysql.getStringFromDB(uid, query);
    }

    public static int setBankerid(String uid, String bankerid) throws SQLException{
        String query = "UPDATE customer SET bankerid=? WHERE customerid=?";
        return Mysql.setString(uid, query, bankerid);
    }

    public static String getAccountid(String uid) throws Exception {
        String query = "SELECT accountid FROM customer WHERE customerid=?;";
        return Mysql.getStringFromDB(uid, query);
    }

    public static int setAccountid(String uid, String accountid) throws SQLException{
        String query = "UPDATE customer SET accountid=? WHERE customerid=?";
        return Mysql.setString(uid, query, accountid);
    }

    public static int addAccount(String customerid, String accountid) throws Exception {
        List<String> account = new ArrayList<String>();
        account = List.of(getAccountid(customerid).split(";"));
        account.add(accountid);
        String ids = "";
        for(int i = 0; i < account.size(); i++){
            ids = ids + account.get(i) + ";";
        }
        return setAccountid(customerid, ids);
    }

    public static int removeAccount(String customerid, String accountid) throws Exception {
        List<String> account = new ArrayList<String>();
        account = List.of(getAccountid(customerid).split(";"));
        account.remove(account.indexOf(accountid));
        String ids = "";
        for(int i = 0; i < account.size(); i++){
            ids = ids + account.get(i) + ";";
        }
        return setAccountid(customerid, ids);
    }

    public static int deleteCustomer(String uid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "DELETE FROM customer WHERE customerid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

}
