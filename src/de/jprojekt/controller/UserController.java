package de.jprojekt.controller;

import java.sql.Date;
import java.sql.SQLException;

import de.jprojekt.data.models.User;
import de.jprojekt.utils.BankingException;

public abstract class UserController {
    void update(User user) throws BankingException {
        try {
            String id = user.getId();
            de.jprojekt.utils.mysql.User.setAddress(id, user.getAddress());
            de.jprojekt.utils.mysql.User.setBday(id, new Date(user.getBday().getTime()));
            de.jprojekt.utils.mysql.User.setFirstname(id, user.getFirstname());
            de.jprojekt.utils.mysql.User.setLastname(id, user.getLastname());
            de.jprojekt.utils.mysql.User.setPlz(id, user.getPlz());
        } catch (SQLException exception) {
            throw new BankingException(exception.getMessage());
        }
    }
}
