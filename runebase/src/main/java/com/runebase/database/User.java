package com.runebase.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Modellklasse für die Speicherung von Anwenderdaten. Enthält die Abbildung auf
 * eine Datenbanktabelle in Form von JPA-Annotation.
 * 
 * @author Bastian Katz (mailto: bastian.katz@hm.edu)
 */
@Entity
public class User {

	@Id
	@NotNull
	@Length(min = 4, max = 32)
	@Column(length = 32)
	private String login;

	@NotNull
	@Length(min = 4, max = 32)
	@Column(length = 32)
	private String name;

	@NotNull
	@Length(min = 7, max = 32) // lenght includes "{noop}"
	@Column(length = 32)
	private String password;
	
	@NotNull
	@Length(min = 7, max = 32) // lenght includes "{noop}"
	@Column(length = 32)
	private Collection<> favorites;

	@Column(name = "ADMIN")
	private boolean administrator;

	/**
	 * JPA-kompatibler Kostruktor. Wird nur von JPA verwendet und darf private sein.
	 */
	public User() {
		// JPA benötigt einen Default-Konstruktor!
	}

	/**
	 * Konstruktor zum Initialisieren eines neuen Anwenders.
	 * 
	 * @param login         login, mindestens 4 Zeichen lang
	 * @param password      Passwort inklusive Hash "{noop}"
	 * @param administrator Flag (true für Administratorrechte)
	 */
	public User(String login, String name, String password, boolean administrator) {
		super();
		this.login = login;
		this.name = name;
		this.password = password;
		this.administrator = administrator;
	}

	@Override
	public String toString() {
		return login + (administrator ? " (admin)" : "");
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	/*
	 * Standard-Methoden. Es ist sinnvoll, hier auf die Auswertung der Assoziationen
	 * zu verzichten, nur die Primärschlüssel zu vergleichen und insbesonderen
	 * Getter zu verwenden, um auch mit den generierten Hibernate-Proxys kompatibel
	 * zu bleiben.
	 */

	@Override
	public int hashCode() {
		return Objects.hash(administrator, login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(getLogin(), other.getLogin());
	}

}
