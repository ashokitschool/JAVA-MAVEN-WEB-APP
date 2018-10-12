package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.domain.User;
import com.app.service.UserService;


/**
 * 
 * @author Ashok
 *
 */
@RestController
public class UserRestController {

	@Autowired(required = true)
	private UserService service;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { "application/xml",
			"application/json" })
	public @ResponseBody String addUser(@RequestBody User user) {
		boolean isAdded = service.add(user);
		if (isAdded) {
			return "User Added successfully";
		} else {
			return "Failed to Add the User..!";
		}
	}

	@RequestMapping(value = "/get", produces = { "application/xml", "application/json" },method=RequestMethod.GET)
	@ResponseBody
	public User getUserById(@RequestParam(name = "uid") String uid) {
		System.out.println("Getting User with User Id : "+uid);
		User user = service.get(uid);
		return user;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = { "application/xml",
			"application/json" })
	public @ResponseBody String update(@RequestParam("uid") String uid, @RequestBody User user) {
		boolean isAdded = service.update(uid, user);
		if (isAdded) {
			return "User updated successfully";
		} else {
			return "Failed to update the User..!";
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody String delete(@RequestParam("uid") String uid) {
		boolean isAdded = service.delete(uid);
		if (isAdded) {
			return "User Deleted successfully";
		} else {
			return "Failed to Delete the User..!";
		}
	}

	public void setService(UserService service) {
		this.service = service;
	}

}
