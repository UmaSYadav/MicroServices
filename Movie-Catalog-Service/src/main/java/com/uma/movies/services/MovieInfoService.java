package com.uma.movies.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.uma.movies.dtos.Rating;
import com.uma.movies.dtos.UserRatings;

@Service
public class MovieInfoService {

	@Autowired private RestTemplate vRestTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackRatings"/*,
			commandProperties = {
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5")
			}*/
		)
	public UserRatings getUserRatings(String pUserId) {
		UserRatings vUserRatings= vRestTemplate.getForObject("http://movie-rating-service/ratingddata/users/" + pUserId, UserRatings.class);
		return vUserRatings;
	}
	
	public UserRatings getFallbackRatings(String pUserId) {
		UserRatings vUserRatings= new UserRatings();
		vUserRatings.setsUserId(pUserId);
		vUserRatings.setAlRatings(Arrays.asList(new Rating("0", 0)));
		return vUserRatings;
	}
}
