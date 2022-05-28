package com.uma.movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.uma.movies.dtos.CatalogItem;
import com.uma.movies.dtos.Movies;
import com.uma.movies.dtos.Rating;

@Service
public class RatingInfoService {

	@Autowired private RestTemplate vRestTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItems")
	public CatalogItem findCatalogItem(Rating pRating) {
		Movies vMovies= vRestTemplate.getForObject("http://movie-info-service/movies/" + pRating.getsMovieId(), Movies.class);
		return new CatalogItem(vMovies.getsName(), "Arnold", pRating.getiRatings());
	}
	
	public CatalogItem getFallbackCatalogItems(Rating pRating) {
		return new CatalogItem("No Movie Found", "-NA-", pRating.getiRatings());
	}
}
