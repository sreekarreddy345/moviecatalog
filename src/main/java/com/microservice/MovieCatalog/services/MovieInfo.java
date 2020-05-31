package com.microservice.MovieCatalog.services;

import com.microservice.MovieCatalog.models.CatalogItem;
import com.microservice.MovieCatalog.models.Movie;
import com.microservice.MovieCatalog.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating userrating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + userrating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), userrating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie Name not found", "", rating.getRating());
    }
}
