package br.com.duckvault.Exceptions.InvalidCompanyOperations;

public class UserAlreadyOwnsThisCompany extends Exception{

    public UserAlreadyOwnsThisCompany(String message){
        super(message);
    }
}

