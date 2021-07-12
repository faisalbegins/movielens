package edu.miu.movielens;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationTest {
    @Test
    public void appHasAGreeting() {
        Application classUnderTest = new Application();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}
