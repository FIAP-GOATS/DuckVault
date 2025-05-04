package br.com.duckvault.Models;

import br.com.duckvault.Exceptions.InvalidMoneyOperations.InvalidDepositAmount;
import br.com.duckvault.Exceptions.InvalidMoneyOperations.InvalidWithDrawAmount;

public class CryptoAccount {
    private String id;
    private String currencyName;
    private double balance; //Saldo
    private Company associatedCompany;

    public CryptoAccount(){}

    public CryptoAccount(String currencyName, Company company) {
        this.currencyName = currencyName;
        this.associatedCompany = company;
    }

    public void deposit(double amount) throws InvalidDepositAmount {
        if (amount >= 0){
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) throws InvalidWithDrawAmount {
        if(amount <=0){
            throw new InvalidWithDrawAmount("Withdraw amount must be greater than zero.");
        }

        if(balance >= amount){
            balance -= amount;
            return true; //withdrawal approved
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Company getAssociatedCompany() {
        return associatedCompany;
    }

    public void setAssociatedCompany(Company associatedCompany) {
        this.associatedCompany = associatedCompany;
    }
}
