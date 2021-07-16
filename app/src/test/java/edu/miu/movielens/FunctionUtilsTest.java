package edu.miu.movielens;

import edu.miu.movielens.functions.FunctionUtils;
import edu.miu.movielens.model.ContentRating;
import edu.miu.movielens.model.Genre;
import edu.miu.movielens.model.Tuple;
import edu.miu.utils.PseudoDatabase;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static edu.miu.movielens.functions.FunctionUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FunctionUtilsTest {
    private PseudoDatabase database;

    @Before
    public void init() {
        this.database = PseudoDatabase.getInstance();
    }

    @Test
    public void top_k_popular_movies_in_a_given_year() {
        List<String> expected = Arrays.asList("Kickboxer: Vengeance", "A Beginner's Guide to Snuff");
        List<String> movies = topKMoviesInGivenYear
                .apply(database.getMovies(), 2, 2016);
        assertEquals(expected, movies);
    }

    @Test
    public void most_successful_director_in_a_specific_year() {
        String expected = "Mike Mayhall";
        Optional<String> result = mostSuccessfulDirectorInGivenYear
                .apply(database.getMovies(), 2015);
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    public void top_k_movies_from_each_genre_in_a_given_year() {
        Map<Genre, List<String>> expected = new HashMap<>();
        expected.put(Genre.valueOf("WAR"), Arrays.asList("Casablanca"));
        expected.put(Genre.valueOf("ACTION"), Arrays.asList("Kickboxer: Vengeanc"));
        expected.put(Genre.valueOf("HISTORY"), Arrays.asList("Schindler's List"));
        expected.put(Genre.valueOf("HISTORY"), Arrays.asList("Schindler's List"));
        expected.put(Genre.valueOf("COMEDY"), Arrays.asList("Forrest Gump"));
        expected.put(Genre.valueOf("ADVENTURE"), Arrays.asList("The Lord of the Rings: The Return of the King"));
        expected.put(Genre.valueOf("THRILLER"), Arrays.asList("The Dark Knight"));
        expected.put(Genre.valueOf("ANIMATION"), Arrays.asList("Spirited Away"));
        expected.put(Genre.valueOf("DOCUMENTARY"), Arrays.asList("Butterfly Girl"));
        expected.put(Genre.valueOf("MUSICAL"), Arrays.asList("The Lion King"));
        expected.put(Genre.valueOf("SCI_FI"), Arrays.asList("Star Wars: Episode V - The Empire Strikes Back"));
        expected.put(Genre.valueOf("MYSTERY"), Arrays.asList("Se7en"));
        expected.put(Genre.valueOf("MUSIC"), Arrays.asList("Samsara"));
        expected.put(Genre.valueOf("FILM_NOIR"), Arrays.asList("Rebecca"));
        expected.put(Genre.valueOf("FAMILY"), Arrays.asList("Modern Times"));
        expected.put(Genre.valueOf("NEWS"), Arrays.asList("The Square"));
        expected.put(Genre.valueOf("SPORT"), Arrays.asList("The Other Dream Team"));
        expected.put(Genre.valueOf("ROMANCE"), Arrays.asList("Casablanca"));
        expected.put(Genre.valueOf("WESTERN"), Arrays.asList("The Good the Bad and the Ugly"));
        expected.put(Genre.valueOf("HORROR"), Arrays.asList("A Beginner's Guide to Snuff"));
        expected.put(Genre.valueOf("BIOGRAPHY"), Arrays.asList("Schindler's List"));
        expected.put(Genre.valueOf("SHORT"), Arrays.asList("Marilyn Hotchkiss' Ballroom Dancing and Charm School"));
        expected.put(Genre.valueOf("FANTASY"), Arrays.asList("The Lord of the Rings: The Return of the King"));
        expected.put(Genre.valueOf("CRIME"), Arrays.asList("The Shawshank Redemption"));
        expected.put(Genre.valueOf("DRAMA"), Arrays.asList("The Shawshank Redemption"));

        Map<Genre, List<String>> result = topKMoviesByGenreInGivenYear
                .apply(database.getMovieGenres(), 1, 2016);

        assertEquals(expected.keySet(), result.keySet());
    }

    @Test
    public void top_k_genre_by_a_given_year() {
        List<String> expected = Arrays.asList("DRAMA", "COMEDY");
        List<String> result = topKGenreByYear
                .apply(database.getMovieGenres(), 2, 2016);
        assertEquals(expected, result);
    }

    @Test
    public void top_k_actor_appeared_in_leading_role_in_a_given_year() {
        List<String> expected = Arrays.asList("Scarlett Johansson", "Morgan Freeman");
        List<String> result = topKActorAppearedInLeadingRoleInAGivenYear
                .apply(database.getMovieActors(), 2, 2016);
        assertEquals(expected, result);
    }

    @Test
    public void in_a_given_year_how_many_movies_were_released_by_given_content_rating() {
        Long expected = 42L;
        Long result = movieCountContentRatingInAGivenYear
                .apply(database.getMovies(),2016, ContentRating.PG_13);
        assertEquals(expected, result);
    }

    @Test
    public void top_k_actors_acted_on_given_content_rating_in_given_year() {
        List<String> expected = Arrays.asList("Tamsin Egerton", "Alice Englert");
        List<String> result = topKActorsForGivenContentRatingInAGivenYear
                .apply(database.getMovieActors(), ContentRating.R, 2, 2015);
        assertEquals(expected, result);
    }

    @Test
    public void top_k_lengthy_movie_from_a_given_year() {
        List<String> expected = Arrays.asList("The Legend of Suriyothai", "Pearl Harbor");
        List<String> result = topKLengthyMovieInAGivenYear
                .apply(database.getMovies(), 2, 2001);
        assertEquals(expected, result);
    }

    @Test
    public void top_k_movie_count_by_country_for_a_given_year() {
        List<Tuple<String, Long>> expected = Arrays.asList(
                new Tuple<>("USA", 72L),
                new Tuple<>("UK", 11L),
                new Tuple<>("CANADA", 3L)
        );
        List<Tuple<String, Long>> result = topKMovieCountByCountryInAGivenYear
                .apply(database.getMovies(), 3, 2016);
        assertEquals(expected, result);
    }

    @Test
    public void top_k_director_based_on_awards() {
        List<String> expected = Arrays.asList("Martin Scorsese", "Ang Lee");
        List<String> result = topKDirectorBasedOnAwards
                .apply(database.getMovieAwards(), 2);
        assertEquals(expected, result);
    }

    @Test
    public void top_k_flop_leading_actors_in_a_given_year() {
        List<String> expected = Arrays.asList("Melissa Sagemiller", "Mariah Carey");
        List<String> result = topKFlopLeadingActorsInAGivenYear
                .apply(database.getMovieActors(), 10, 2001);
        assertEquals(expected, result);
    }

    @Test
    public void first_n_movies_title_that_has_a_specific_words() {
        List<String> expected = Arrays.asList("Blood Work", "There Will Be Blood");
        List<String> result = firstNMovieTitleContainGivenWord
                .apply(database.getMovies(), "blood", 2);
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void movie_count_by_language_for_a_given_year() {
        int size = 47;
        List<Tuple<String, Long>> result = movieCountByLanguageInAGivenYear
                .apply(database.getMovies(), 2016);
        assertEquals(size, result.size());
    }

    @Test
    public void top_k_movie_count_by_director_for_a_given_year() {
        List<Tuple<String, Long>> expected = Arrays.asList(
                new Tuple<>("Steven Spielberg", 2L),
                new Tuple<>("Dennis Dugan", 2L)
        );
        List<Tuple<String, Long>> result = topKMovieCountByDirectorInAGivenYear
                .apply(database.getMovies(), 2, 2011);
        assertEquals(expected, result);
    }
}
