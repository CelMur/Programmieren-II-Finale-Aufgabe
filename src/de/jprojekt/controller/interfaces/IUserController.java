package de.jprojekt.controller.interfaces;

import de.jprojekt.data.models.User;
import de.jprojekt.utils.BankingException;

public interface IUserController {
    public void update(User user) throws BankingException;

    public void changePassword(User user, String passwordOld, String passwordNew) throws BankingException;

    public boolean isPasswordValid(User user, String password) throws BankingException;
}
