package de.jprojekt.controller.mockups;

import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.data.models.*;
import de.jprojekt.utils.BankingException;

public class MockupBankAccountController implements IBankAccountController {

    @Override
    public void changeMaxDebt(BankAccount b, long amount, Employee e) throws BankingException {
        if(amount < 0 ) {
            throw new BankingException("Es ist nur ein positiver Betrag erlaubt.");
        }
        if(b instanceof SavingAccount){
            throw new BankingException("Sie können den maximalen Schuldenbetrag nicht auf ein Sparbuch setzen.");
        }
        //ggf Datenbank Query oder in setMaxDebt()
        b.setMaxDebt(amount);
    }
    // soll ein Benutzer einen Konto erstellen können?
    @Override
    public BankAccount create(User user, String name, int type) throws BankingException {
        
        return null;
    }

    /*Erstellen eines neuen Bankkontos für einen Benutzer.
     *@param type 0 = DepositAccount 1 = SavingAccount 2= GiroAccount
     */
    @Override
    public BankAccount create(Customer user, String name, int type, Employee e) throws BankingException {
        switch (type) {
            case 0:
                return new DepositAccount(name, user);
            case 1:
                return new SavingAccount(name, user);
            case 2:
                return new GiroAccount(name, user);
            default:
                throw new BankingException("Unbekannte Kontotyp");
        }
    }

    @Override
    public void delete(BankAccount b) throws BankingException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void depositMoney(BankAccount b, long amount) throws BankingException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dissolve(BankAccount b, long amount) throws BankingException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enable(BankAccount b) throws BankingException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void lock(BankAccount b) throws BankingException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void transferMoney(BankAccount src, BankAccount target, long amount) throws BankingException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unlock(BankAccount b) throws BankingException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void withdrawMoney(BankAccount b, long amount) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
