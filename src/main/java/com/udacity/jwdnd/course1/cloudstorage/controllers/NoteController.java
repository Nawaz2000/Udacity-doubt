package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.models.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class NoteController {
	private NoteService noteService;
	private UserService userService;

	public NoteController(NoteService noteService, UserService userService) {
		super();
		this.noteService = noteService;
		this.userService = userService;
	}

	@PostMapping("/noteUpload")
	public String noteUpload(@ModelAttribute("formNote") Notes note, Model model, Authentication auth) {

		Users user = userService.getUser(auth.getName());
		note.setUserid(user.getUserid());

		if (!noteService.isNoteTitleAvailable(note.getNotetitle()))
			model.addAttribute("error", "Note of same title already exists!");
		else {
			noteService.addOrUpdateNote(note/* , auth */);
			model.addAttribute("success", true);
		}

		return "result";
	}

	@GetMapping("/noteDelete")
	public String noteDelete(@RequestParam Integer noteid, Model model) {
		noteService.deleteNote(noteid);
		model.addAttribute("success", true);
		return "result";
	}
}
