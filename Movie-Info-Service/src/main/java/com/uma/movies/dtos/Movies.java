package com.uma.movies.dtos;

public class Movies {

	private String sMovieId;
	private String sName;
	
	public Movies() {
		super();
	}
	public Movies(String sMovieId, String sName) {
		super();
		this.sMovieId = sMovieId;
		this.sName = sName;
	}
	public String getsMovieId() {
		return sMovieId;
	}
	public void setsMovieId(String sMovieId) {
		this.sMovieId = sMovieId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
}
