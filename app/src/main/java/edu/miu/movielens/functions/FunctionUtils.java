package edu.miu.movielens.functions;

import edu.miu.movielens.model.Genre;
import edu.miu.movielens.model.Movie;
import edu.miu.movielens.model.MovieGenre;
import edu.miu.utils.PseudoDatabase;

import java.util.List;
import java.util.Map;
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

    // top k movies from each genre in a given year
    BiFunction<Integer, Integer, Map<Genre, List<String>>> topKMoviesByGenreInGivenYear =
            (k, year) -> database.getMovieGenres().stream()
                    .collect(Collectors.groupingBy(MovieGenre::genre))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, (entity) -> FunctionUtils.sortedKMoveGenreByImdbScore.apply(entity.getValue(), k)))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> FunctionUtils.toMovieNames.apply(e.getValue())));

    // helper functions
    BiFunction<List<MovieGenre>, Integer, List<MovieGenre>> sortedKMoveGenreByImdbScore =
            (movieGenres, k) -> movieGenres.stream()
                    .sorted((mg1, mg2) -> Double.compare(mg2.movie().imdbScore(), mg1.movie().imdbScore()))
                    .limit(k)
                    .collect(Collectors.toList());

    Function<List<MovieGenre>, List<String>> toMovieNames =
            (movieGenres) -> movieGenres.stream()
                    .map(g -> g.movie().name())
                    .collect(Collectors.toList());

}
