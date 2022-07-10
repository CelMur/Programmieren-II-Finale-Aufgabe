package de.jprojekt.controller;

import java.sql.Date;
import java.sql.SQLException;

import de.jprojekt.controller.interfaces.IBankAccountController;
import de.jprojekt.data.models.BankAccount;
import de.jprojekt.utils.mysql.*;
import de.jprojekt.data.models.*;
import de.jprojekt.utils.BankingException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BankAccountController implements IBankAccountController {

    @Override
    public void changeMaxDebt(BankAccount b, long amount, Employee e) throws BankingException {
        if (b instanceof SavingAccount) {
            throw new BankingException("Tagesgeldkonten können nicht überzogen werden.");
        }
        if (amount < 0) {
            throw new BankingException("Es ist nur ein positiver Betrag erlaubt.");
        }
        if (b instanceof SavingAccount) {
            throw new BankingException("Sie können den maximalen Schuldenbetrag nicht auf ein Sparbuch setzen.");
        }
        try {
            DBAccount.setMaxdebt(b.getName(), amount);
        } catch (Exception ex) {
            throw new BankingException("Es ist ein Fehler aufgetreten.");
        }
        b.setMaxDebt(amount);
    }

    // soll ein Benutzer einen Konto erstellen können?
    @Override
    public BankAccount create(Customer user, String name, int type) throws BankingException {
        switch (type) {
            case 0:
                try {
                    String uuid = DBAccount.createAccount(user.getId(), name, type);
                    if (uuid == null) {
                        throw new BankingException("Dieses Konto existiert bereits.");
                    }
                    return new DepositAccount(name, user, uuid);
                } catch (Exception ex) {
                    throw new BankingException("Es ist ein Fehler aufgetreten.");
                }
            case 1:
                try {
                    String uuid = DBAccount.createAccount(user.getId(), name, type);
                    if (uuid == null) {
                        throw new BankingException("Dieses Konto existiert bereits.");
                    }
                    return new SavingAccount(name, user, uuid);
                } catch (Exception ex) {
                    throw new BankingException("Es ist ein Fehler aufgetreten.");
                }
            case 2:
                try {
                    String uuid = DBAccount.createAccount(user.getId(), name, type);
                    if (uuid == null) {
                        throw new BankingException("Dieses Konto existiert bereits.");
                    }
                    return new GiroAccount(name, user, uuid);
                } catch (Exception ex) {
                    throw new BankingException("Es ist ein Fehler aufgetreten.");
                }
        }
        throw new BankingException("Es ist ein Fehler aufgetreten.");
    }

    /*
     * Erstellen eines neuen Bankkontos für einen Benutzer.
     * 
     * @param type 0 = DepositAccount 1 = SavingAccount 2= GiroAccount
     */
    @Override
    public BankAccount create(Customer user, String name, int type, Employee e) throws BankingException {

        try {
            if (DBUser.exists(user.getId()) != false) {
                throw new BankingException("Der Benutzer existiert nicht.");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        switch (type) {
            case 0:
                try {
                    String uuid = DBAccount.createAccount(user.getId(), name, type);
                    if (uuid == null) {
                        throw new BankingException("Dieses Konto existiert bereits.");
                    }
                    DepositAccount tmp = new DepositAccount(name, user, uuid);
                    tmp.setLocked(false);
                    return tmp;
                } catch (Exception ex) {
                    throw new BankingException("Es ist ein Fehler aufgetreten.");
                }
            case 1:
                try {
                    String uuid = DBAccount.createAccount(user.getId(), name, type);
                    if (uuid == null) {
                        throw new BankingException("Dieses Konto existiert bereits.");
                    }
                    SavingAccount tmp = new SavingAccount(name, user, uuid);
                    tmp.setLocked(false);
                    return tmp;

                } catch (Exception ex) {
                    throw new BankingException("Es ist ein Fehler aufgetreten.");
                }
            case 2:
                try {
                    String uuid = DBAccount.createAccount(user.getId(), name, type);
                    if (uuid == null) {
                        throw new BankingException("Dieses Konto existiert bereits.");
                    }
                    GiroAccount tmp = new GiroAccount(name, user, uuid);
                    tmp.setLocked(false);
                    return tmp;

                } catch (Exception ex) {
                    throw new BankingException("Es ist ein Fehler aufgetreten.");
                }
            default:
                throw new BankingException("Unbekannter Kontotyp");
        }
    }

    @Override
    public void delete(Customer c, BankAccount b) throws BankingException {
        if (c == b.getCustomer()) {
            try {
                if (DBAccount.deleteAccount(b.getName()) == 1) {
                    c.getBankAccounts().remove(b);
                    DBCustomer.removeAccount(c.getId(), b.getName());
                }
            } catch (Exception ex) {
                throw new BankingException("Es ist ein Fehler aufgetreten.");
            }
        } else {
            throw new BankingException("Sie sind nicht berechtigt, dieses Konto zu löschen.");
        }
    }

    @Override
    public void depositMoney(Customer c, BankAccount b, long amount) throws BankingException {
        if (amount < 0) {
            throw new BankingException("Es ist nur ein positiver Betrag erlaubt.");
        }
        long balance = b.getBalance();
        try {
            if (DBAccount.setBalance(b.getName(), amount + balance) == 1) {
                balance += amount;
                b.setBalance(balance);
            }
        } catch (Exception ex) {
            throw new BankingException("Es ist ein Fehler aufgetreten.");
        }
    }

    @Override
    public void dissolve(BankAccount b) throws BankingException {
        if (b.getBalance() != 0) {
            throw new BankingException("Das Konto hat noch einen Restbetrag.");
        }
        try {
            if (DBAccount.deleteAccount(b.getName()) == 1) {
                b.getCustomer().getBankAccounts().remove(b);
            }
            b = null;
        } catch (Exception ex) {
            throw new BankingException("Es ist ein Fehler aufgetreten.");
        }

    }

    @Override
    public void enable(BankAccount b) throws BankingException {
        try {
            if (DBAccount.setLocked(b.getName(), 0) == 1) {
                b.setLocked(true);
            }
        } catch (Exception ex) {
            throw new BankingException("Es ist ein Fehler aufgetreten.");
        }

    }

    @Override
    public void lock(BankAccount b) throws BankingException {
        try {
            if (DBAccount.setLocked(b.getName(), 1) == 1) {
                b.setLocked(false);
            }
        } catch (Exception ex) {
            throw new BankingException("Es ist ein Fehler aufgetreten.");
        }
    }

    @Override
    public void transferMoney(BankAccount src, BankAccount target, long amount) throws BankingException {
        long srcBalance = src.getBalance();
        long targetBalance = target.getBalance();
        if (srcBalance + src.getMaxDebt() < amount) {
            throw new BankingException("Sie haben nicht genug Geld.");
        }
        if (amount < 0) {
            throw new BankingException("Es ist nur ein positiver Betrag erlaubt.");
        }

        if (src instanceof SavingAccount) {
            if (target.getCustomer() != src.getCustomer()) {
                throw new BankingException("Tagesgeldkonten können nur an Konten des selben Nutzer überweisen.");
            }
        }

        if (src instanceof DepositAccount || target instanceof DepositAccount) {
            if (src.getCustomer() != target.getCustomer()) {
                throw new BankingException("Aktiendepots sind vom regulären Zahlungsverkehr ausgeschlossen.");
            }
        }

        try {
            if (DBAccount.setBalance(src.getId(), srcBalance - amount) == 1
                    && DBAccount.setBalance(target.getId(), targetBalance + amount) == 1) {
                srcBalance -= amount;
                targetBalance += amount;
                src.setBalance(srcBalance);
                target.setBalance(targetBalance);
                LocalDate now = LocalDateTime.now().toLocalDate();
                java.sql.Date date = java.sql.Date.valueOf(now);
                DBTransactions.createTransaction(src.getName(), target.getName(), amount, date);
            }
        } catch (Exception ex) {
            throw new BankingException("Es ist ein Fehler aufgetreten.");
        }

    }

    @Override
    public void unlock(BankAccount b) throws BankingException {
        try {
            if (DBAccount.setLocked(b.getName(), 0) == 1) {
                b.setLocked(true);
            }
        } catch (Exception ex) {
            throw new BankingException("Es ist ein Fehler aufgetreten.");
        }

    }

    @Override
    public void withdrawMoney(Customer c, BankAccount b, long amount) throws BankingException {
        if (c.getId() != b.getCustomer().getId()) {
            throw new BankingException("Sie sind nicht berechtigt, dieses Konto zu löschen.");
        }
        if (amount < 0) {
            throw new BankingException("Es ist nur ein positiver Betrag erlaubt.");
        }
        long balance = b.getBalance();
        if (balance + b.getMaxDebt() < amount) {
            throw new BankingException("Sie haben nicht genug Geld und können sich nicht (weiter) verschulden.");
        }
        try {
            DBAccount.setBalance(b.getName(), balance - amount);
        } catch (Exception ex) {
            throw new BankingException("Fehler bei Kommunikation mit der Datenbank.");
        }
        b.setBalance(balance - amount);
    }
    
    public BankAccount getAccountByID(String kontoID) throws BankingException {
        try {
            return DBAccount.getAccount(kontoID);
        } catch (Exception ex) {
            throw new BankingException("Fehler bei Kommunikation mit der Datenbank.");
        }
    }
}
