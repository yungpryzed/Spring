package it.guidera.gabriele.controller;

import it.guidera.gabriele.dto.*;
import it.guidera.gabriele.service.UtenteService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.*;

@Path("/utente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GET
    public List<UtenteDTO> getAllUtenti() {
        return utenteService.getAllUtenti();
    }

    @GET
    @Path("/{id}")
    public Optional<UtenteDTO> getUtenteById(@PathVariable int id) {
        return utenteService.getUtenteById(id);
    }

    @GET
    @Path("/byEmail/{email}")
    public Optional<UtenteDTO> getUtenteByEmail(@PathVariable String email) {
        return utenteService.getUtenteByEmail(email);
    }

    

    /*
    @PUT
    @Path("/{id}")
    public UtenteDTO updateUtente(@PathVariable int id, @RequestBody UtenteDTO utenteDTO) {
        return utenteService.updateUtente(id, utenteDTO);
    }*/
    
    @DELETE
    @Path("/{id}")
    public void deleteUtente(@PathVariable int id) {
        utenteService.deleteUtente(id);
    }
    
    // Per fare il register
    @POST
    public UtenteDTO createUtente(@RequestBody UtenteRegistrationDTO utenteRegistrazioneDTO) {
        return utenteService.createUtente(utenteRegistrazioneDTO);
    }
    
    // Per fare il login
    @POST
    @Path("/login")
    public UtenteLoginResponseDTO login(@RequestBody UtenteLoginRequestDTO utenteLoginRequestDTO) {
        Optional<UtenteDTO> optionalUtente = utenteService.getUtenteByEmail(utenteLoginRequestDTO.getEmail());
        if (optionalUtente.isPresent()) {
            UtenteDTO utente = optionalUtente.get();
            if (utente.getPassword().equals(utenteLoginRequestDTO.getPassword())) {
                return issueToken(utenteLoginRequestDTO.getEmail());
            } else {
                throw new RuntimeException("Invalid email or password");
            }
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    public UtenteLoginResponseDTO issueToken(String email) {
        byte[] secret = "aklsuhfghvsfhdjverlvherfgbvevfgbergfbvhbehjbverggfivhvbbvgio7777".getBytes();
        Key key = Keys.hmacShaKeyFor(secret);

        UtenteDTO informazioniUtente = utenteService.getUtenteByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Map<String, Object> map = new HashMap<>();

        map.put("nome", informazioniUtente.getNome());
        map.put("cognome", informazioniUtente.getCognome());
        map.put("email", email);

        Date creation = new Date();
        Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));

        String tokenJwts = Jwts.builder()
                .claims(map)
                .issuer("http://localhost:8080")
                .issuedAt(creation)
                .expiration(end)
                .signWith(key)
                .compact();

        UtenteLoginResponseDTO token = new UtenteLoginResponseDTO();
        token.setToken(tokenJwts);
        token.setTokenCreationTime(creation);
        token.setTtl(end);

        return token;
    }
}
