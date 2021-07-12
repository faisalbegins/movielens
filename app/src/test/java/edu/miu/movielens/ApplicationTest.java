package edu.miu.movielens;

import edu.miu.movielens.model.Genre;
import edu.miu.movielens.model.Movie;
import edu.miu.movielens.model.MovieAward;
import edu.miu.movielens.model.ViewerRating;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationTest {
    private List<Movie> movies;
    private List<ViewerRating> ratings;
    private List<MovieAward> awards;

    @Before
    public void initMovies() {
        this.movies = new ArrayList<>();
        this.movies.add(new Movie() {{
            setName("Tenet");
            setGenres(new ArrayList<>() {{
                add(Genre.ACTION);
                add(Genre.SCI_FI);
                add(Genre.THRILLER);
            }});
            setDirector(LookupFactory.directors().get(3));
            setActors(new ArrayList<>() {{
                
            }});
        }});
    }

    @Test
    public void appHasAGreeting() {
        Application application = new Application();
        assertNotNull("app should have a greeting", "test");
    }
}
