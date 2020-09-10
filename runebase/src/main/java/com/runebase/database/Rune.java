package com.runebase.database;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Rune {
	@Id
	@NotNull
	@Length(min = 4, max = 32)
	@Column(length = 32)
	private String Id;

	@NotNull
	@Length(min = 4, max = 32)
	@Column(length = 32)
	private String name;
	
	
}
