package br.com.duckvault.Services;

import br.com.duckvault.Models.CryptoAccount;
import br.com.duckvault.Models.Transaction;

public class TransactionService {

    public static boolean transfer(CryptoAccount from, CryptoAccount to, double amount){
        Transaction tx = new Transaction(from, to, amount);
        return tx.execute();
    }

    //Future: I don't think throwing the exception directly in the withdraw and execute method is a good practice.

}
