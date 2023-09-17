package com.zeyneptoker.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeyneptoker.entity.Note;
import com.zeyneptoker.entity.User;;

@Repository
public class UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Long insert(User user) {
		return (Long) sessionFactory.getCurrentSession().save(user); 
	}
	
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user); 
	}
	
	public User getFindByUsernameAndPass(String username, String pass){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username AND pass=:pass AND active=:active")//Fromdan sonra tablo ismi deðil classýn ismi kullanýlýr.
				.setString("username", username)
				.setString("pass", pass) 
				.setBoolean("active", true);
		User user = null;
		
		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		
		return user;
	}
	
	public User getFindByUsername(String username){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username")//Fromdan sonra tablo ismi deðil classýn ismi kullanýlýr.
				.setString("username", username);
		return (User) query.getSingleResult(); //girilen id'nin karþýlýðý yok ise null hatasý alýnýyor
	}
	
	public User getFindByKey(String key){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE key=:key")//Fromdan sonra tablo ismi deðil classýn ismi kullanýlýr.
				.setString("key", key);
		
		User user = null;
		
		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		
		return user;
	}
	
}
