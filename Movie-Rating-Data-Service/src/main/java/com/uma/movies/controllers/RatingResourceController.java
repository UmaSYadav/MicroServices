package com.uma.movies.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uma.movies.dtos.Rating;
import com.uma.movies.dtos.UserRatings;

@RestController
@RequestMapping("/ratingddata")
public class RatingResourceController {

	@RequestMapping("/{pMovieId}")
	public Rating getRatings(@PathVariable("pMovieId") String pMovieId) {
		return new Rating(pMovieId, 4);
	}
	
	@RequestMapping("users/{pUserId}")
	public UserRatings getUserRatings(@PathVariable("pUserId") String pUserId) {
		List<Rating> vAlRatings=  Arrays.asList(
				new Rating("1001", 4),
				new Rating("1002", 3)
				);
		UserRatings vUserRatings= new UserRatings();
		vUserRatings.setAlRatings(vAlRatings);
		return vUserRatings;
	}
}
