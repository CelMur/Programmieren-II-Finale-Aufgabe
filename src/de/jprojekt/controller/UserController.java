package de.jprojekt.controller;

import java.sql.Date;
import java.sql.SQLException;

import de.jprojekt.data.models.User;
import de.jprojekt.utils.BankingException;
import de.jprojekt.utils.mysql.DBUser;

public abstract class UserController {
    void update(User user) throws BankingException {
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
    
}
