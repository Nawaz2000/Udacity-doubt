package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.models.Users;

@Service
public class NoteService {
	private NoteMapper noteMapper;
	private UserService userService;

	public NoteService(NoteMapper noteMapper, UserService userService) {
		super();
		this.noteMapper = noteMapper;
		this.userService = userService;
	}

	public Notes getNote(Integer noteid) {
		return noteMapper.getNoteById(noteid);
	}

	public Notes getNote(String notetitle) {
		return noteMapper.getNoteByTitle(notetitle);
	}

	public boolean isNoteTitleAvailable(String notetitle) {
		return noteMapper.getNoteByTitle(notetitle) == null;
	}

	public Integer addOrUpdateNote(Notes note/* , Authentication auth */) {
//		Users user = userService.getUser(auth.getName());
//		note.setUserid(user.getUserid());

		if (note.getNoteid() != null)
			return noteMapper.update(note);
		else
			return noteMapper.insert(note);
	}

	public void deleteNote(Integer noteid) {
		noteMapper.delete(noteid);
	}

	public List<Notes> getAllNotes(Integer userid) {
		return noteMapper.getAllNotes(userid);
	}
}
