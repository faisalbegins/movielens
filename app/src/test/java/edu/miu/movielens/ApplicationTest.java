package edu.miu.movielens;

import edu.miu.movielens.model.Movie;
import edu.miu.movielens.model.MovieAward;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ApplicationTest {
    private List<Movie> movies;
    private List<ViewerRating> ratings;
    private List<MovieAward> awards;

    @Before
    public void initMovies() {
    }

    @Test
    public void appHasAGreeting() {
        Application application = new Application();
        assertNotNull("app should have a greeting", "test");
    }
}
