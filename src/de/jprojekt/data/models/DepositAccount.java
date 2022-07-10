package de.jprojekt.data.models;

public class DepositAccount extends BankAccount {

    public DepositAccount(String name, Customer customer, String uuid) {
        super(name, customer, uuid);
        setMaxDebt(1000);
    }

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "Depot";
	}
    
    

}
