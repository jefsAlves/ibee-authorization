package com.ibee.authorization.api.controllers;

import com.ibee.authorization.api.dto.UserPasswordDTO;
import com.ibee.authorization.api.dto.UsersDTO;
import com.ibee.authorization.model.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping(value = "/{userId}")
	public ResponseEntity<UsersDTO> searchUsers(@PathVariable Long userId) {
		return new ResponseEntity<>(usersService.searchUsers(userId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UsersDTO>> listsUsers() {
		return new ResponseEntity<>(usersService.listsUsers(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UsersDTO> createUsers(@RequestBody UsersDTO usersDTO) {
		return new ResponseEntity<>(usersService.createUser(usersDTO), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{userId}")
	public ResponseEntity<UsersDTO> updateUsers(@PathVariable Long userId, @RequestBody UsersDTO usersDTO) {
		return new ResponseEntity<>(usersService.updateUsers(userId, usersDTO), HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value = "/{userId}/password")
	public ResponseEntity<Void> updateUsersPassword(@PathVariable Long userId, @RequestBody UserPasswordDTO userPasswordDTO) {
		usersService.updatePassword(userId, userPasswordDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<Void> deleteUsers(@PathVariable Long userId) {
		usersService.deleteUsers(userId);
		return ResponseEntity.noContent().build();
	}

}
