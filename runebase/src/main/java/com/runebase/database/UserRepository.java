package com.runebase.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository zum Zugriff auf gespeicherte Anwenderdaten. Repostory-Interfaces erben eine
 * unglaubliche Menge hilfreicher Methoden. Weitere Methoden kann man einfach durch Benennung
 * definierern. Spring Data ergänzt die Implementierungen zur Laufzeit.
 * 
 * @author Bastian Katz (mailto: bastian.katz@hm.edu)
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {


 
  
}
