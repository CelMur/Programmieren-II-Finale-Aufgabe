package de.jprojekt.controller.interfaces;

import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.utils.BankingException;

public interface IEmployeeController {
    public void update(Employee e) throws BankingException;

    public void delete(Employee e) throws BankingException;

    public Customer create(Employee employee);
}
