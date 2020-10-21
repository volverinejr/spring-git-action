package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homes")
public class HomeController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}

}
