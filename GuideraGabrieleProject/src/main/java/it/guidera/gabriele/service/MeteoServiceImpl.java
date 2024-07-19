package it.guidera.gabriele.service;

import it.guidera.gabriele.dao.MeteoDAO;
import it.guidera.gabriele.model.Meteo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MeteoServiceImpl implements MeteoService {

 @Autowired
    private MeteoDAO meteoDAO;
    
    @Override
    public List<Meteo> findAll() {
        return meteoDAO.findAll();
    }

    @Override
    public Meteo findById(int id) {
        return meteoDAO.findById(id).orElse(null);
    }

    @Override
    public Meteo save(Meteo meteo) {
        return meteoDAO.save(meteo);
    }

    @Override
    public void deleteById(int id) {
        meteoDAO.deleteById(id);
    }

    @Override
    public void saveAll(List<Meteo> meteos) {
        meteoDAO.saveAll(meteos);
    }

  
}
