package br.com.duckvault.Services;

import br.com.duckvault.Models.Company;
import br.com.duckvault.Models.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SaveOnFileService {

    public static void saveUsersOnFile(Map<String, User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Lista de usuários.txt"))) {
            for (User user : users.values()) {
                writer.write("Nome: " + user.getName() + ", Email: " + user.getEmail());
                writer.newLine();
            }
            System.out.println("\nArquivo 'Lista de usuários.txt' salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Error on saving files: " + e.getMessage());
        }
    }

    public static void saveCompanyOnFile(Map<String, Company> companies) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Lista de empresas.txt"))) {
            for (Company company : companies.values()) {
                writer.write("Empresa: " + company.getName() + ", Donos: ");
                for (User owner : company.getOwners()) {
                    writer.write(owner.getName() + " <" + owner.getEmail() + ">; ");
                }
                writer.newLine();
            }
            System.out.println("Arquivo 'Lista de empresas.txt' salvo com sucesso !");
        } catch (IOException e) {
            System.err.println("Error on saving files: " + e.getMessage());
        }
    }

}

