package edu.miu.movielens.model;

import java.time.LocalDate;

public record Movie(String name,
                    Person writer,
                    Person director,
                    Country country,
                    Integer duration,
                    Language language,
                    Double imdbScore,
                    ContentRating rating,
                    LocalDate releaseYear) { }
