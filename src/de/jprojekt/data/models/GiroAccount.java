package de.jprojekt.data.models;

public class GiroAccount extends BankAccount {

    public GiroAccount(String name, Customer customer) {
        super(name, customer);
        setMaxDebt(0);
    }
}
