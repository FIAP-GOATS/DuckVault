package br.com.duckvault;

import br.com.duckvault.Models.User;
import br.com.duckvault.Services.UserService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            UserService service = new UserService();
            boolean running = true;

            while (running) {
                System.out.println("\n===== GERENCIADOR DE USUÁRIOS - DUCKVAULT =====");
                System.out.println("1 - Cadastrar usuário");
                System.out.println("2 - Buscar usuário pelo email");
                System.out.println("3 - Listar usuários");
                System.out.println("4 - Atualizar usuário");
                System.out.println("5 - Deletar usuário");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                int userChoice = scanner.nextInt();
                scanner.nextLine();

                switch (userChoice) {
                    case 1:
                        System.out.println("\n--- Cadastro de Usuário ---");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        System.out.print("2FA Habilitado? (0 - Não, 1 - Sim): ");
                        int twoFactor = scanner.nextInt();
                        scanner.nextLine();


                        User novoUser = new User(nome, email, senha, twoFactor);
                        service.registerUser(novoUser);
                        System.out.println("✅ Usuário cadastrado com sucesso!");
                        break;

                    case 2:
                        System.out.print("\nDigite o email do usuário: ");
                        String userEmail = scanner.next();
                        scanner.nextLine();
                        try {
                            User user = service.searchByName(userEmail);
                            System.out.println("Usuário encontrado: \n");
                            System.out.println("Nome: " + user.getName());
                            System.out.println("Email: " + user.getEmail());

                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.println("\n--- Lista de Usuários ---");
                        List<User> users = service.listUsers();
                        if (users.isEmpty()) {
                            System.out.println("Nenhum usuário encontrado.");
                        } else {
                            for (User u : users) {
                                System.out.println("Nome: " + u.getName());
                                System.out.println("Email: " + u.getEmail());
                                System.out.println();
                            }
                        }
                        break;

                    case 4:
                        System.out.print("\nDigite o email do usuário que você deseja atualizar: ");
                        String originalEmail = scanner.nextLine();
                        try {
                            User uUpdate = service.searchByName(originalEmail);
                            String emailOriginal = uUpdate.getEmail();

                            System.out.print("Novo nome (" + uUpdate.getName() + "): ");
                            String novoNome = scanner.nextLine();
                            if (!novoNome.isEmpty()) uUpdate.setName(novoNome);

                            System.out.print("Novo email (" + uUpdate.getEmail() + "): ");
                            String novoEmail = scanner.nextLine();
                            if (!novoEmail.isEmpty()) uUpdate.setEmail(novoEmail);

                            System.out.print("Nova senha: ");
                            String novaSenha = scanner.nextLine();
                            if (!novaSenha.isEmpty()) uUpdate.setPassword(novaSenha);

                            System.out.print("2FA (atual " + uUpdate.getIsTwoFactorEnabled() + "): ");
                            String novo2fa = scanner.nextLine();
                            if (!novo2fa.isEmpty()) uUpdate.setIsTwoFactorEnabled(Integer.parseInt(novo2fa));

                            service.updateUser(uUpdate, emailOriginal);
                            System.out.println("✅ Usuário atualizado com sucesso!");
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.print("\nDigite o email do usuário que deseja deletar: ");
                        String userToDeleteMail = scanner.next();
                        scanner.nextLine();
                        try {
                            service.deleteUser(userToDeleteMail);
                            System.out.println("Usuário deletado com sucesso!");
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 0:
                        running = false;
                        service.closeConnection();
                        System.out.println("Encerrando o programa...");
                        break;

                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro de conexão com o banco: " + e.getMessage());
        }
    }
}

