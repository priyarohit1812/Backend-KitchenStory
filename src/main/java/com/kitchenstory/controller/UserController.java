package com.kitchenstory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kitchenstory.model.User;
import com.kitchenstory.model.requests.LoginRequest;
import com.kitchenstory.model.responses.BaseResponse;
import com.kitchenstory.service.ISecurityService;
import com.kitchenstory.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private ISecurityService securityService;

	@PostMapping("/register")
	public ResponseEntity<BaseResponse<String>> registerUser(@RequestBody User user) {
		BaseResponse<String> response = new BaseResponse<String>();
		if (user == null) {
			response.setIsError(true);
			response.setMessage("Request object is null");
			return ResponseEntity.badRequest().body(response);
		}

		User savedUser = this.userService.saveUser(user);
		if (savedUser == null) {
			response.setIsError(true);
			response.setMessage("Could not register the requested user");
			return ResponseEntity.internalServerError().body(response);

		}

		response.setIsError(false);
		response.setMessage("User registered successfully!");
		response.setResponse(this.securityService.generateJWTToken(savedUser.getUserId()));
		return ResponseEntity.ok(response);

	}

	@PutMapping("/update")
	public ResponseEntity<BaseResponse<User>> updateUser(@RequestBody User user) {
		BaseResponse<User> response = new BaseResponse<User>();
		if (user == null) {
			response.setIsError(true);
			response.setMessage("Request object is null");
			return ResponseEntity.badRequest().body(response);
		} else if (user.getUserId() <= 0) {
			response.setIsError(true);
			response.setMessage("Please provide user id to update");
			return ResponseEntity.badRequest().body(response);
		}
		
		User savedUser;
		
		if (user.getPassword() == null ||user.getPassword().isBlank()) {
			savedUser = this.userService.updateUser(user);
		} else {
			savedUser = this.userService.saveUser(user);
		}
		
		if (savedUser == null) {
			response.setIsError(true);
			response.setMessage("Could not update the requested user");
			return ResponseEntity.internalServerError().body(response);

		}

		response.setIsError(false);
		response.setMessage("User updated successfully!");
		response.setResponse(savedUser);
		return ResponseEntity.ok(response);

	}

	@PostMapping("/login")
	public ResponseEntity<BaseResponse<String>> loginUser(@RequestBody LoginRequest request) {
		BaseResponse<String> response = new BaseResponse<String>();
		if (request == null) {
			response.setIsError(true);
			response.setMessage("Request object is null");
			return ResponseEntity.badRequest().body(response);
		}

		User user = this.userService.getUser(request.getUsername(), request.getPassword());
		if (user == null) {
			response.setIsError(true);
			response.setMessage("Invalid username or password");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		response.setIsError(false);
		response.setMessage("User logged in successfully!");
		response.setResponse(this.securityService.generateJWTToken(user.getUserId()));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/get")
	public ResponseEntity<BaseResponse<User>> getuser(@RequestAttribute("userId") Long userId) {
		BaseResponse<User> response = new BaseResponse<User>();
		if (userId <= 0) {
			response.setIsError(true);
			response.setMessage("Please provide user id to fetch user");
			return ResponseEntity.badRequest().body(response);
		}

		User user = this.userService.getUserById(userId);
		if (user == null) {
			response.setIsError(true);
			response.setMessage("Could not fetch the requested user");
			return ResponseEntity.internalServerError().body(response);
		}

		response.setIsError(false);
		response.setMessage("User found!");
		response.setResponse(user);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<BaseResponse<?>> deleteUser(@RequestAttribute("userId") Long userId) {
		BaseResponse<User> response = new BaseResponse<User>();
		if (userId <= 0) {
			response.setIsError(true);
			response.setMessage("Please provide user id to fetch user");
			return ResponseEntity.badRequest().body(response);
		}

		boolean isAddressDeleted = this.userService.deleteAddresssForUser(userId);
		if (isAddressDeleted) {
			boolean isDeleted = this.userService.deleteUser(userId);
			if (isDeleted) {
				response.setIsError(true);
				response.setMessage("User deleted successfully!");
				return ResponseEntity.ok(response);
			} else {
				response.setIsError(true);
				response.setMessage("Could not delete the requested user");
				return ResponseEntity.internalServerError().body(response);
			}
		} else {
			response.setIsError(true);
			response.setMessage("Could not delete the requested user");
			return ResponseEntity.internalServerError().body(response);
		}
	}
}
