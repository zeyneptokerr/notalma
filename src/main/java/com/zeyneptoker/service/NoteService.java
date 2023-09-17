package com.zeyneptoker.service;

import java.util.ArrayList;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeyneptoker.dao.NoteDAO;
import com.zeyneptoker.entity.Note;
import com.zeyneptoker.security.LoginFilter;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteDAO noteDAO;
	
	public Long createNote(Note note, HttpServletRequest request) {
		//TODO:user_id deðiþecek
		note.setUser_id(LoginFilter.user.getId());
		return noteDAO.insert(note);
	}

	public Long updateNote(Note note, HttpServletRequest request) {
		//TODO:user_id deðiþecek
		noteDAO.update(note);
		return LoginFilter.user.getId();
	}

	public Long deleteNote(Note note, HttpServletRequest request) {
		//TODO:user_id deðiþecek
		noteDAO.delete(note);
		return LoginFilter.user.getId();
	}
	public Note getNoteFindById(Long id){
		return noteDAO.getNoteFindById(id);
	}
	
	public ArrayList<Note> getAll(){
		return noteDAO.getAll();
	}
	
	public ArrayList<Note> getAll(Long userId){
		return noteDAO.getAll(userId);
	}
}
