package com.doconnectai.service;

import com.doconnectai.entity.User;

public interface IUserService {
	
	User registerUser(User user);
	
	User getUserByIdUser(int id);
	
	User getUserByEmail(String email);

}
