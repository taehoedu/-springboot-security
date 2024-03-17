package com.office.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {

	// member 홈으로 리다이렉트
	@GetMapping({"", "/"})
	public String home() {
		log.info("home()");
		
		return "redirect:/member";
		
	}
	
}
