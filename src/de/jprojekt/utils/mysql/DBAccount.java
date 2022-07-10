package de.jprojekt.utils.mysql;

import de.jprojekt.data.models.*;
import de.jprojekt.utils.BankingException;

import java.sql.*;
import java.util.ArrayList;

public class DBAccount {

    public static String createAccount(String customerid, String name, int typ) throws Exception {
        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String uuid =  Mysql.createNewUUID();
        String query = "INSERT INTO account (accountid, balance, typ, customerid, name, maxdebt, locked) VALUES (?,?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uuid);
        ps.setInt(2, 0);
        ps.setInt(3, typ);
        ps.setString(4, customerid);
        ps.setString(5, name);
        ps.setInt(6, 0);
        ps.setInt(7,0);
        ps.executeUpdate();
        ps.close();

        return uuid;
    }

    public static BankAccount getAccount(String accountid) throws Exception {
        switch (getTyp(accountid)) {
            case 0:
                GiroAccount gacc = new GiroAccount(accountid, (Customer) DBUser.getUser(getCustomer(accountid)), accountid);
                gacc.setBalance(getBalance(accountid));
                gacc.setName(getName(accountid));
                gacc.setMaxDebt(getMaxdebt(accountid));
                gacc.setLocked(getLocked(accountid));
                return gacc;
            case 1:
                SavingAccount sacc = new SavingAccount(accountid, (Customer) DBUser.getUser(getCustomer(accountid)), accountid);
                sacc.setBalance(getBalance(accountid));
                sacc.setName(getName(accountid));
                sacc.setMaxDebt(getMaxdebt(accountid));
                sacc.setLocked(getLocked(accountid));
                return sacc;
            case 2:
                DepositAccount dacc = new DepositAccount(accountid, (Customer) DBUser.getUser(getCustomer(accountid)), accountid);
                dacc.setBalance(getBalance(accountid));
                dacc.setName(getName(accountid));
                dacc.setMaxDebt(getMaxdebt(accountid));
                dacc.setLocked(getLocked(accountid));
                return dacc;
            default:
                throw new BankingException("Unbekannter Kontotyp.");
        }
    }

    public static long getBalance(String accountid) throws Exception {
        String query = "SELECT balance FROM account WHERE accountid=?;";
        return Mysql.getLongFromDB(accountid, query);
    }

    public static int setBalance(String accountid, long balance) throws SQLException{
        String query = "UPDATE account SET balance=? WHERE accountid=?";
        return Mysql.setLong(accountid, query, balance);
    }

    public static int getTyp(String accountid) throws Exception {
        String query = "SELECT typ FROM account WHERE accountid=?;";
        return Mysql.getIntFromDB(accountid, query);
    }

    public static String getCustomer(String accountid) throws Exception {
        String query = "SELECT customerid FROM account WHERE accountid=?;";
        return Mysql.getStringFromDB(accountid, query);
    }

    public static int setTyp(String accountid, int typ) throws SQLException{
        String query = "UPDATE account SET typ=? WHERE accountid=?";
        return Mysql.setLong(accountid, query, typ);
    }

    public static String getName(String accountid) throws Exception {
        String query = "SELECT name FROM account WHERE accountid=?;";
        return Mysql.getStringFromDB(accountid, query);
    }

    public static int setName(String accountid, String name) throws SQLException{
        String query = "UPDATE account SET name=? WHERE accountid=?";
        return Mysql.setString(accountid, query, name);
    }

    public static long getMaxdebt(String accountid) throws Exception {
        String query = "SELECT maxdebt FROM account WHERE accountid=?;";
        return Mysql.getLongFromDB(accountid, query);
    }

    public static int setMaxdebt(String accountid, long maxdebt) throws SQLException{
        String query = "UPDATE account SET maxdebt=? WHERE accountid=?";
        return Mysql.setLong(accountid, query, maxdebt);
    }

    public static boolean getLocked(String accountid) throws Exception {
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

    public static ArrayList<BankAccount> getBankAccountsForCustomer(Customer customer) throws SQLException { 
        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

        Connection con = DriverManager.getConnection(Mysql.url, Mysql.user, Mysql.pass);
        String query = "SELECT * FROM account WHERE customerid = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, customer.getId());
        ResultSet rs = ps.executeQuery();

        while(rs.next())  {
            String accountid    = String.valueOf(rs.getInt("accountid"));
            String name         = rs.getString("name");
            int balance         = rs.getInt("balance");
            int type            = rs.getInt("typ");
            int maxDebt         = rs.getInt("maxdebt");
            int locked          = rs.getInt("locked");

            if(type == BankAccount.TYPE_GIRO) {
                GiroAccount account = new GiroAccount(name, customer, accountid);
                account.setId(accountid);
                account.setBalance(balance);
                account.setMaxDebt(maxDebt);
                account.setLocked(locked != 0);
                accounts.add(account);
            } else {
                SavingAccount account = new SavingAccount(name, customer, accountid);
                account.setId(accountid);
                account.setBalance(balance);
                account.setLocked(locked != 0);
                accounts.add(account);
            }
        }

        return accounts;
    }

}
