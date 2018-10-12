package com.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.domain.User;

@Service(value = "service")
public class UserServiceImpl implements UserService {

	//This is used to Store Users data (In Memory)
	private static Map<Integer, User> usersData = new HashMap<Integer, User>();

	/**
	 * This method is used to add the User
	 */
	public boolean add(User user) {
		if (usersData.containsKey(user.getUserid())) {
			return false;
		} else {
			usersData.put(user.getUserid(), user);
			return true;
		}
	}

	/**
	 * This method is used to retrieve the User
	 */
	public User get(String uid) {
		System.out.println(usersData);
		if (usersData.containsKey(Integer.parseInt(uid))) {
			return usersData.get(Integer.parseInt(uid));
		}
		return null;
	}

	/**
	 * This method is used to Update the Existing User
	 */
	public boolean update(String uid, User user) {
		if (usersData.containsKey(Integer.parseInt(uid))) {
			usersData.put(Integer.parseInt(uid), user);
			return true;
		}
		return false;
	}

	/**
	 * This method is used to Delete the User
	 */
	public boolean delete(String uid) {
		if (usersData.containsKey(Integer.parseInt(uid))) {
			usersData.remove(usersData.get(Integer.parseInt(uid)));
			return true;
		}
		return false;
	}

}