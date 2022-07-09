package de.jprojekt.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.data.models.BankAccount;
import de.jprojekt.data.models.Customer;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.Krypto;
import de.jprojekt.utils.mysql.DBAccount;
import de.jprojekt.utils.mysql.DBCustomer;
import de.jprojekt.utils.mysql.DBUser;

public class CustomerController implements ICustomerController {


    /**
     * 
     * Params can be null or Fields to update
     */
    @Override
    public void update(Customer c) throws BankingException {
        if(c == null) {
            throw new BankingException("Kunde nicht gefunden.");
        }
        try{
            DBCustomer.setBankerid(c.getId(), c.getAdviser().getId());
            DBUser.setAddress(c.getId(), c.getAddress());
            //TODO Brithday ggf konvertiren
            //DBUser.setBday(c.getId(), c.getBday());
            DBUser.setPlz(c.getId(), c.getPlz());
            DBUser.setFirstname(c.getId(), c.getFirstname());
            DBUser.setLastname(c.getId(), c.getLastname());

        }catch(SQLException e){
            throw new BankingException("Es ist ein Fehler aufgetreten.");
        }

        
    }

    @Override
    public void delete(Customer c) {
        try{
            DBCustomer.deleteCustomer(c.getId());
            DBUser.deleteUser(c.getId());
            c = null;
        }catch(Exception e){
            System.out.println("Kunde konnte nicht gelöscht werden.");
        }
        
    }

    @Override
    public void create(Customer customer) {
        try{
           
            customer.setId(DBUser.createUser(customer.getLastname(), customer.getFirstname(), customer.getPassword(), customer.getAddress(), customer.getPlz(), customer.getBday(), 0));	
            DBCustomer.createCustomer(customer.getId(), customer.getAdviser().getId());
        }catch(Exception e){
            System.out.println("Kunde konnte nicht erstellt werden.");
        }
        
    }

    @Override
    public boolean isPasswordValid(Customer customer, String password) throws BankingException {
        if(customer == null) {
            return false;
        }
        int salt;
        
        try {
            salt = DBUser.getSalt(customer.getId());
        } catch (SQLException e) {
            throw new BankingException("error while checking password");
        }
        try {
            if(DBUser.getPassword(customer.getId()).equals(Krypto.getHash(password, Integer.toString(salt)))) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            throw new BankingException("error while checking password");
        }
        
    }


}
    

