package com.runebase.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.runebase.service.UserService;
import com.runebase.service.dto.UserDisplayDto;
import com.runebase.service.dto.UserManagementDto;

/**
 * Controller-Klasse für alle Interaktionen, die das Verwalten der Anwender
 * betrifft. Controller reagieren auf Aufrufe von URLs. Sie benennen ein
 * View-Template (Thymeleaf-Vorlage) und stellen Daten zusammen, die darin
 * dargestellt werden. Dafür verwenden Sie Methoden der Service-Schicht.
 * 
 * @author Bastian Katz (mailto: bastian.katz@hm.edu)
 */
@Controller
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	/**
	 * Erzeugt eine Listenansicht mit allen Anwendern.
	 */
	@GetMapping("/users")
	public String getUserListView(Model model, Authentication auth) {
		model.addAttribute("users", userService.getAllUsers());
		return "user-listview";
	}

	/**
	 * Erzeugt eine Profilansicht mit Daten des Users.
	 */
	@GetMapping("/profil")
	public String getProfilView() {
		return "profil";
	}

	/**
	 * Verarbeitet die Änderung des Namens und/oder Passworts eines Users.
	 */
	@PostMapping("/changeUserData")
	public String changeUserData(@ModelAttribute("user") UserDisplayDto user, Authentication auth,
			@RequestHeader(value = "referer", required = true) String referer, RedirectAttributes redirectAttributes) {
		try {
			userService.changeUserData(user.getLogin(), user.getName(), user.getPassword());
			redirectAttributes.addFlashAttribute("success", "Daten wurden erfolgreich geändert!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:" + referer;
		}

		return "redirect:" + referer;
	}

	/**
	 * Erzeugt eine Formularansicht für das Erstellen eines Anwenders.
	 */
	@GetMapping("/users/create")
	public String getUserCreationView(Model model) {
		model.addAttribute("newUser", new UserManagementDto());
		return "user-creation";
	}

	/**
	 * Nimmt den Formularinhalt vom Formular zum Erstellen eines Anwenders entgegen
	 * und legt einen entsprechenden Anwender an. Kommt es dabei zu einer Exception,
	 * wird das Erzeugungsformular wieder angezeigt und eine Fehlermeldung
	 * eingeblendet. Andernfalls wird auf die Listenansicht der Anwender
	 * weitergeleitet und das Anlegen in einer Einblendung bestätigt.
	 */
	@PostMapping("users")
	public String handleUserCreation(Model model, @ModelAttribute("newUser") UserManagementDto anwender,
			RedirectAttributes redirectAttributes) {
		try {
			userService.legeAn(anwender.getLogin(), anwender.getName(), anwender.getPassword(), false);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/users/create";
		}
		redirectAttributes.addFlashAttribute("success", "Anwender " + anwender.getLogin() + " erstellt.");
		return "redirect:/users";
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
