package com.office.security.admin.service2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/admin/service2")
public class Service2Controller {

	@GetMapping({"", "/"})
	public String home() {
		log.info("home()");
		
		return "admin/service2/home";
		
	}
	
}
