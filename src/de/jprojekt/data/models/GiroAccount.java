package de.jprojekt.data.models;

public class GiroAccount extends BankAccount {

    public GiroAccount(String name, Customer customer,String uuid) {
        super(name, customer, uuid);
        setMaxDebt(0);
    }
}
