package com.zeyneptoker.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeyneptoker.service.NoteService;

@RestController
@RequestMapping(value="/rest")
public class NoteEndpoint {

	@Autowired
	private NoteService noteService;
}
