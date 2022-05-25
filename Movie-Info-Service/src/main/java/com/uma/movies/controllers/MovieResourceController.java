package com.uma.movies.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uma.movies.dtos.Movies;

@RestController
@RequestMapping("/movies")
public class MovieResourceController {

	@RequestMapping("/{pMovieId}")
	public Movies getMovieInfo(@PathVariable("pMovieId") String pMovieId) {
		return new Movies(pMovieId, "Transformer");
	}
}
