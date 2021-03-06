package de.jprojekt.controller;

import de.jprojekt.controller.interfaces.IEmployeeController;
import de.jprojekt.controller.interfaces.IUserController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.data.models.User;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.mysql.DBBanker;
import de.jprojekt.utils.mysql.DBCustomer;
import de.jprojekt.utils.mysql.DBUser;

public class EmployeeController extends UserController implements IEmployeeController, IUserController {
    /**
     * @param e
     * @throws BankingException
     */
    @Override
    public void update(Employee e) throws BankingException {
        // update user
        super.update(e.getUser());

        // update customers in DB
        try {
            for (Customer customer : e.getCustomers()) {
                if (DBCustomer.getBankerid(customer.getId()) != e.getId()) {
                    DBCustomer.setBankerid(customer.getId(), e.getId());
                }
            }
        } catch (Exception exception) {
            throw new BankingException(exception.getMessage());
        }
    }

    /**
     * @param e
     * @throws BankingException
     */
    @Override
    public void delete(Employee e) throws BankingException {
        try {
            if (DBBanker.getAmountCustomers(e.getId()) != 0) {
                throw new BankingException("Can not delete employee while they have customers assigned!");
            }
            DBBanker.deleteBanker(e.getId());
        } catch (Exception exception) {
            throw new BankingException(exception.getMessage());
        }
    }

    /**
     * @param employee
     * @throws BankingException
     * @todo implement createUser when username is implemented
     */
    @Override
    public void create(Employee employee) throws BankingException {
        try {
            employee.setId(DBUser.createUser(employee.getLastname(), employee.getFirstname(), employee.getPassword(),
                    employee.getAddress(), employee.getPlz(), employee.getBday(), User.TYPE_EMPLOYEE));
            DBBanker.createBanker(employee.getId());
        } catch (Exception exception) {
            throw new BankingException(exception.getMessage());
        }
    }

    @Override
    public boolean isPasswordValid(Employee employee, String password) throws BankingException {
        try {
            return DBUser.checkPassword(employee.getId(), password);
        } catch (Exception e) {
            return false;
        }

    }
}