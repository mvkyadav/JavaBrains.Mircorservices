package com.vamshi.moviecatalogservice.models;

import java.util.List;

public class UserRatings {

    List<Ratings> userRatings;

    public UserRatings() {
    }

    public UserRatings(List<Ratings> userRatings) {
        this.userRatings = userRatings;
    }

    public List<Ratings> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<Ratings> userRatings) {
        this.userRatings = userRatings;
    }
}
