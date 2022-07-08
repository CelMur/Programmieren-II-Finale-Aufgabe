package de.jprojekt.controller;

import java.sql.SQLException;

import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.mysql.OldBanker;

public class EmployeeController extends UserController implements IEmployeeController {
    @Override
    public void update(Employee e) throws BankingException {
        super.update(e.getUser());
    }

    @Override
    public void delete(Employee e) throws BankingException{
        try {
            OldBanker.deleteBanker(e.getId());
        }
        catch(SQLException exception) {
            throw new BankingException(exception.getMessage());
        }
        // TODO check and persist in DB
        
    }

    @Override
    public Customer create(Employee employee) {
        // TODO persist in DB
        return null;
    }
}
