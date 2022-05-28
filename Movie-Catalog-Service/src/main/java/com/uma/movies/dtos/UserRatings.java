package com.uma.movies.dtos;

import java.util.List;

public class UserRatings {

	private String sUserId;
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
	public String getsUserId() {
		return sUserId;
	}
	public void setsUserId(String sUserId) {
		this.sUserId = sUserId;
	}
	
}
