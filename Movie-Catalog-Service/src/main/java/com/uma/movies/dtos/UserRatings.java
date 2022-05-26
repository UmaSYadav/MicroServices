package com.uma.movies.dtos;

import java.util.List;

public class UserRatings {

	private List<Rating> alRatings;

	public UserRatings(List<Rating> alRatings) {
		super();
		this.alRatings = alRatings;
	}
	public UserRatings() {
		super();
	}
	public List<Rating> getAlRatings() {
		return alRatings;
	}
	public void setAlRatings(List<Rating> alRatings) {
		this.alRatings = alRatings;
	}
	
}
