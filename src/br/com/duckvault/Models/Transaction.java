package br.com.duckvault.Models;

import br.com.duckvault.Exceptions.InvalidMoneyOperations.InvalidDepositAmount;
import br.com.duckvault.Exceptions.InvalidMoneyOperations.InvalidWithDrawAmount;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private CryptoAccount source; //conta que vai fazer a transferência
    private CryptoAccount destination; //conta que vai receber a transferência
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(){}

    public Transaction(CryptoAccount source, CryptoAccount destination, double amount){
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public boolean execute(){
        try {
            if (source.withdraw(amount)){
                destination.deposit((amount));
                return true;
            }else{
                return false;
            }
        } catch (InvalidWithDrawAmount | InvalidDepositAmount e) {
            throw new RuntimeException(e);
        }
    }

    public String getId() {
        return id;
    }

    public CryptoAccount getSource() {
        return source;
    }

    public void setSource(CryptoAccount source) {
        this.source = source;
    }

    public CryptoAccount getDestination() {
        return destination;
    }

    public void setDestination(CryptoAccount destination) {
        this.destination = destination;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
