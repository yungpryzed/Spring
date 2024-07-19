package it.guidera.gabriele.service;

import java.util.List;
import java.util.Optional;
import it.guidera.gabriele.dto.UtenteDTO;
import it.guidera.gabriele.dto.UtenteRegistrationDTO;

public interface UtenteService {
    List<UtenteDTO> getAllUtenti();
    Optional<UtenteDTO> getUtenteById(int id);
    Optional<UtenteDTO> getUtenteByEmail(String email);
    UtenteDTO createUtente(UtenteRegistrationDTO utenteRegistrazioneDTO);
    UtenteDTO updateUtente(int id, UtenteDTO utenteDTO);
    void deleteUtente(int id);
    
}
