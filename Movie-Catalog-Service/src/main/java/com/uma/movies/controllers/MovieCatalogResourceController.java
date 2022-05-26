package com.uma.movies.controllers;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.uma.movies.dtos.CatalogItem;
import com.uma.movies.dtos.Movies;
import com.uma.movies.dtos.Rating;
import com.uma.movies.dtos.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResourceController {

	@Autowired private RestTemplate vRestTemplate;
	@Autowired private WebClient.Builder vBuilder;
	
	@RequestMapping("/{pUserId}")
	public List<CatalogItem> getCatalogs(@PathVariable("pUserId") String pUserId) {
		/*List<Rating> vAlRatings= Arrays.asList(
				new Rating("1001", 4),
				new Rating("1002", 3)
			);*/
		
		UserRatings vUserRatings= vRestTemplate.getForObject("http://localhost:4002/ratingddata/users/" + pUserId, UserRatings.class);
		List<Rating> vAlRatings= vUserRatings.getAlRatings();
		
		return vAlRatings.stream().map((rating)-> {
			Movies vMovies= vRestTemplate.getForObject("http://localhost:4001/movies/" + rating.getsMovieId(), Movies.class);
			return new CatalogItem(vMovies.getsName(), "Arnold", rating.getiRatings());
		}).collect(Collectors.toList());
	}
	
	@RequestMapping("/wc/{pUserId}")
	public List<CatalogItem> getCatalogsWc(@PathVariable("pUserId") String pUserId) {
		List<Rating> vAlRatings= Arrays.asList(
				new Rating("1001", 4),
				new Rating("1002", 3)
			);
		
		return vAlRatings.stream().map((rating)-> {
			Movies vMovies= vBuilder.build()
				.get()
				.uri("http://localhost:4001/movies/" + rating.getsMovieId())
				.retrieve().bodyToMono(Movies.class)
				.block();
			return new CatalogItem(vMovies.getsName(), "Arnold", rating.getiRatings());
		}).collect(Collectors.toList());
	}
	
	
}
