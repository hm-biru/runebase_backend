package com.runebase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.runebase.database.Champion;
import com.runebase.database.ChampionRepository;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChampionController {
	@Autowired
	private ChampionRepository championRepository;

	public ChampionController(ChampionRepository championRepository) {
		this.championRepository = championRepository;
	}

	@GetMapping("/champion")
	public List<Champion> getChampion() {
		return (List<Champion>) championRepository.findAll();
	}

	@PostMapping("/champion")
	void addUser(@RequestBody Champion champion) {
		championRepository.save(champion);
	}
}
