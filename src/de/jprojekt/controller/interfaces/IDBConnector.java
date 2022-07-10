package de.jprojekt.controller.interfaces;

import de.jprojekt.utils.BankingException;

public interface IDBConnector {
	public void initConnection() throws BankingException;
}
