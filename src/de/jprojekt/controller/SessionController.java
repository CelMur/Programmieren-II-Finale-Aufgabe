package de.jprojekt.controller;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.data.models.Customer;
import de.jprojekt.data.models.Employee;
import de.jprojekt.data.models.User;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.mysql.DBAccount;
import de.jprojekt.utils.mysql.DBUser;

public class SessionController implements ISessionController {
	public boolean login(String username, String password) {
        try {
            String userid = DBUser.getUserid(username);
            if(!DBUser.checkPassword(userid, password)) {
                return false;
            }

            User user = DBUser.getUser(userid);
            if(user.getType() == User.TYPE_CUSTOMER) {
                Customer customer = new Customer(user.getId(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getBday(), user.getAddress(), user.getPlz()); 
                customer.setBankAccounts(DBAccount.getBankAccountsForCustomer(customer));
                ApplicationData.getInstance().setCurrentUser(customer);
            } else  {
                Employee employee = new Employee(user.getId(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getBday(), user.getAddress(), user.getPlz());
                ApplicationData.getInstance().setCurrentUser(employee);
            }
        } catch(Exception exception) {
            return false;
        }
        return true;
    }
	
	public void logout() {

    }
}
