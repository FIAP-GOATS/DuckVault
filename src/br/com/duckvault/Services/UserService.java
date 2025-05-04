package br.com.duckvault.Services;

import br.com.duckvault.Models.User;

public class UserService {

    public static User registerUser(String name, String email, String password){
        User user = new User(name, email, password);
        // future: save user on the db
        return user;
    }

    //future: add authentication logic here

}
