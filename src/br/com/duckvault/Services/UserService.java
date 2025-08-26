package br.com.duckvault.Services;

import br.com.duckvault.Factory.ConnectionFactory;
import br.com.duckvault.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private Connection dbConnection;

    public UserService() throws SQLException{
        dbConnection = ConnectionFactory.getConnection();
    }

    public void closeConnection() throws SQLException{
        dbConnection.close();
    }

    private User parseUser(ResultSet result) throws SQLException{
        String name = result.getString("name");
        String email = result.getString("email");
        String password = result.getString("password");
        int isTwoFactorEnabled = result.getInt("istwofactorenabled");
        return new User(name, email, password, isTwoFactorEnabled);
    }

    public void registerUser(User user) throws SQLException{
        PreparedStatement stm = dbConnection.prepareStatement("INSERT INTO users (name, email, password, istwofactorenabled, createdat) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)");
        stm.setString(1, user.getName());
        stm.setString(2, user.getEmail());
        stm.setString(3, user.getPassword());
        stm.setInt(4, user.getIsTwoFactorEnabled());
        stm.executeUpdate();
    }

    public User searchByName(String email) throws SQLException{
        PreparedStatement stm = dbConnection.prepareStatement("SELECT * FROM users WHERE email = ?");
        stm.setString(1, email);
        ResultSet result = stm.executeQuery();
        if(!result.next()){
            throw new SQLException("User not found");
        }
        return parseUser(result);
    }

    public List<User> listUsers() throws SQLException{
        PreparedStatement stm = dbConnection.prepareStatement("SELECT * FROM users");
        ResultSet result = stm.executeQuery();
        List<User> users = new ArrayList<>();
        while(result.next()){
            users.add(parseUser(result));
        }
        return users;
    }

    public void updateUser(User user, String originalEmail) throws SQLException {
        PreparedStatement stm = dbConnection.prepareStatement(
                "UPDATE users SET name=?, email=?, password=?, istwofactorenabled=? WHERE email=?"
        );
        stm.setString(1, user.getName());
        stm.setString(2, user.getEmail());
        stm.setString(3, user.getPassword());
        stm.setInt(4, user.getIsTwoFactorEnabled());
        stm.setString(5, originalEmail);
        stm.executeUpdate();
    }

    public void deleteUser(String email) throws SQLException{
        PreparedStatement stm = dbConnection.prepareStatement("DELETE FROM users WHERE email = ?");
        stm.setString(1, email);
        int row = stm.executeUpdate();
        if(row == 0){
            throw new SQLException("User not found");
        }
    }

}
