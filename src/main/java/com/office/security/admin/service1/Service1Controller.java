package com.office.security.admin.service1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/admin/service1")
public class Service1Controller {

	@GetMapping({"", "/"})
	public String home() {
		log.info("home()");
		
		return "admin/service1/home";
		
	}
}
