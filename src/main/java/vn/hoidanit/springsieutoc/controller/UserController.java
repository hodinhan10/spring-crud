package vn.hoidanit.springsieutoc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.springsieutoc.model.User;
import vn.hoidanit.springsieutoc.service.UserService;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user")
	public String getUserPage(Model model) {
		List<User> users = this.userService.fetchUsers();
		model.addAttribute("users", users);
		return "user/show";
	}

	@GetMapping("/user/create")
	public String getCreateUserPage(Model model) {
		model.addAttribute("user", new User());
		return "user/create";
	}

	@PostMapping("/user/create")
	public String postCreateUser(@ModelAttribute User user) {
		this.userService.createUser(user);
		return "redirect:/user";
	}

	@GetMapping("/user/{id}")
	public String getUpdateUserPage(Model model, @PathVariable int id) {
		User updateUser = this.userService.fetchUserById(id).orElse(null);
		if (updateUser == null)	return "redirect:/user";
		model.addAttribute("user", updateUser);
		return "user/update";
	}

	@PostMapping("/user/{id}/update")
	public String postUpdatePage(@PathVariable int id, @ModelAttribute User updateUser) {
		updateUser.setId(id);
		if (!this.userService.updateUser(updateUser)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return "redirect:/user";
	}

	@PostMapping("/user/delete/{id}")
	public String postDeleteUser(@PathVariable int id) {
		if (!this.userService.deleteUser(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return "redirect:/user";
	}
}
