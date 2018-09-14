package com.codex.imp;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.codex.data.UserData;
import com.codex.model.User;

import io.jsonwebtoken.impl.crypto.MacProvider;


public class UsersManager {
	static List<User> users = new ArrayList<User>();
	static Key key;

	static UsersManager userManager = null;
	
	public static UsersManager getInstance() {
		if (userManager == null) {
			userManager = new UsersManager();
			key = MacProvider.generateKey();
		}
		return userManager;
	}
	
	public Key getKey() {
		return key;
	}
	
	// GET ALL USERS
		public List<User> getUsers() {
			UserData userData = UserData.getInstance();
			
			
			return userData.getUsers();
		}

		public static boolean checkCredentials(String username, String password) {
			
			UserData userData = UserData.getInstance();
			return userData.checkCredentials(username, password);
			
		}

		public void createUser(String username, String email, String password, String nome) {
			
			UserData userData = UserData.getInstance();
			

			userData.createUser(new User(username,  password, email,nome));
			
		}
		
		// GET A USER = USERID
		public List<User> getUser(String userID) {
			UserData userData = UserData.getInstance();
			return userData.getUser(userID);
		}

		//CHANGE PASSWORD USERID
		public Response changePassword(String username, String password, String NovaPassword, String user_ID) {
			
			List<User> user = UserData.getInstance().getUser(username);
			
			String userPassword = null;
			for (User user_Temp : user) {
				userPassword = user_Temp.getPassword();
			}
			
			
			
			// CHECKS IF THE USER LOGGED == USER TO UPDATE
			if (username.equals(user_ID) && (password.equals(userPassword))) {
				UserData.getInstance().updatePassword(username, NovaPassword);
				
			} else {
				System.out.println("VOCE NAO PODE ALTERAR A PASS PQ NAO TEM PERMISSOES");
			}
			return null;
		}

		
}