package com.uma.movies.dtos;

public class Rating {

	private String sMovieId;
	private int iRatings;
	
	public Rating(String sMovieId, int iRatings) {
		super();
		this.sMovieId = sMovieId;
		this.iRatings = iRatings;
	}
	public Rating() {
		super();
	}
	public String getsMovieId() {
		return sMovieId;
	}
	public void setsMovieId(String sMovieId) {
		this.sMovieId = sMovieId;
	}
	public int getiRatings() {
		return iRatings;
	}
	public void setiRatings(int iRatings) {
		this.iRatings = iRatings;
	}
}
