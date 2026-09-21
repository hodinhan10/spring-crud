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

	@GetMapping("/user/edit/{id}")
	public String updateUser() {
		return "user/CreateOrEdit";
	}

	@GetMapping("/faculty")
	public String faculties() {
		return "Faculty";
	}

	@GetMapping("/faculty/{facultyId}/students")
	public String studentsByFaculty() {
		return "Student/list";
	}

	@GetMapping("/faculty/{facultyId}/students/create")
	public String createStudent() {
		return "Student/CreateOrEdit";
	}

	@GetMapping("/faculty/{facultyId}/students/edit/{studentId}")
	public String updateStudent() {
		return "Student/CreateOrEdit";
	}
}
