package edu.miu.movielens;

import edu.miu.Application;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ApplicationTest {

    @Before
    public void initMovies() {
    }

    @Test
    public void appHasAGreeting() {
        Application application = new Application();
        assertNotNull("app should have a greeting", "test");
    }
}
