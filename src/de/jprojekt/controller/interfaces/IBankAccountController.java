package de.jprojekt.controller.interfaces;

import de.jprojekt.data.models.BankAccount;
import de.jprojekt.data.models.User;

public interface IBankAccountController {
    public void changeMaxDebt(BankAccount b, long amount);

    public void delete(BankAccount b);

    public BankAccount create(User user, String name, int type);

    public void transferMoney(BankAccount src, BankAccount target, long amount) throws Exception;

    public void depositMoney(BankAccount b, long amount);

    public void withdrawMoney(BankAccount b, long amount) throws Exception;

    public void dissolve(BankAccount b, long amount);

    public void lock(BankAccount b);

    public void unlock(BankAccount b);

    public void enable(BankAccount b);
}
