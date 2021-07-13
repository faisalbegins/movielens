package edu.miu.movielens.model;

import java.util.List;

public record Person (String name, List<Role> roles) { }
