package de.jprojekt.controller;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.data.models.User;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.mysql.DBUser;

public class SessionController implements ISessionController {
	public boolean login(String username, String password) throws Exception {
		String userid = username; //DBUser.getUserid(username);
        if(!DBUser.checkPassword(userid, password)) {
            return false;
        }

        User user = DBUser.getUser(userid);
        ApplicationData.getInstance().setCurrentUser(DBUser.getUser(user.getId()));
        return true;
    }
	 
	public void logout() {

    }
}
