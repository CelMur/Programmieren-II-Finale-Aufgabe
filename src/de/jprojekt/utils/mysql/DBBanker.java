package de.jprojekt.utils.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.jprojekt.data.models.Employee;

public class DBBanker {

    public static void createBanker(String uid) throws Exception {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "INSERT INTO banker (bankerid) VALUES (?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        int rows = ps.executeUpdate();
        if (rows == 0) {
            throw new SQLException("Could not create banker");
        }
        ps.close();
    }

    public static String getCustomerid(String bankerid) throws Exception {
        String query = "SELECT customerid FROM banker WHERE bankerid=?;";
        return Mysql.getStringFromDB(bankerid, query);
    }

    public static int getAmountCustomers(String bankerid) throws Exception {
        String query = "SELECT count(customerid) AS anz FROM customer WHERE bankerid = ? GROUP BY customerid";
        return Mysql.getIntFromDB(bankerid, query);
    }

    public static int addCustomer(String bankerid, String customerid) throws Exception {
        List<String> customer = new ArrayList<String>();
        customer = List.of(getCustomerid(bankerid).split(";"));
        customer.add(customerid);
        String ids = "";
        for (int i = 0; i < customer.size(); i++) {
            ids = ids + customer.get(i) + ";";
        }
        return setCustomerid(bankerid, ids);
    }

    public static int removeCustomer(String bankerid, String customerid) throws Exception {
        List<String> customer = new ArrayList<String>();
        customer = List.of(getCustomerid(bankerid).split(";"));
        customer.remove(customer.indexOf(customerid));
        String ids = "";
        for (int i = 0; i < customer.size(); i++) {
            ids = ids + customer.get(i) + ";";
        }
        return setCustomerid(bankerid, ids);
    }

    public static int setCustomerid(String bankerid, String customerid) throws SQLException {
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

    public static Employee getEmployee(String uid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "SELECT * FROM user WHERE user.userid = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            throw new SQLException("User not found");
        }

        Employee employee = new Employee(
            rs.getString("userid"),
            rs.getString("password"),
            rs.getString("firstname"),
            rs.getString("lastname"),
            rs.getDate("bday").toString(),
            rs.getString("address"),
            rs.getInt("plz")
        );

        return employee;
    }

}
