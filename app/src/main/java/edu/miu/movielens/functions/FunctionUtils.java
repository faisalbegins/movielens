package edu.miu.movielens.functions;

import edu.miu.movielens.model.Genre;
import edu.miu.movielens.model.Movie;
import edu.miu.movielens.model.MovieActor;
import edu.miu.movielens.model.MovieGenre;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface FunctionUtils {
    // Query 1: Top K popular movies in a given year
    TriFunction<Set<Movie>, Integer, Integer, List<String>> topKMoviesInGivenYear =
            (movies, k, year) -> movies.stream()
                    .filter(movie -> movie.releaseYear().getYear() == year)
                    .sorted((m1, m2) -> Double.compare(m2.imdbScore(), m1.imdbScore()))
                    .limit(k)
                    .map(Movie::name)
                    .collect(Collectors.toList());

    // Query 2: most successful director in a specific year
    BiFunction<Set<Movie>, Integer, Optional<String>> mostSuccessfulDirectorInGivenYear =
            (movies, year) -> movies.stream()
                    .filter(movie -> movie.releaseYear().getYear() == year)
                    .sorted((m1, m2) -> Double.compare(m2.imdbScore(), m1.imdbScore()))
                    .limit(1)
                    .map(m -> m.director().name())
                    .findFirst();

    // Query 3: top k movies from each genre in a given year
    TriFunction<List<MovieGenre>, Integer, Integer, Map<Genre, List<String>>> topKMoviesByGenreInGivenYear =
            (movieGenres, k, year) -> movieGenres.stream()
                    .collect(Collectors.groupingBy(MovieGenre::genre))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, (entity) -> FunctionUtils.topKMoveGenreByImdbScore.apply(entity.getValue(), k)))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> FunctionUtils.toMovieNames.apply(e.getValue())));

    // Query 4: top k genre by a given year
    TriFunction<List<MovieGenre>, Integer, Integer, List<String>> topKGenreByYear =
            (movieGenres, k, year) -> movieGenres.stream()
                    .collect(Collectors.groupingBy(e -> e.genre().name(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .limit(k)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());


    // Query 5: top k movie genres by imdb score
    BiFunction<List<MovieGenre>, Integer, List<MovieGenre>> topKMoveGenreByImdbScore =
            (movieGenres, k) -> movieGenres.stream()
                    .sorted((mg1, mg2) -> Double.compare(mg2.movie().imdbScore(), mg1.movie().imdbScore()))
                    .limit(k)
                    .collect(Collectors.toList());

    // Query 6: top k actor appeared in leading role in a given year
    TriFunction<List<MovieActor>, Integer, Integer, List<String>> topKActorAppearedInLeadingRoleInAGivenYear =
            (movieActors, k, year) ->  movieActors.stream()
                    .filter(ma -> ma.movie().releaseYear().getYear() == year)
                    .filter(MovieActor::leadRole)
                    .map(m -> m.actor().name())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .map(Map.Entry::getKey)
                    .limit(k)
                    .collect(Collectors.toList());

    Function<List<MovieGenre>, List<String>> toMovieNames =
            (movieGenres) -> movieGenres.stream()
                    .map(g -> g.movie().name())
                    .collect(Collectors.toList());

}
