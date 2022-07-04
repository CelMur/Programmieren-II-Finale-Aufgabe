package de.jprojekt.controller.interfaces;

import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;

public interface IEmployeeController {
    public void update(Employee e);

    public void delete(Employee e);

    public Customer create(Employee employee);
}
