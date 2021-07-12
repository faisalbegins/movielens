package edu.miu.movielens.model;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    private String name;
    private String summery;
    private Person writer;
    private Person director;
    private List<Person> actors;
    private List<Genre> genres;
    private Integer duration;
    private LocalDate releaseYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public Person getWriter() {
        return writer;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public void setWriter(Person writer) {
        this.writer = writer;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }
}
