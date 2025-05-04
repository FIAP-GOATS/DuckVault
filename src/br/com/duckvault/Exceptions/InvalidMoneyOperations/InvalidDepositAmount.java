package br.com.duckvault.Exceptions.InvalidMoneyOperations;

public class InvalidDepositAmount extends Exception {

    public InvalidDepositAmount(String message)
    {
        super(message);
    }
}
