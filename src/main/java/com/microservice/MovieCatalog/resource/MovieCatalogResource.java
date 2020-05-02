package com.microservice.MovieCatalog.resource;

import com.microservice.MovieCatalog.models.CatalogItem;
import com.microservice.MovieCatalog.models.Movie;
import com.microservice.MovieCatalog.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
        assert userRating != null;
        return userRating.getUserRating().stream().map(userrating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + userrating.getMovieId(), Movie.class);
            assert movie != null;
            return new CatalogItem(movie.getName(), movie.getDescription(), userrating.getRating());
        })
                .collect(Collectors.toList());
    }

    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {

        return Arrays.asList(new CatalogItem("No Movie", "", 0));
    }
}
