package de.jprojekt.controller.mockups;

import de.jprojekt.controller.interfaces.IDBConnector;

public class MockupDBConnector implements IDBConnector{

	@Override
	public void initConnection() {
		// TODO Auto-generated method stub
		System.out.println("Mockup DBConnector inintialized");
	}

}
