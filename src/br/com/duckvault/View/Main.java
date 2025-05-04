package br.com.duckvault.View;

import br.com.duckvault.Models.Company;
import br.com.duckvault.Models.CryptoAccount;
import br.com.duckvault.Models.Transaction;
import br.com.duckvault.Models.User;
import br.com.duckvault.Services.CompanyService;
import br.com.duckvault.Services.TransactionService;
import br.com.duckvault.Services.UserService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        //Try-catch test according to the FIAP's requirements
        try{
            User user1 = UserService.registerUser("Zezinho", "Zezinho@fiap.com", "zezinho123");
            User user2 = UserService.registerUser("Pedrinho", "Pedrinho@fiap.com", "pedrinho123");
            System.out.println("Usuários:");
            System.out.println("nome: " + user1.getName() + ", email: " + user1.getEmail());
            System.out.println("nome: " + user2.getName() + ", email: " + user2.getEmail());

            List<User> owners1 = new ArrayList<>();
            owners1.add(new User("Aurelio", "aurelio@fiap.com", "123"));
            owners1.add(new User("Nayara", "nayara@fiap.com", "321"));
            Company company1 = CompanyService.registerCompany("madereira du norte", owners1);

            List<User> owners2 = new ArrayList<>();
            owners2.add(new User("Luna", "luna@fiap.com", "123"));
            owners2.add(new User("Leo", "leo@fiap.com", "321"));
            Company company2 = CompanyService.registerCompany("Padaria Casa do Pão", owners2);

            System.out.println("\nEmpresas:");
            System.out.println("Nome da empresa: " + company1.getName() + ", lista de donos: " + company1.getOwners());
            System.out.println("Nome da empresa: " + company2.getName() + ", lista de donos: " + company2.getOwners());

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

        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

