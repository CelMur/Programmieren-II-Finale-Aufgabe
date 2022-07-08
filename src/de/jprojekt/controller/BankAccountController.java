package de.jprojekt.controller;

import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.data.models.BankAccount;
import de.jprojekt.utils.mysql.*;
import de.jprojekt.data.models.*;
import de.jprojekt.utils.BankingException;

public class BankAccountController implements IBankAccountController {

    @Override
    public void changeMaxDebt(BankAccount b, long amount, Employee e) throws BankingException {
        if(amount < 0 ) {
            throw new BankingException("Es ist nur ein positiver Betrag erlaubt.");
        }
        if(b instanceof SavingAccount){
            throw new BankingException("Sie können den maximalen Schuldenbetrag nicht auf ein Sparbuch setzen.");
        }
        try{
        DBAccount.setMaxdebt(b.getName(), amount);
        }catch(Exception ex){
            throw new BankingException("Es ist ein Fehler aufgetreten.");
        }
        b.setMaxDebt(amount);
    }
    // soll ein Benutzer einen Konto erstellen können?
    @Override
    public BankAccount create(Customer user, String name, int type) throws BankingException {
        switch (type) {
            case 0:
            try{
                if(DBAccount.createAccount(user.getId(),name,type) == 1) {
                    throw new BankingException("Dieses Konto existiert bereits.");
                };
                return new DepositAccount(name, user);
            }catch(Exception ex){
                throw new BankingException("Es ist ein Fehler aufgetreten.");
            }
            case 1:
            try{
                if(DBAccount.createAccount(user.getId(),name,type) == 1) {
                    throw new BankingException("Dieses Konto existiert bereits.");
                };
                return new SavingAccount(name, user);
            }catch(Exception ex){
                throw new BankingException("Es ist ein Fehler aufgetreten.");
            }
            case 2:
            try{
                if(DBAccount.createAccount(user.getId(),name,type) == 1) {
                    throw new BankingException("Dieses Konto existiert bereits.");
                };
                return new GiroAccount(name, user);
            }catch(Exception ex){
                throw new BankingException("Es ist ein Fehler aufgetreten.");
            }
        }
        throw new BankingException("Es ist ein Fehler aufgetreten.");
    }   
    /*Erstellen eines neuen Bankkontos für einen Benutzer.
     *@param type 0 = DepositAccount 1 = SavingAccount 2= GiroAccount
     */
    @Override
    public BankAccount create(Customer user, String name, int type, Employee e) throws BankingException {
        if(DB.getUserbyUid() != null) {
            throw new BankingException("Der Benutzer existiert bereits.");
        }
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
    public void delete(Customer c,BankAccount b) throws BankingException {
        if(c == b.getCustomer()){
            try{
            if(DBAccount.deleteAccount(b.getName()) == 1){
                c.getBankAccounts().remove(b);
            }
            }catch(Exception ex){
                throw new BankingException("Es ist ein Fehler aufgetreten.");
            }
        }
        else{
            throw new BankingException("Sie sind nicht berechtigt, dieses Konto zu löschen.");
        }
    }

    @Override
    public void depositMoney(BankAccount b, long amount) throws BankingException {
        if(amount < 0){
            throw new BankingException("Es ist nur ein positiver Betrag erlaubt.");
        }
        long balance = b.getBalance();
        if(Mysql.setBalance(b.getId(), balance + amount) == true){
            b.setBalance(balance + amount);
        }
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
        long srcBalance = Mysql.getBalance(src.getId());
        long targetBalance = Mysql.getBalance(target.getId());
        if(amount < 0 + src.maxDebt){
            throw new BankingException("Es ist nur ein positiver Betrag erlaubt.");
        }
        
    }

    @Override
    public void unlock(BankAccount b) throws BankingException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void withdrawMoney(Customer c, BankAccount b, long amount) throws Exception {
        if(c.getId() == b.getCustomer().getId()){
            if(amount < 0){
                throw new BankingException("Es ist nur ein positiver Betrag erlaubt.");
            }
            long balance = b.getBalance();
            if(balance + b.getMaxDebt() < amount){
                throw new BankingException("Sie haben nicht genug Geld.");
            }
            DBAccount.setBalance(b.getName(), balance-amount);
            b.setBalance(balance-amount);
        }
        else{
            throw new BankingException("Sie sind nicht berechtigt, dieses Konto zu löschen.");
        }
    }
}
