package de.jprojekt.data.models;

public class DepositAccount extends BankAccount {

    public DepositAccount(String name, Customer customer) {
        super(name, customer);
        setMaxDebt(1000);
    }

}
