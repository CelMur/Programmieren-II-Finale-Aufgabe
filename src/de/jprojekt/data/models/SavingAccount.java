package de.jprojekt.data.models;

public class SavingAccount extends BankAccount {

    public SavingAccount(String name, Customer customer) {
        super(name, customer);
        setMaxDebt(0);
    }
}

