package de.jprojekt.data.models;

public class GiroAccount extends BankAccount {

    public GiroAccount(String id, String name, Customer customer) {
        super(id, name, customer);
        setMaxDebt(0);
    }
}
