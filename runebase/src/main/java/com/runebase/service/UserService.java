package com.runebase.service;

import java.util.List;

import com.runebase.service.dto.UserDisplayDto;

public interface UserService {

	/**
	 * Zugriff auf alle Nutzer.
	 */
	List<UserDisplayDto> getAllUsers();

	/**
	 * Zugriff auf alle Admins.
	 */
	List<UserDisplayDto> findeAdmins();

	/**
	 * Zugriff auf alle Nutzerdaten.
	 */
	UserDisplayDto getUserInfo(String login);

	/**
	 * Legt einen neuen Nutzer an.
	 */

	void legeAn(String login, String name, String password, boolean b);

	/**
	 * Ändert die Nutzerdaten eines Users
	 */

	void changeUserData(String login, String name, String password);

}
