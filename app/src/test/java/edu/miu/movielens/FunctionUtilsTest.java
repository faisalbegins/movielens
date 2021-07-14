package edu.miu.movielens;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static edu.miu.movielens.functions.FunctionUtils.mostSuccessfulDirectorInGivenYear;
import static edu.miu.movielens.functions.FunctionUtils.topKMoviesInGivenYear;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FunctionUtilsTest {
    @Test
    public void top_k_popular_movies_in_a_given_year() {
        List<String> expected = Arrays.asList("Kickboxer: Vengeance", "A Beginner's Guide to Snuff");
        List<String> movies = topKMoviesInGivenYear.apply(2, 2016);
        assertEquals(expected, movies);
    }

    @Test
    public void most_successful_director_in_a_specific_year() {
        String expected = "Mike Mayhall";
        Optional<String> result = mostSuccessfulDirectorInGivenYear.apply(2015);
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }
}
