package com.locadora.locadora.Utils;

import com.locadora.locadora.Models.BelongToCollection;
import com.locadora.locadora.Models.Genre;
import com.locadora.locadora.Models.Movie;
import com.locadora.locadora.Models.ProductionCompany;
import com.locadora.locadora.Models.ProductionCountry;
import com.locadora.locadora.Models.SpokenLanguage;
import com.locadora.locadora.Models.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {

    //Le os usuarios cadastrados a partir de um arquivo. Qualquer problema na leitura de um arquivo retorna uma lista vazia
    public static LinkedList<User> readUserFromFile() {
        LinkedList<User> users = new LinkedList<User>();

        try {
            FileReader fr = new FileReader(new File("users.csv"));
            BufferedReader br = new BufferedReader(fr);
            String email = new String();
            String password = new String();
            while ((email = br.readLine()) != null) {
                password = br.readLine();
                users.add(new User(email, password));
            }
            br.close();
            fr.close();
        } catch (IOException e) {
        }

        return users;
    }

    public static void saveUserOnFile(User user) {
        try {
            FileWriter fWriter = new FileWriter("users.csv");
            fWriter.write(user.getEmail());
            fWriter.write("\n");
            fWriter.write(user.getPassword());
            fWriter.write("\n");

            fWriter.close();

        } catch (Exception e) {
        }

    }

    public static LinkedList<Movie> readMoviesFromFile() {
        LinkedList<Movie> movies = new LinkedList<Movie>();

        try {
            FileReader fr = new FileReader(new File("movies.csv"));
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                int position = 0;

                boolean adult = values[position++] == "False";

                BelongToCollection belongToColletion = new BelongToCollection();

                if ((!values[position].equals(""))) {

                    int id = Integer.parseInt(values[position++].split(":")[1].trim());
                    String name = values[position++].split(":")[1].trim().replace("\"", "").replace("'", "");

                    while (!values[position].contains("poster_path")) {
                        name = name.concat(values[position++]);
                    }

                    String posterPath = values[position++].split(":")[1].trim().replace("\"", "").replace("'", "").replace("}", "");
                    String backdropPath = values[position++].split(":")[1].trim().replace("\"", "").replace("'", "").replace("}", "");

                    belongToColletion = new BelongToCollection(
                            id, name, posterPath, backdropPath
                    );

                } else {
                    ++position;
                }

                int budget = 0;

                if (values[position].matches("\\d+?")) {
                    budget = Integer.parseInt(values[position++]);
                }

                LinkedList<Genre> genres = new LinkedList<Genre>();

                if (!values[position].equals("[]")) {
                    while (true) {

                        Genre genre = new Genre(
                                Integer.parseInt(values[position++].split(":")[1].trim()),
                                (values[position++].split(":")[1].trim().replace("}", "").replace("\"", "").replace("'", "").replace("]", ""))
                        );
                        genres.add(genre);

                        if (values[position - 1].contains("]")) {
                            break;
                        }

                    }
                }

                String homepage = values[position++];

                while (!values[position].matches("\\d+?")) {
                    homepage = homepage.concat(values[position++]);
                }

                int id = values[position].equals("") ? 0 : Integer.parseInt(values[position++]);
                String imdbId = values[position++];
                String originalLanguage = values[position++];
                String originalTitle = values[position++];

                String overview = values[position++];

                while ((!values[position].contains("e-0") && !values[position].matches("^[1-9]\\d*.\\d*|0.\\d*[0-9]\\d*$")) || ((values[position].equals("500)")) || (values[position].equals("000\"")))) {
                    overview = overview.concat(values[position++]);

                }

                String popularity = values[position++];

                String posterPath = values[position++];
                LinkedList<ProductionCompany> productionCompanies = new LinkedList<ProductionCompany>();

                if (!values[position].equals("[]")) {
                    while (true) {

                        String nameProductionCompany = (values[position++].split(":")[1].trim().replace("}", "").replace("\"", "").replace("'", "").replace("]", ""));

                        while (!values[position].contains("id")) {
                            nameProductionCompany = nameProductionCompany.concat(values[position++]);
                        }

                        int idProductionCompany = Integer.parseInt(values[position++].split(":")[1].trim().replace("}", "").replace("]", "").replace("\"", ""));

                        ProductionCompany productionCompany = new ProductionCompany(
                                nameProductionCompany,
                                idProductionCompany
                        );

                        productionCompanies.add(productionCompany);

                        if (values[position - 1].contains("]")) {
                            break;
                        }

                    }
                } else {
                    ++position;
                }

                LinkedList<ProductionCountry> productionCountries = new LinkedList<ProductionCountry>();

                if (!values[position].equals("[]")) {
                    while (true) {

                        ProductionCountry productionCountry = new ProductionCountry(
                                (values[position++].split(":")[1].trim().replace("}", "").replace("\"", "").replace("'", "").replace("]", "")),
                                (values[position++].split(":")[1].trim().replace("}", "").replace("\"", "").replace("'", "").replace("]", ""))
                        );

                        productionCountries.add(productionCountry);

                        if (values[position - 1].contains("]")) {
                            break;
                        }

                    }
                } else {
                    ++position;
                }

                String releaseDate = values[position++];

                float revenue = Float.parseFloat(values[position++]);
                float runtime = 0.f;

                if (!values[position].equals("")) {
                    runtime = Float.parseFloat(values[position++]);
                } else {
                    ++position;
                }

                LinkedList<SpokenLanguage> spokenLanguages = new LinkedList<SpokenLanguage>();

                if (!values[position].equals("[]")) {
                    while (true) {

                        SpokenLanguage spokenLanguage = new SpokenLanguage(
                                (values[position++].split(":")[1].trim().replace("}", "").replace("\"", "").replace("'", "").replace("]", "")),
                                (values[position++].split(":")[1].trim().replace("}", "").replace("\"", "").replace("'", "").replace("]", ""))
                        );

                        spokenLanguages.add(spokenLanguage);

                        if (values[position - 1].contains("]")) {
                            break;
                        }

                    }
                } else {
                    ++position;
                }

                String status = values[position++];
                String tagline = values[position++];

                while (position < (values.length - 4)) {
                    tagline = tagline.concat(values[position++]);
                }

                String title = values[values.length - 4];
                boolean video = Boolean.parseBoolean(values[values.length - 3]);

                float voteAverage = Float.parseFloat(values[values.length - 2]);
                int voteCount = Integer.parseInt(values[values.length - 1]);

                Movie movie = new Movie(adult, belongToColletion, budget, genres,
                        homepage, id, imdbId, originalLanguage, originalTitle, overview, popularity,
                        posterPath, productionCompanies, productionCountries,
                        releaseDate, revenue, runtime, spokenLanguages, status, tagline,
                        title, video, voteAverage, voteCount);
                movies.add(movie);
            }

            br.close();
            fr.close();
        } catch (IOException e) {
        }

        return movies;
    }

}
