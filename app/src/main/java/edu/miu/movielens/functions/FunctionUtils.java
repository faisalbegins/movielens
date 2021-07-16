package edu.miu.movielens.functions;

import edu.miu.movielens.model.*;
import edu.miu.utils.HelperFunctions;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static edu.miu.utils.StreamConfig.streamOf;

public interface FunctionUtils {
    // Query 1: Top K popular movies in a given year
    TriFunction<Set<Movie>, Integer, Integer, List<String>> topKMoviesInGivenYear =
            (movies, k, year) -> streamOf(movies)
                    .filter(movie -> movie.releaseYear().getYear() == year)
                    .sorted((m1, m2) -> Double.compare(m2.imdbScore(), m1.imdbScore()))
                    .limit(k)
                    .map(Movie::name)
                    .collect(Collectors.toList());

    // Query 2: most successful director in a specific year
    BiFunction<Set<Movie>, Integer, Optional<String>> mostSuccessfulDirectorInGivenYear =
            (movies, year) -> streamOf(movies)
                    .filter(movie -> movie.releaseYear().getYear() == year)
                    .sorted((m1, m2) -> Double.compare(m2.imdbScore(), m1.imdbScore()))
                    .limit(1)
                    .map(m -> m.director().name())
                    .findFirst();

    // Query 3: top k movies from each genre in a given year
    TriFunction<List<MovieGenre>, Integer, Integer, Map<Genre, List<String>>> topKMoviesByGenreInGivenYear =
            (movieGenres, k, year) -> streamOf(movieGenres)
                    .collect(Collectors.groupingBy(MovieGenre::genre))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, (entity) -> FunctionUtils.topKMoveGenreByImdbScore.apply(entity.getValue(), k)))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> HelperFunctions.toMovieNames.apply(e.getValue())));

    // Query 4: top k genre by a given year
    TriFunction<List<MovieGenre>, Integer, Integer, List<String>> topKGenreByYear =
            (movieGenres, k, year) -> streamOf(movieGenres)
                    .collect(Collectors.groupingBy(e -> e.genre().name(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .limit(k)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());


    // Query 5: top k movie genres by imdb score
    BiFunction<List<MovieGenre>, Integer, List<MovieGenre>> topKMoveGenreByImdbScore =
            (movieGenres, k) -> streamOf(movieGenres)
                    .sorted((mg1, mg2) -> Double.compare(mg2.movie().imdbScore(), mg1.movie().imdbScore()))
                    .limit(k)
                    .collect(Collectors.toList());

    // Query 6: top k actor appeared in leading role in a given year
    TriFunction<List<MovieActor>, Integer, Integer, List<String>> topKActorAppearedInLeadingRoleInAGivenYear =
            (movieActors, k, year) ->  streamOf(movieActors)
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

    // Query 7: in a given year how many movies were released by given content rating
    TriFunction<Set<Movie>, Integer, ContentRating, Long> movieCountContentRatingInAGivenYear =
            (movies, year, contentRating) -> streamOf(movies)
                    .filter(m -> m.releaseYear().getYear() == year)
                    .filter(m -> m.rating().equals(contentRating))
                    .count();

    // Query 8: top k actors acted on given content rating in given year
    QuadFunction<List<MovieActor>, ContentRating, Integer, Integer, List<String>> topKActorsForGivenContentRatingInAGivenYear =
            (movieActors, contentRating, k, year) -> streamOf(movieActors)
                    .filter(ma -> ma.movie().releaseYear().getYear() == year)
                    .filter(ma -> ma.movie().rating().equals(contentRating))
                    .map(ma -> ma.actor().name())
                    .limit(k)
                    .collect(Collectors.toList());

    // Query 9: top k lengthy movie from a given year
    TriFunction<Set<Movie>, Integer, Integer, List<String>> topKLengthyMovieInAGivenYear =
            (movies, k, year) -> streamOf(movies)
                    .filter(movie -> movie.releaseYear().getYear() == year)
                    .sorted((m1, m2) -> Integer.compare(m2.duration(), m1.duration()))
                    .limit(k)
                    .map(Movie::name)
                    .collect(Collectors.toList());

    // Query 10: top k director based on awards
    BiFunction<List<MovieAward>, Integer, List<String>> topKDirectorBasedOnAwards =
            (awards, k) -> streamOf(awards)
                    .map(award -> award.movie().director().name())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .map(Map.Entry::getKey)
                    .limit(k)
                    .collect(Collectors.toList());

    // Query 11: top k flop leading actors in a given year
    TriFunction<List<MovieActor>, Integer, Integer, List<String>> topKFlopLeadingActorsInAGivenYear =
            (movieActors, k, year) -> streamOf(movieActors)
                    .filter(MovieActor::leadRole)
                    .filter(movieActor -> movieActor.movie().releaseYear().getYear() == year)
                    .filter(movieActor -> movieActor.movie().imdbScore() < 4)
                    .limit(k)
                    .map(movieActor -> movieActor.actor().name())
                    .collect(Collectors.toList());

    // Query 12: first n movies title that has a specific words
    TriFunction<Set<Movie>, String, Integer, List<String>> firstNMovieTitleContainGivenWord =
            (movies, word, k) -> streamOf(movies)
                    .filter(movie -> movie.name().toUpperCase().contains(word.toUpperCase()))
                    .limit(k)
                    .map(Movie::name)
                    .collect(Collectors.toList());
}
