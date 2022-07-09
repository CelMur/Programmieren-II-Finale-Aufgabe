package de.jprojekt.data.models;

public class DepositAccount extends BankAccount {

    public DepositAccount(String id, String name, Customer customer) {
        super(id, name, customer);
        setMaxDebt(1000);
    }

}
