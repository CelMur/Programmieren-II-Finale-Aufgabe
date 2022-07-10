package de.jprojekt.controller;

import de.jprojekt.controller.interfaces.ISessionController;
import de.jprojekt.data.models.User;
import de.jprojekt.main.ApplicationData;
import de.jprojekt.utils.mysql.DBUser;

public class SessionController implements ISessionController {
	public boolean login(String username, String password) {
        try {
            String userid = DBUser.getUserid(username);
            if(!DBUser.checkPassword(userid, password)) {
                return false;
            }

            User user = DBUser.getUser(userid);
            ApplicationData.getInstance().setCurrentUser(DBUser.getUser(user.getId()));
        } catch(Exception exception) {
            return false;
        }
        return true;
    }
	
	public void logout() {

    }
}
