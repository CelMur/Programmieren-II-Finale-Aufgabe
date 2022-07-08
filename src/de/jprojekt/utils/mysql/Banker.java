package de.jprojekt.utils.mysql;

import de.jprojekt.data.models.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static String getCustomerid(String bankerid) throws SQLException{
        String query = "SELECT customerid FROM banker WHERE bankerid=?;";
        return Mysql.getStringFromDB(bankerid, query);
    }

    public static int addCustomer(String bankerid, String customerid) throws SQLException{
        List<String> customer = new ArrayList<String>();
        customer = List.of(getCustomerid(bankerid).split(";"));
        customer.add(customerid);
        String ids = "";
        for(int i = 0; i < customer.size(); i++){
            ids = ids + customer.get(i) + ";";
        }
        return setCustomerid(bankerid, ids);
    }

    public static int removeCustomer(String bankerid, String customerid) throws SQLException{
        List<String> customer = new ArrayList<String>();
        customer = List.of(getCustomerid(bankerid).split(";"));
        customer.remove(customer.indexOf(customerid));
        String ids = "";
        for(int i = 0; i < customer.size(); i++){
            ids = ids + customer.get(i) + ";";
        }
        return setCustomerid(bankerid, ids);
    }

    public static int setCustomerid(String bankerid, String customerid) throws SQLException{
        String query = "UPDATE banker SET customerid=? WHERE bankerid=?";
        return Mysql.setString(bankerid, query, customerid);
    }
    public static int deleteBanker(String uid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "DELETE FROM banker WHERE bankerid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

}
