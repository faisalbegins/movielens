package edu.miu.utils;

import edu.miu.movielens.model.MovieGenre;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface HelperFunctions {
    Function<List<MovieGenre>, List<String>> toMovieNames =
            (movieGenres) -> movieGenres.stream()
                    .map(g -> g.movie().name())
                    .collect(Collectors.toList());
}
