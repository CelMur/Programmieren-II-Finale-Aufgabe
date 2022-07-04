package de.jprojekt.controller;

import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;

public class EmployeeController implements IEmployeeController{
    @Override
    public void update(Employee e) {
        //TODO persist in DB

    }

    @Override
    public void delete(Employee e) {
        // TODO check and persist in DB
        
    }

    @Override
    public Customer create(Employee employee) {
        // TODO persist in DB
        return null;
    }
}
