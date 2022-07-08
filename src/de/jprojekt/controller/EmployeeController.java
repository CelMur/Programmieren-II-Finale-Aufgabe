package de.jprojekt.controller;

import java.sql.SQLException;

import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.mysql.DBBanker;
import de.jprojekt.utils.mysql.DBUser;

public class EmployeeController extends UserController implements IEmployeeController {
    @Override
    public void update(Employee e) throws BankingException {
        super.update(e.getUser());
    }

    @Override
    public void delete(Employee e) throws BankingException{
        try {
            if(DBBanker.getAmountCustomers(e.getId()) != 0) {
                throw new BankingException("Can not delete employee while they have customers assigned!");
            }
            DBBanker.deleteBanker(e.getId());
        }
        catch(SQLException exception) {
            throw new BankingException(exception.getMessage());
        }
        // TODO check and persist in DB
        
    }

    @Override
    public void create(Employee employee) throws BankingException {
        try {
            //int id = DBUser.createUser(employee., lastname, firstname, nonHashedPassword, address, plz, bday, typ)
            if(DBBanker.createBanker(employee.getId()) != 1) {
                throw new BankingException("Could not create employee in DB");
            }
            
        } catch (SQLException exception) {
            throw new BankingException(exception.getMessage());
        }
    }
}
