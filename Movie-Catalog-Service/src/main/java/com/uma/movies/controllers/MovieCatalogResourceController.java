package com.uma.movies.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uma.movies.dtos.CatalogItem;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResourceController {

	@RequestMapping("/{pUserId}")
	public List<CatalogItem> getCatalogs(@PathVariable("pUserId") String pUserId) {
		return Collections.singletonList(new CatalogItem("Transformer", "Arnold", 4));
	}
}
