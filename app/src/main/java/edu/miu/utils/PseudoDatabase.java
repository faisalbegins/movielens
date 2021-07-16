package edu.miu.utils;

import edu.miu.movielens.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PseudoDatabase {
    private final Set<Movie> movies = new HashSet<>();
    private final Set<Person> actors = new HashSet<>();
    private final Set<Person> directors = new HashSet<>();;
    private final Set<Person> writers = new HashSet<>();;
    private final List<Award> awards = new ArrayList<>();;
    private final List<MovieAward> movieAwards = new ArrayList<>();;
    private final List<MovieActor> movieActors = new ArrayList<>();;
    private final List<MovieGenre> movieGenres = new ArrayList<>();;

    private static final String[] rows;

    private final static String FILE_NAME = "data.csv";

    public static PseudoDatabase getInstance() {
        return Holder.INSTANCE;
    }

    static {
        Optional<String> optional = FileContentReader.readFileContent(FILE_NAME);
        String content = optional.orElseThrow(RuntimeException::new);
        rows = content.split("\n");
    }

    private PseudoDatabase() {
        initialize();
    }

    private void initialize() {
        initAwards();
        initActors();
        initDirectors();
        initWriter();
        initMovies();
    }

    private void initAwards() {
        awards.add(new Award("Oscar"));
        awards.add(new Award("Golden Globe"));
    }

    private void initActors() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Actor());
        for(String row : rows) {
            String[] cells = row.split(",");
            actors.add(new Person(cells[2].trim(), roles));
            actors.add(new Person(cells[3].trim(), roles));
            actors.add(new Person(cells[4].trim(), roles));
        }
    }

    private void initDirectors() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Director());
        for(String row : rows) {
            String[] cells = row.split(",");
            directors.add(new Person(cells[0].trim(), roles));
        }
    }

    private void initWriter() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Writer());
        for(String row : rows) {
            String[] cells = row.split(",");
            writers.add(new Person(cells[0].trim(), roles));
        }
    }

    private void initMovies() {
        for (String row : rows) {
            String[] cells = row.split(",");

            // get movie name
            String movieName = cells[6].trim();

            // get writer
            Optional<Person> writerOptional = getWriters()
                    .stream()
                    .filter(person -> person.name().equals(cells[0].trim()))
                    .findFirst();
            Person writer = writerOptional.orElseThrow(RuntimeException::new);

            // get director
            Optional<Person> directorOptional = getDirectors()
                    .stream()
                    .filter(person -> person.name().equals(cells[0].trim()))
                    .findFirst();
            Person director = directorOptional.orElseThrow(RuntimeException::new);

            // get country
            String cnt = cells[8].trim().toUpperCase();
            Country country = cnt.isBlank() ? Country.UNKNOWN : Country.valueOf(cnt);

            // get duration
            String dur = cells[1].trim();
            Integer duration = dur.isBlank() ? 0 : Integer.parseInt(dur);

            // get language
            String lang = cells[7].trim().toUpperCase();
            Language language = lang.isBlank() ? Language.UNKNOWN : Language.valueOf(lang);

            // get IMDB score
            String imdb = cells[11].trim();
            Double imdbScore = imdb.isBlank() ? 0 : Double.parseDouble(imdb);


            // get content rating
            String contRat = cells[9].trim().toUpperCase();
            ContentRating rating = contRat.isBlank() ? ContentRating.NOT_RATED : ContentRating.valueOf(contRat);

            // get release year
            String ry = cells[10].trim();
            ry = ry.isBlank() ? "1995" : ry;
            ry = "01/01/" + ry;
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate releaseYear = LocalDate.parse(ry, pattern);

            Movie movie = new Movie(movieName, writer, director,
                                    country, duration, language,
                                    imdbScore, rating, releaseYear);
            // populate movie table
            movies.add(movie);

            // populate movie actor table
            movieActors.add(new MovieActor(movie, getActorByName(cells[2].trim()), true));
            movieActors.add(new MovieActor(movie, getActorByName(cells[3].trim()), false));
            movieActors.add(new MovieActor(movie, getActorByName(cells[4].trim()), false));

            // populate movie genre table
            String[] genres = cells[5].split("\\|");
            for(String genre : genres) {
                movieGenres.add(new MovieGenre(movie, Genre.valueOf(genre.toUpperCase())));
            }

            // populate movie awards
            String[] awards = cells[12].trim().split("\\|");
            for(String awardStr : awards) {
                Award award = getAwardByName(awardStr);
                if(award != null) {
                    movieAwards.add(new MovieAward(movie, new Award(award.name()), releaseYear));
                }
            }
        }
    }

    private Person getActorByName(String name) {
        return actors.stream()
                .filter(person -> person.name().equals(name))
                .findFirst().orElseThrow(RuntimeException::new);
    }

    private Award getAwardByName(String name) {
        return awards.stream()
                .filter(award -> award.name().equals(name))
                .findFirst().orElse(null);
    }


    private static class Holder {
        private static final PseudoDatabase INSTANCE = new PseudoDatabase();
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public Set<Person> getActors() {
        return actors;
    }

    public Set<Person> getDirectors() {
        return directors;
    }

    public Set<Person> getWriters() {
        return writers;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public List<MovieAward> getMovieAwards() {
        return movieAwards;
    }

    public List<MovieActor> getMovieActors() {
        return movieActors;
    }

    public List<MovieGenre> getMovieGenres() {
        return movieGenres;
    }
}
