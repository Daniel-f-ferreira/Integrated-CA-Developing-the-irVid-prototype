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

    // Initialize the variables and read the users already registered
    public Menu() {
        this.isRunning = true;
        this.isLogged = false;
        this.scanner = new Scanner(System.in);
        this.users = FileManager.readUserFromFile();
        this.movies = FileManager.readMoviesFromFile();
        loggedUser = new String();
    }

    //A function that scans the list of all registered users and says whether or not the user exists in the system
    public boolean existUser(String email, String password) {
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    //Centralizing all console output in a single function, so it's simple to change if necessary
    public void print(String phrase) {
        System.out.println(phrase);
    }

    //A function that takes the option that the user has entered. Takes an upper limit as input, and will print an error if it is greater than this limit or less than 1
    public int getUserOption(int limit) {
        int option = 0;

        do {
            try {
                option = scanner.nextInt();

                if (option > limit || option < 1) {
                    print("Enter a valid option");
                } else {
                    break;
                }
            } catch (Exception e) {
                scanner.nextLine();
                print("Enter a valid value");
            }

        } while (true);
        scanner.nextLine();
        return option;
    }

    //A function that just waits for the user to type anything
    public void stopScreen() {
        print("Type any key to continue...");
        scanner.next();
    }

    //This is a function that saves the user in memory and in the file
    public void saveUser(User user) {
        this.users.add(user);
        FileManager.saveUserOnFile(user);
    }

    //When the login option is selected, the email and password will be asked, and if it exists, the login will be performed, otherwise an error message will be displayed    public void menuLoginOptionLogin() {
        print("Enter your email: ");
        String email = scanner.next();

        print("Type the password: ");
        String password = scanner.next();

        if (existUser(email, password)) {
            this.isLogged = true;
            this.loggedUser = email;

        } else {
            print("User not found...");
            stopScreen();
        }
    }

   //This is the function to register a user in the system. If the email already exists, an error message will be sent. Otherwise, the user will be registered in the base
    public void menuLoginOptionSignUp() {
        print("Enter your email: ");
        String email = scanner.next();

        print("Type the password: ");
        String password = scanner.next();

        if (!existUser(email, password)) {

            saveUser(new User(email, password));
            print("User registered successfully!!!");
            stopScreen();

        } else {
            print("User already exists in the system...");
            stopScreen();
        }

    }

    //This is the menu that is the login screen. The user will be given the option of registering and logging in, or leaving the system.
    public void menuLogin() {

        print("Hello welcome!");
        print("Enter an option below: ");
        print("1 - Login");
        print("2 - Register");
        print("3 - Exit");

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

    // This is the function that rented a movie. If the movie is already rented, an error message is displayed to the user;
    public void menuPrincialOption1RentMovie() {

        print("Enter the name of the movie you want to rent: ");
        String movieName = scanner.nextLine();

        for (int i = 0; i < movies.size(); ++i) {

            if (movies.get(i).getTitle().equals(movieName)) {

                for (int j = 0; j < movies.get(i).getRents().size(); ++j) {
                    if (movies.get(i).getRents().get(j).getEmailUserRent().equals(loggedUser)) {
                        print("The movie is already rented!!!!");
                        return;
                    }

                }

                print("Successfully rented movie!!!");
                movies.get(i).getRents().add(new Rent(System.currentTimeMillis(), loggedUser));
                movies.get(i).setTotalRents(movies.get(i).getTotalRents() + 1);
                return;

            }
        }

        print("The movie doesn't exist!!!");

    }

    //This function shows all movies rented by the user
    public void menuPrincialOption2SeeRentMovie() {

        print("Your rented movies: ");
        for (int i = 0; i < movies.size(); ++i) {

            for (int j = 0; j < movies.get(i).getRents().size(); ++j) {
                if (movies.get(i).getRents().get(j).getEmailUserRent().equals(loggedUser)) {
                    print(movies.get(i).getTitle());
                    print("Price: " + movies.get(i).getPrice());

                }

            }

        }

    }

    //This function recommends the 5 most rented movies. Use a function to sort the total rents and then get the top 5
    public void menuPrincialOption3BestMovie() {

        print("Most rented movies: ");
        Collections.sort(this.movies);

        for (int i = movies.size() - 1; i >= 0; --i) {
            if (movies.get(i).getTotalRents() > 0) {
                print(movies.get(i).getTitle());
            }
        }

    }

    //This function shows the movies rented by the user and also returns them if the rental time is greater than 60000 ms or 1 minute
    public void menuPrincialOption4AReturnRentMovie() {

        print("Rented Movies: ");
        for (int i = 0; i < movies.size(); ++i) {

            for (int j = 0; j < movies.get(i).getRents().size(); ++j) {
                if (movies.get(i).getRents().get(j).getEmailUserRent().equals(loggedUser)) {
                    print(movies.get(i).getTitle());
                    print("Price: " + movies.get(i).getPrice());

                }

            }

        }

        print("Returned movies: ");

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
        print("Hello, welcome to our rental company!!!");
        print("To continue, enter one of the options below: ");
        print("1 - Rent a Movie");
        print("2 -View rented movies");
        print("3 - View movie recommendations");
        print("4 - return film");
        print("5 - Exit");
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
