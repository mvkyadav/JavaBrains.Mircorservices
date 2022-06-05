package com.vamshi.ratingsdataservice.resource;

import com.vamshi.ratingsdataservice.models.Ratings;
import com.vamshi.ratingsdataservice.models.UserRatings;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Ratings getRating(@PathVariable("movieId") int movieId)
    {
        return new Ratings(movieId, 5);
    }

    @RequestMapping("/users/{userId}")
    public UserRatings getRatingsList(@PathVariable("userId") int id) {
        List<Ratings> ratings = Arrays.asList(
                new Ratings(1, 4),
                new Ratings(2, 3)
        );
        UserRatings userRatings = new UserRatings();
        userRatings.setUserRatings(ratings);
    return userRatings;
    }
}
