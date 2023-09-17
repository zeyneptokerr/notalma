package com.zeyneptoker.service;

import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeyneptoker.dao.UserDAO;
import com.zeyneptoker.entity.Note;
import com.zeyneptoker.entity.User;
import com.zeyneptoker.security.LoginFilter;;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserDAO userDAO;
	
	public Long insert(User user) {
		String uuid = UUID.randomUUID().toString();
		user.setKey(uuid);
		
		
		if(userDAO.insert(user)>0){
			mailService.registerMail(user.getEmail(), user.getKey());
		}
		return LoginFilter.user.getId();
	}
	
	public void update(User user) {
		userDAO.update(user);; 
	}
	
	public User getNoteFindByUsernameAndPass(User user){
		return userDAO.getFindByUsernameAndPass(user.getUsername(), user.getPass());
	}
	
	public User getNoteFindByUsername(String username){
		return userDAO.getFindByUsername(username);
	}
	
	public boolean getNoteFindByKey(String key){
		User user = userDAO.getFindByKey(key);
		if(user != null) {
			user.setActive(true);
			update(user);
			return true;
		}else
			return false;
	}
}
