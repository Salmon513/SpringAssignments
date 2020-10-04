package com.Salmon.Start.NewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Salmon.Start.Implementation.Perform;

@RestController
public class NewController {

	@Autowired
	Perform use;
	
	@GetMapping("/Hari")
	public String Hari() {
		return "Hari";
	}
}
