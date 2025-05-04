package br.com.duckvault.Services;

import br.com.duckvault.Exceptions.InvalidMoneyOperations.InvalidDepositAmount;
import br.com.duckvault.Exceptions.InvalidMoneyOperations.InvalidWithDrawAmount;
import br.com.duckvault.Models.CryptoAccount;

public class CryptoAccountService {

    public void deposit(CryptoAccount account, double amount){
        try {
            account.deposit(amount);
        } catch (InvalidDepositAmount e) {
            throw new RuntimeException(e);
        }
    }

    public boolean withdraw (CryptoAccount account, double amount){
        try {
            return account.withdraw(amount);
        } catch (InvalidWithDrawAmount e) {
            throw new RuntimeException(e);
        }
    }

    //Future: updating the amount on db
}
