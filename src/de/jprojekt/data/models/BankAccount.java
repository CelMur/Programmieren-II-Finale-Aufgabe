package de.jprojekt.data.models;

public abstract class BankAccount {
	
	
	public enum BankAccountType{
		
		GIRO(2),
		SAVING(1),
		DEPOSIT(0);
		
		
		private final int value;
		
		BankAccountType(int value) {
			this.value = value;
		}
		
		public int value(){return value;}
	}
	
	//name = kontonummer
	private String name;
	private String id;
	private long balance;
	private long maxDebt;
	private Customer customer;
	private boolean locked;

	public static final int TYPE_GIRO = 2;
	public static final int TYPE_SAVING = 1;
	public static final int TYPE_DEPOSIT = 0;

	public BankAccount(String name, Customer customer, String uuid) {
		this.id = uuid;
		this.name = name;
		this.customer = customer;
		customer.addBankAccount(this);
		this.locked = true;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBalance() {
		return this.balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public long getMaxDebt() {
		return this.maxDebt;
	}

	public void setMaxDebt(long maxDebt) {
		this.maxDebt = maxDebt;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isLocked() {
		return this.locked;
	}

	public boolean getLocked() {
		return this.locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public abstract String getTypeName();

}
