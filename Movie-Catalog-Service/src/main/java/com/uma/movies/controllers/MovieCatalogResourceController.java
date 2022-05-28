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

import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.uma.movies.dtos.CatalogItem;
import com.uma.movies.dtos.Movies;
import com.uma.movies.dtos.Rating;
import com.uma.movies.dtos.UserRatings;
import com.uma.movies.services.MovieInfoService;
import com.uma.movies.services.RatingInfoService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResourceController {

	@Autowired private RestTemplate vRestTemplate;
	@Autowired private WebClient.Builder vBuilder;
	//@Autowired 
	private DiscoveryClient vDiscoveryClient;
	
	@Autowired MovieInfoService vMovieInfoService;
	@Autowired RatingInfoService vRatingInfoService;
	
	
	@RequestMapping("/hys1/{pUserId}")
	public List<CatalogItem> getCatalogsHtstrixIdeal(@PathVariable("pUserId") String pUserId) {
		UserRatings vUserRatings= vMovieInfoService.getUserRatings(pUserId);
		List<Rating> vAlRatings= vUserRatings.getAlRatings();
		return vAlRatings.stream().map((rating)-> {
			return vRatingInfoService.findCatalogItem(rating);
		}).collect(Collectors.toList());
	}
	
	

	





	@RequestMapping("/hys/{pUserId}")
	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalogsHtstrix(@PathVariable("pUserId") String pUserId) {
		//fallback method needed in circuit break for method calls within the class, hystrix doesnot work in this case.
		UserRatings vUserRatings= vRestTemplate.getForObject("http://movie-rating-service/ratingddata/users/" + pUserId, UserRatings.class);
		List<Rating> vAlRatings= vUserRatings.getAlRatings();
		
		
		return vAlRatings.stream().map((rating)-> {
			Movies vMovies= vRestTemplate.getForObject("http://movie-info-service/movies/" + rating.getsMovieId(), Movies.class);
			return new CatalogItem(vMovies.getsName(), "Arnold", rating.getiRatings());
		}).collect(Collectors.toList());
	}
	
	public List<CatalogItem> getFallbackCatalog(@PathVariable("pUserId") String pUserId) {
		return Arrays.asList(new CatalogItem("No Movie available", "", 0));
	}
	
	
	
	
	
	@RequestMapping("/er/{pUserId}")
	public List<CatalogItem> getCatalogsEureka(@PathVariable("pUserId") String pUserId) {
		UserRatings vUserRatings= vRestTemplate.getForObject("http://movie-rating-service/ratingddata/users/" + pUserId, UserRatings.class);
		List<Rating> vAlRatings= vUserRatings.getAlRatings();
		
		
		return vAlRatings.stream().map((rating)-> {
			Movies vMovies= vRestTemplate.getForObject("http://movie-info-service/movies/" + rating.getsMovieId(), Movies.class);
			return new CatalogItem(vMovies.getsName(), "Arnold", rating.getiRatings());
		}).collect(Collectors.toList());
	}
	
	
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
