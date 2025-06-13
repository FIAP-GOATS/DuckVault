package br.com.duckvault.View;

import br.com.duckvault.Models.Company;
import br.com.duckvault.Models.CryptoAccount;
import br.com.duckvault.Models.Transaction;
import br.com.duckvault.Models.User;
import br.com.duckvault.Services.CompanyService;
import br.com.duckvault.Services.TransactionService;
import br.com.duckvault.Services.UserService;
import br.com.duckvault.Services.SaveOnFileService;

import java.util.*;

public class Main {
    public static void main(String[] args){

        //Try-catch test according to the FIAP's requirements
        try{
            Map<String, User> usersMap = new HashMap<>();
            Map<String, Company> companiesMap = new HashMap<>();

            User user1 = UserService.registerUser("Tom Brady", "tombrady@nfl.com", "admin123");
            User user2 = UserService.registerUser("Elon Musk", "elonmusk@x.com", "admin123");

            usersMap.put(user1.getEmail(), user1);
            usersMap.put(user2.getEmail(), user2);

            System.out.println("Usuários:");
            for (User u : usersMap.values()) {
                System.out.println("nome: " + u.getName() + ", email: " + u.getEmail());
            }

            List<User> owners1 = Arrays.asList(new User("Steve Jobs", "Stevejobs@apple.com", "123"), new User("Steve Wozniak", "stevewozniak@fiap.com", "321"));
            List<User> owners2 = Arrays.asList(new User("Larry Page", "larrypage@gmail.com", "123"), new User("Sergey Brin", "sergey@gmail.com", "321"));

            Company company1 = CompanyService.registerCompany("Apple Inc.", owners1);
            Company company2 = CompanyService.registerCompany("Alphabet Inc.", owners2);

            companiesMap.put(company1.getName(), company1);
            companiesMap.put(company2.getName(), company2);

            System.out.println("\nEmpresas:");
            for (Company c : companiesMap.values()) {
                System.out.print("Nome: " + c.getName() + ", Donos: ");
                List<User> donos = c.getOwners();
                for (int i = 0; i < donos.size(); i++) {
                    User dono = donos.get(i);
                    System.out.print(dono.getName() + "(" + dono.getEmail() + ")");
                    if (i < donos.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }

            CryptoAccount conta1 = new CryptoAccount("ethereum", company1);
            System.out.println("\n");
            conta1.deposit(200);
            System.out.println("Saldo da conta 1 depois do depósito: " + conta1.getBalance());
            conta1.withdraw(25);
            System.out.println("Saldo da conta 1 depois do saque: " + conta1.getBalance());

            CryptoAccount conta2 = new CryptoAccount("ethereum", company2);
            conta2.deposit(150);
            System.out.println("\nSaldo da conta 2 depois do depósito: " + conta2.getBalance());
            conta2.withdraw(50);
            System.out.println("Saldo da conta 2 depois do saque: " + conta2.getBalance());

            boolean transacao1 = TransactionService.transfer(conta1, conta2, 20);
            System.out.println("\nNova transferência: Conta 1 -> 20.00 -> Conta2");
            if(transacao1){
                System.out.println("Transação aprovada");
            }else{
                System.out.println("Transação recusada");
            }

            boolean transacao2 = TransactionService.transfer(conta1, conta2, 350);
            System.out.println("\nNova transferência: Conta 1 -> 350.00 -> Conta2");
            if(transacao2){
                System.out.println("Transação aprovada");
            }else{
                System.out.println("Transação recusada: saldo insuficiente");
            }

            SaveOnFileService.saveUsersOnFile(usersMap);
            SaveOnFileService.saveCompanyOnFile(companiesMap);

        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

