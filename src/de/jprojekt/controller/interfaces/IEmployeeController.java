package de.jprojekt.controller.interfaces;

import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.data.models.User;
import de.jprojekt.utils.BankingException;

public interface IEmployeeController extends IUserController {
    public void update(Employee e) throws BankingException;

    public void delete(Employee e) throws BankingException;

    public void create(Employee employee) throws BankingException;

    public void changePassword(User user, String passwordOld, String passwordNew) throws BankingException;
    
    public boolean isPasswordValid(Employee employee, String password) throws BankingException;
}
