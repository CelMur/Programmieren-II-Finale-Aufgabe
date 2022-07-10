package de.jprojekt.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import de.jprojekt.controller.interfaces.ICustomerController;
import de.jprojekt.controller.interfaces.IUserController;
import de.jprojekt.data.models.BankAccount;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.data.models.User;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.Krypto;
import de.jprojekt.utils.mysql.DBAccount;
import de.jprojekt.utils.mysql.DBCustomer;
import de.jprojekt.utils.mysql.DBUser;

public class CustomerController extends UserController implements ICustomerController, IUserController {

    @Override
    public void update(Customer c) throws BankingException {
        if (c == null) {
            throw new BankingException("Kunde nicht gefunden.");
        }
        try {
            DBCustomer.setBankerid(c.getId(), c.getAdviser().getId());
            super.update(c);

        } catch (Exception e) {
            throw new BankingException("Es ist ein Fehler aufgetreten.");
        }

    }

    @Override
    public void delete(Customer c) {
        try {
            DBCustomer.deleteCustomer(c.getId());
            DBUser.deleteUser(c.getId());
            c = null;
        } catch (Exception e) {
            System.out.println("Kunde konnte nicht gelöscht werden.");
        }

    }

    @Override
    public void create(Customer customer) throws BankingException {
        try {
            customer.setId(DBUser.createUser(customer.getLastname(), customer.getFirstname(), customer.getPassword(),
                    customer.getAddress(), customer.getPlz(), customer.getBday(), User.TYPE_CUSTOMER));
            DBCustomer.createCustomer(customer.getId(), customer.getAdviser().getId());
        } catch (Exception e) {
            throw new BankingException(e.getMessage());
        }
    }

    @Override
    public boolean isPasswordValid(Customer customer, String password) throws BankingException {
        try {
            return DBUser.checkPassword(customer.getId(), password);
        } catch (Exception e) {
            return false;
        }
    }
}
