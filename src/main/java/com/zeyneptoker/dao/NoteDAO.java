package com.zeyneptoker.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeyneptoker.entity.Note;;

@Repository
public class NoteDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Long insert(Note note) {
		return (Long) sessionFactory.getCurrentSession().save(note); 
	}
	
	public void update(Note note) {
		sessionFactory.getCurrentSession().update(note); 
	}

	// g�ncellenecek verinin id'si var ise g�nceller yok ise insert eder
	public void persist(Note note) {
		sessionFactory.getCurrentSession().persist(note); 
	}
	
	public void delete(Note note) {
		sessionFactory.getCurrentSession().delete(note); 
	}
	
	public Note getNoteFindById(Long id){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note WHERE id=:id")//Fromdan sonra tablo ismi de�il class�n ismi kullan�l�r.
				.setLong("id", id); 
		return (Note) query.getSingleResult(); //girilen id'nin kar��l��� yok ise null hatas� al�n�yor
	}
	
	public ArrayList<Note> getAll(){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note"); //Fromdan sonra tablo ismi de�il class�n ismi kullan�l�r.
		return (ArrayList<Note>) query.getResultList();
	}

	public ArrayList<Note> getAll(Long userId){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note WHERE user_id=:userId order by id desc")//Fromdan sonra tablo ismi de�il class�n ismi kullan�l�r.
				.setLong("userId", userId); 
		return (ArrayList<Note>) query.getResultList();
	}
}
