package com.vamshi.moviecatalogservice.resource;

import com.vamshi.moviecatalogservice.models.Movie;
import com.vamshi.moviecatalogservice.models.MovieCatalog;
import com.vamshi.moviecatalogservice.models.Ratings;
import com.vamshi.moviecatalogservice.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webclientBuilder;

    @GetMapping("/{userId}")
    public List<MovieCatalog> getCatalog(@PathVariable("userId") String userId) {

        UserRatings newRatings = restTemplate.getForObject("http://rating-data-service/ratings/users/" +userId, UserRatings.class);

//        List<Ratings> ratings = Arrays.asList(
//                new Ratings(1, 4),
//                new Ratings(2, 3)
//        );

        return newRatings.getUserRatings()
                .stream()
                .map(rating -> {
//            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

                    Movie movie = webclientBuilder.build()
                        .get()
                        .uri("http://movie-info-service/movies/" + rating.getMovieId())
                        .retrieve()
                        .bodyToMono(Movie.class)
                        .block();

                    return new MovieCatalog(movie.getName(), "desc", rating.getRating());
        })
                .collect(Collectors.toList());
    }
}
