package com.microservice.MovieCatalog.models;

import java.util.List;

public class UserRating {
    private List<Rating> userRating;

    public UserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }

    public List<Rating> getUserRating() {
        return userRating;
    }

    public UserRating() {
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }
}
