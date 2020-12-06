package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.models.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class HomeController {
	private UserService userService;
	private NoteService noteService;
	private FileService fileService;
	private CredentialService credentialService;
	private EncryptionService encryptionService;

	public HomeController(UserService userService, NoteService noteService, FileService fileService,
			CredentialService credentialService, EncryptionService encryptionService) {
		super();
		this.userService = userService;
		this.noteService = noteService;
		this.fileService = fileService;
		this.credentialService = credentialService;
		this.encryptionService = encryptionService;
	}

	@GetMapping("/home")
	public String getHome(Model model, @ModelAttribute("formCredential") Credentials credential,
			@ModelAttribute("formNote") Notes note, Authentication auth) {
		Users user = userService.getUser(auth.getName());
		model.addAttribute("fileList", fileService.listAllFiles(user.getUserid()));
		model.addAttribute("noteList", noteService.getAllNotes(user.getUserid()));
		model.addAttribute("credentialList", credentialService.getAllCredentials(user.getUserid()));

		return "home";
	}

}
