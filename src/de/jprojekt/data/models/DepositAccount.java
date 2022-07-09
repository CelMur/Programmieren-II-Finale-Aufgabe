package de.jprojekt.data.models;

public class DepositAccount extends BankAccount {

    public DepositAccount(String name, Customer customer, String uuid) {
        super(name, customer, uuid);
        setMaxDebt(1000);
    }

}
