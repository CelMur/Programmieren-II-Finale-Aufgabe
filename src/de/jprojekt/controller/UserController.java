package de.jprojekt.controller;

import java.sql.Date;
import java.sql.SQLException;

import de.jprojekt.data.models.User;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.mysql.DBUser;

public abstract class UserController {
    public void update(User user) throws BankingException {
        try {
            String id = user.getId();
            DBUser.setAddress(id, user.getAddress());
            DBUser.setBday(id, new Date(user.getBday().getTime()));
            DBUser.setFirstname(id, user.getFirstname());
            DBUser.setLastname(id, user.getLastname());
            DBUser.setPlz(id, user.getPlz());
        } catch (SQLException exception) {
            throw new BankingException(exception.getMessage());
        }
    }

    public void changePassword(User user, String passwordOld, String passwordNew) throws BankingException {
        try {
            if(!DBUser.checkPassword(user.getId(), passwordOld)) {
                throw new BankingException("Altes Passwort ist falsch!");
            }
            DBUser.setPassword(user.getId(), passwordNew);
        } catch(Exception e) {
            throw new BankingException(e.getMessage());
        }
    }

    public boolean isPasswordValid(User user, String password) throws BankingException {
        try {
            return DBUser.checkPassword(user.getId(), password);
        } catch (Exception e) {
            return false;
        }

    }

}
