package br.com.duckvault.Exceptions.InvalidCompanyOperations;

public class CryptoAccountAlreadyExists extends Exception{
    
    public CryptoAccountAlreadyExists(String message){
        super(message);
    }

}
