package vn.hoidanit.springsieutoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "home";
	}

	@GetMapping("/user")
	public String users() {
		return "user/index";
	}

	@GetMapping("/faculty")
	public String faculties() {
		return "faculty/index";
	}
}
