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
		return "user/list";
	}

	@GetMapping("/user/create")
	public String createUser() {
		return "user/CreateOrEdit";
	}

	@GetMapping("/user/{id}/edit")
	public String updateUser() {
		return "user/CreateOrEdit";
	}

	@GetMapping("/faculty")
	public String faculties() {
		return "Faculty";
	}

	@GetMapping("/student")
	public String students() {
		return "Student/list";
	}

	@GetMapping("/student/create")
	public String createStudent() {
		return "Student/CreateOrEdit";
	}

	@GetMapping("/student/{id}/edit")
	public String updateStudent() {
		return "Student/CreateOrEdit";
	}
}
