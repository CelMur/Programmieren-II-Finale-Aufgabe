package de.jprojekt.controller.interfaces;

import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.data.models.User;
import de.jprojekt.utils.BankingException;

public interface ICustomerController extends IUserController{
    public void update(Customer c) throws BankingException;

    public void delete(Customer c);

    public void create(Customer customer) throws BankingException;

    public void changePassword(User user, String passwordOld, String passwordNew) throws BankingException;
    
    public boolean isPasswordValid(Customer customer, String Password) throws BankingException;
}
