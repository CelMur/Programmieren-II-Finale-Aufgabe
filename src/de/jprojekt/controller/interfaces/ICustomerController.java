package de.jprojekt.controller.interfaces;

import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.data.models.User;

public interface ICustomerController {
    public void update(Customer c);

    public void delete(Customer c);

    public Customer create(Customer customer, Employee employee);
}