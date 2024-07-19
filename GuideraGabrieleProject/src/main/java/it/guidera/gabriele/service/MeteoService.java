package it.guidera.gabriele.service;

import it.guidera.gabriele.model.Meteo;
import java.util.List;

public interface MeteoService {
    List<Meteo> findAll();
    Meteo findById(int id);
    Meteo save(Meteo meteo);
    void deleteById(int id);
    void saveAll(List<Meteo> meteos);
    	
}