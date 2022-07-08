package de.jprojekt.controller.interfaces;

import de.jprojekt.data.models.*;
import de.jprojekt.utils.BankingException;

public interface IBankAccountController {
    public void changeMaxDebt(BankAccount b, long amount, Employee e) throws BankingException;

    public void delete(Customer u, BankAccount b) throws BankingException;

    public BankAccount create(Customer user, String name, int type) throws BankingException;

    public BankAccount create(Customer user, String name, int type, Employee e) throws BankingException;

    public void transferMoney(BankAccount src, BankAccount target, long amount) throws BankingException;

    public void depositMoney(Customer u, BankAccount b, long amount) throws BankingException;

    public void withdrawMoney(Customer c, BankAccount b, long amount) throws Exception;

    public void dissolve(BankAccount b) throws BankingException;

    public void lock(BankAccount b) throws BankingException;

    public void unlock(BankAccount b) throws BankingException;

    public void enable(BankAccount b) throws BankingException;
}
