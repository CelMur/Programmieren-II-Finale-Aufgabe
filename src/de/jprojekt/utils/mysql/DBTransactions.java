package de.jprojekt.utils.mysql;

import java.sql.*;

public class DBTransactions {

    public static String createTransaction(String accountidfrom, String accountidto, long balance, Date date) throws Exception {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String uuid =  Mysql.createNewUUID();
        String query = "INSERT INTO transactions (transactionid, accountidfrom, accountidto, balance, date) VALUES (?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uuid);
        ps.setString(2, accountidfrom);
        ps.setString(3, accountidto);
        ps.setLong(4, balance);
        ps.setDate(5, date);
        ps.executeUpdate();
        ps.close();

        return uuid;
    }

    public static String getAccountidfrom(String transactionid) throws Exception {
        String query = "SELECT accountidfrom FROM transactions WHERE transactionid=?;";
        return Mysql.getStringFromDB(transactionid, query);
    }

    public static String getAccountidto(String transactionid) throws Exception {
        String query = "SELECT accountidto FROM transactions WHERE transactionid=?;";
        return Mysql.getStringFromDB(transactionid, query);
    }

    public static long getBalance(String transactionid) throws Exception {
        String query = "SELECT accountidto FROM transactions WHERE transactionid=?;";
        return Mysql.getLongFromDB(transactionid, query);
    }

    public static Date getDate(String transactionid) throws SQLException{
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "SELECT date FROM transactions WHERE transactionid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, transactionid);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            ps.close();
            return null;
        }else {
            java.sql.Date bday = (Date) new java.util.Date();
            while (rs.next()) {
                bday = rs.getDate(1);
            }
            ps.close();
            return bday;
        }
    }

    public static int deleteTransaction(String transactionid) throws SQLException {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "DELETE FROM transactions WHERE transactionid=?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, transactionid);
        int rows = ps.executeUpdate();
        ps.close();
        return rows;
    }

}
