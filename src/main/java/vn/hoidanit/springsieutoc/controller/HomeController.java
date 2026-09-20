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
		return "user/show";
	}

	@GetMapping("/user/create")
	public String createUser() {
		return "user/create";
	}

	@GetMapping("/user/{id}/edit")
	public String updateUser() {
		return "user/update";
	}
}
