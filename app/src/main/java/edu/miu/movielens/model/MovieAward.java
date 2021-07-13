package edu.miu.movielens.model;

import java.time.LocalDate;

public record MovieAward(Movie movie, Award award, LocalDate year) { }
