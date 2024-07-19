package it.guidera.gabriele.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import it.guidera.gabriele.model.Meteo;

public interface MeteoDAO extends JpaRepository<Meteo, Integer> {
	
}
