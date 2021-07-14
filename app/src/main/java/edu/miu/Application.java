package edu.miu;

import edu.miu.movielens.functions.FunctionUtils;
import edu.miu.movielens.model.Genre;

import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // Top K popular movies in a given year
        //topKMoviesInGivenYear.apply(2, 2016).forEach(System.out::println);
        // most successful director in a specific year
        //mostSuccessfulDirectorInGivenYear.apply(2015).ifPresent(System.out::println);
//        FunctionUtils.topKMoviesByGenreInGivenYear.apply(1, 2016).entrySet().forEach(System.out::println);
        Map<Genre, List<String>> result = FunctionUtils.topKMoviesByGenreInGivenYear.apply(1, 2016);
        for(Map.Entry<Genre, List<String>> entry : result.entrySet()) {
            System.out.println(entry.getKey());
            for(String movieName : entry.getValue()) {
                System.out.println("      " + movieName);
            }
        }
    }
}
