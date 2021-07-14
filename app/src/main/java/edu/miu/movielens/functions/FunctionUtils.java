package edu.miu.movielens.functions;

import edu.miu.movielens.model.Movie;
import edu.miu.utils.PseudoDatabase;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface FunctionUtils {
    // instantiate database
    PseudoDatabase database = PseudoDatabase.getInstance();

    // Top K popular movies in a given year
    BiFunction<Integer, Integer, List<String>> topKMoviesInGivenYear =
            /**
             * @param  k                number of movies we want to show
             * @param  year             specific year
             * @return List<String>     List of movie names
             */
            (k, year) -> database.getMovies().stream()
                    .filter(movie -> movie.releaseYear().getYear() == year)
                    .sorted((m1, m2) -> Double.compare(m2.imdbScore(), m1.imdbScore()))
                    .limit(k)
                    .map(Movie::name)
                    .collect(Collectors.toList());

    // most successful director in a specific year
    Function<Integer, Optional<String>> mostSuccessfulDirectorInGivenYear =
            (year) -> database.getMovies().stream()
                    .filter(movie -> movie.releaseYear().getYear() == year)
                    .sorted((m1, m2) -> Double.compare(m2.imdbScore(), m1.imdbScore()))
                    .limit(1)
                    .map(m -> m.director().name())
                    .findFirst();
}
