package org.jsp.userbootapp.controller;

import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<User>> findAll() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable(name = "id") int id) {
		return service.findById(id);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<Boolean>> deleteById(@PathVariable(name = "id") int id) {
		return service.deleteById(id);
	}

	@PostMapping("/users/verify-by-phone")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam(name = "phone") long phone, @RequestParam(name = "password") String password) {
		return service.verifyUser(phone, password);
}
}
