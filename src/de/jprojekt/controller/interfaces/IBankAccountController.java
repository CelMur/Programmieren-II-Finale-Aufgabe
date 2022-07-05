package de.jprojekt.controller.interfaces;

import de.jprojekt.data.models.*;
import de.jprojekt.utils.BankingException;

public interface IBankAccountController {
    public void changeMaxDebt(BankAccount b, long amount, Employee e) throws BankingException;

    public void delete(BankAccount b) throws BankingException;

    public BankAccount create(User user, String name, int type) throws BankingException;

    public BankAccount create(Customer user, String name, int type, Employee e) throws BankingException;

    public void transferMoney(BankAccount src, BankAccount target, long amount) throws BankingException;

    public void depositMoney(BankAccount b, long amount) throws BankingException;

    public void withdrawMoney(BankAccount b, long amount) throws Exception;

    public void dissolve(BankAccount b, long amount) throws BankingException;

    public void lock(BankAccount b) throws BankingException;

    public void unlock(BankAccount b) throws BankingException;

    public void enable(BankAccount b) throws BankingException;
}
