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
 * This class exposed as RestResource
 * 
 * @author Ashok
 *
 */
@RestController
public class UserRestController {

	//Injecting Service Object into RestController
	@Autowired(required = true)
	private UserService service;

	/**
	 * This method is used to Add the user - Binded to POST request 
	 * It accepts both XML and JSON data as input
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"application/json" })
	public @ResponseBody String addUser(@RequestBody User user) {
		boolean isAdded = service.add(user);
		if (isAdded) {
			return "User Added successfully";
		} else {
			return "Failed to Add the User..!";
		}
	}

	/**
	 * This method is used to Get the User details - Binded to GET request
	 * This method produces the data in XML or JSON format
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/get", produces = { "application/json" }, method = RequestMethod.GET)
	@ResponseBody
	public User getUserById(@RequestParam(name = "uid") String uid) {
		System.out.println("Getting User with User Id : " + uid);
		User user = service.get(uid);
		return user;
	}

	/**
	 * This method is used to Update the User details - Binded to PUT request
	 * @param uid
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = { "application/json" })
	public @ResponseBody String update(@RequestParam("uid") String uid, @RequestBody User user) {
		boolean isAdded = service.update(uid, user);
		if (isAdded) {
			return "User updated successfully";
		} else {
			return "Failed to update the User..!";
		}
	}

	/**
	 * This method is used to Delete the User details - Binded to DELETE request
	 * @param uid
	 * @return
	 */
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
