package com.uma.movies.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uma.movies.dtos.DBSettings;

@RestController
public class GreetingsController {

	@Value("${my.greeting2: default_val}")
	private String vGreetings2;
	
	@Value("${my.greeting}")
	private String vGreetings;
	
	@Value("${my.list.values}")
	private List<String> vAlStrings;
	
	@Autowired private DBSettings vDbSettings;
	
	@Value("#{${keyValues}}")
	private Map<String, String> vHmKeyValues;
	
	@RequestMapping("/greetings/val")
	public String greetingsVal() {
		return vAlStrings.toString() + "--"+vHmKeyValues +"::"+vDbSettings;
	}
	
	@RequestMapping("/greetings")
	public String greetings() {
		return "Hello direct";
	}
	
	@RequestMapping("/greetings1")
	public String greetingsva() {
		return vGreetings;
	}
	
	@RequestMapping("/greetings2")
	public String greetings2() {
		return vGreetings2;
	}
	
}
