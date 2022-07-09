package de.jprojekt.data.models;

public class SavingAccount extends BankAccount {

    public SavingAccount(String id, String name, Customer customer) {
        super(id, name, customer);
        setMaxDebt(0);
    }
}

