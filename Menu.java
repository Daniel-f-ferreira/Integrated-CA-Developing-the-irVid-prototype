package com.locadora.locadora;

import com.locadora.locadora.Models.Movie;
import com.locadora.locadora.Models.Rent;
import com.locadora.locadora.Models.User;
import com.locadora.locadora.Utils.FileManager;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu {

    private boolean isRunning;
    private boolean isLogged;
    private Scanner scanner;
    private LinkedList<User> users;
    private LinkedList<Movie> movies;
    private String loggedUser;

    //Inicializa as variáveis e ler os usuarios já cadastrados
    public Menu() {
        this.isRunning = true;
        this.isLogged = false;
        this.scanner = new Scanner(System.in);
        this.users = FileManager.readUserFromFile();
        this.movies = FileManager.readMoviesFromFile();
        loggedUser = new String();
    }

    //Uma função que varre a lista de todos os usuarios cadastrados e diz se existe ou não o usuario no sistema
    public boolean existUser(String email, String password) {
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    //Centralizando toda saída do console em uma única função, assim fica simples mudar caso seja necessário
    public void print(String phrase) {
        System.out.println(phrase);
    }

    //Uma função que pega a opção que o usuário digitou. Recebe como entrada um limite superior, e irá printar erro se for maior que esse limite ou menor que 1
    public int getUserOption(int limit) {
        int option = 0;

        do {
            try {
                option = scanner.nextInt();

                if (option > limit || option < 1) {
                    print("Digite uma opção válida");
                } else {
                    break;
                }
            } catch (Exception e) {
                scanner.nextLine();
                print("Digite um valor valido");
            }

        } while (true);
        scanner.nextLine();
        return option;
    }

    //Uma função que apenas espera o usuário digitar qualquer coisa
    public void stopScreen() {
        print("Digite qualquer tecla para continuar...");
        scanner.next();
    }

    //Esta e uma funcao que salva o usuario na memoria e no arquivo
    public void saveUser(User user) {
        this.users.add(user);
        FileManager.saveUserOnFile(user);
    }

    //Quando a opção login for selecionada, será pedido o email e senha, e caso existe, sera feito o login, senao sera mostrada uma mensagem de erro
    public void menuLoginOptionLogin() {
        print("Digite o seu email: ");
        String email = scanner.next();

        print("Digite a senha: ");
        String password = scanner.next();

        if (existUser(email, password)) {
            this.isLogged = true;
            this.loggedUser = email;

        } else {
            print("Usuario não encontrado...");
            stopScreen();
        }
    }

    //Essa e a funcao de cadastrar um usuario no sistema. Se o email ja existir, sera mandada uma mensagem de erro. Senao o usuario sera cadastrado na base
    public void menuLoginOptionSignUp() {
        print("Digite o seu email: ");
        String email = scanner.next();

        print("Digite a senha: ");
        String password = scanner.next();

        if (!existUser(email, password)) {

            saveUser(new User(email, password));
            print("Usuario cadastrado com sucesso!!!");
            stopScreen();

        } else {
            print("Usuario ja existe no sistema...");
            stopScreen();
        }

    }

    //Esse e o menu que esta a tela de login. Sera dada a opcao ao usuario de cadastro e login, ou sair do sistema
    public void menuLogin() {

        print("Olá, seja bem vindo!");
        print("Digite uma opção abaixo: ");
        print("1 - Login");
        print("2 - Cadastrar");
        print("3 - Sair");

        int option = getUserOption(3);

        switch (option) {

            case 1:
                menuLoginOptionLogin();
                break;

            case 2:
                menuLoginOptionSignUp();
                break;

            case 3:
                this.isRunning = false;
                break;
        }
    }

    //Essa é a função que aluga um filme. Se o filme já estiver alugado, é soltado uma mensagem de erro para o usuário;
    public void menuPrincialOption1RentMovie() {

        print("Digite o nome  do filme que voce deseja alugar: ");
        String movieName = scanner.nextLine();

        for (int i = 0; i < movies.size(); ++i) {

            if (movies.get(i).getTitle().equals(movieName)) {

                for (int j = 0; j < movies.get(i).getRents().size(); ++j) {
                    if (movies.get(i).getRents().get(j).getEmailUserRent().equals(loggedUser)) {
                        print("O filme já está alugado!!!!");
                        return;
                    }

                }

                print("Filme alugado com sucesso!!!");
                movies.get(i).getRents().add(new Rent(System.currentTimeMillis(), loggedUser));
                movies.get(i).setTotalRents(movies.get(i).getTotalRents() + 1);
                return;

            }
        }

        print("O filme não existe!!!");

    }

    //Essa função mostra todos os filmes alugados pelo usuário
    public void menuPrincialOption2SeeRentMovie() {

        print("Seus filmes alugados: ");
        for (int i = 0; i < movies.size(); ++i) {

            for (int j = 0; j < movies.get(i).getRents().size(); ++j) {
                if (movies.get(i).getRents().get(j).getEmailUserRent().equals(loggedUser)) {
                    print(movies.get(i).getTitle());
                    print("Preco: " + movies.get(i).getPrice());

                }

            }

        }

    }

    //Essa função faz a recomendação dos 5 filmes mais alugados. Usa uma função para ordenar o total de alugueis e depois pega os 5 primeiros
    public void menuPrincialOption3BestMovie() {

        print("Filmes mais alugados: ");
        Collections.sort(this.movies);

        for (int i = movies.size() - 1; i >= 0; --i) {
            if (movies.get(i).getTotalRents() > 0) {
                print(movies.get(i).getTitle());
            }
        }

    }

    //Essa função mostra os filmes alugados pelo usuário e também faz a devolução caso o tempo de aluguel seja maior que 60000 ms ou 1 minuto
    public void menuPrincialOption4AReturnRentMovie() {

        print("Filmes alugados: ");
        for (int i = 0; i < movies.size(); ++i) {

            for (int j = 0; j < movies.get(i).getRents().size(); ++j) {
                if (movies.get(i).getRents().get(j).getEmailUserRent().equals(loggedUser)) {
                    print(movies.get(i).getTitle());
                    print("Preco: " + movies.get(i).getPrice());

                }

            }

        }

        print("Filmes devolvidos: ");

        for (int i = 0; i < movies.size(); ++i) {

            for (int j = 0; j < movies.get(i).getRents().size(); ++j) {
                if (movies.get(i).getRents().get(j).getEmailUserRent().equals(loggedUser) && ((System.currentTimeMillis() - movies.get(i).getRents().get(j).getTimeRent() > 60000))) {
                    print(movies.get(i).getTitle());
                    movies.get(i).getRents().remove(movies.get(i).getRents().get(j));
                }

            }

        }
    }

    public void menuPrincipal() {
        print("Ola, seja bem vindo a nossa Locadora!!!");
        print("Para continuar, digite uma das opcoes abaixo: ");
        print("1 - Alugar um filme");
        print("2 - Ver os filmes alugados");
        print("3 - Ver recomendações de filme");
        print("4 - Devolver filme");
        print("5 - Sair");
        int option = getUserOption(5);

        switch (option) {
            case 1:
                menuPrincialOption1RentMovie();
                break;
            case 2:
                menuPrincialOption2SeeRentMovie();
                break;
            case 3:
                menuPrincialOption3BestMovie();
                break;
            case 4:
                menuPrincialOption4AReturnRentMovie();
                break;

            case 5:
                isRunning = false;
                break;
        }

    }

    public void start() {
        while (isRunning) {
            if (!isLogged) {
                menuLogin();
            } else {
                menuPrincipal();
            }

            System.out.flush();
        }
    }

}
