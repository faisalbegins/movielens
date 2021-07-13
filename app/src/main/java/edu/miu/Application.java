package edu.miu;

import edu.miu.movielens.model.Movie;
import edu.miu.utils.PseudoDatabase;

import java.util.Set;

public class Application {
    public static void main(String[] args) {
        PseudoDatabase database = PseudoDatabase.getInstance();
        Set<Movie> movies = database.getMovies();
        movies.forEach(System.out::println);
        System.out.println(movies.size());
    }
}
