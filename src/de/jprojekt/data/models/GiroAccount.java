package de.jprojekt.data.models;

public class GiroAccount extends BankAccount {

    public GiroAccount(String name, Customer customer,String uuid) {
        super(name, customer, uuid);
        setMaxDebt(0);
    }

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "Girokonto";
	}
}
