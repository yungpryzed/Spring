package it.guidera.gabriele.controller;

import it.guidera.gabriele.model.Meteo;
import it.guidera.gabriele.service.MeteoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/meteo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MeteoController {

	@Autowired
    private MeteoService meteoService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Meteo> findAll() {
        return meteoService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Meteo findById(@PathParam("id") int id) {
        return meteoService.findById(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Meteo save(Meteo meteo) {
        return meteoService.save(meteo);
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteById(@PathParam("id") int id) {
        meteoService.deleteById(id);
    }

    
    @POST
    @Path("/aggiungi")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Meteo addWeather(Meteo meteo) {
        return meteoService.save(meteo);
    }
}