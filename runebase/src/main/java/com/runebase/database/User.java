package com.runebase.database;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	@GeneratedValue
	private String name;

	@NonNull
	@GeneratedValue
	private String password;

	/**
	 * JPA-kompatibler Kostruktor. Wird nur von JPA verwendet und darf private sein.
	 */
	public User() {
		// JPA benötigt einen Default-Konstruktor!
	}
	
	public User(String name) {
		this.name = name;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * Standard-Methoden. Es ist sinnvoll, hier auf die Auswertung der Assoziationen
	 * zu verzichten, nur die Primärschlüssel zu vergleichen und insbesonderen
	 * Getter zu verwenden, um auch mit den generierten Hibernate-Proxys kompatibel
	 * zu bleiben.
	 */

	@Override
	public int hashCode() {
		return Objects.hash(name, password);
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
		return Objects.equals(getName(), other.getName());
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	

}
