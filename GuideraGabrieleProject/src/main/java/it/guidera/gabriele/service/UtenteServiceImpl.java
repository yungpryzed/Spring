package it.guidera.gabriele.service;

import it.guidera.gabriele.dao.UtenteDAO;
import it.guidera.gabriele.dto.UtenteDTO;
import it.guidera.gabriele.dto.UtenteRegistrationDTO;
import it.guidera.gabriele.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteDAO utenteDAO;

    @Override
    public List<UtenteDTO> getAllUtenti() {
        List<Utente> utenti = utenteDAO.findAll();
        List<UtenteDTO> utentiDTO = new ArrayList<>();
        for (Utente utente : utenti) {
            utentiDTO.add(convertToDto(utente));
        }
        return utentiDTO;
    }

    @Override
    public Optional<UtenteDTO> getUtenteById(int id) {
        Optional<Utente> utente = utenteDAO.findById(id);
        return utente.map(this::convertToDto);
    }

    @Override
    public Optional<UtenteDTO> getUtenteByEmail(String email) {
        Optional<Utente> utente = utenteDAO.findByEmail(email);
        return utente.map(this::convertToDto);
    }

    @Override
    public UtenteDTO createUtente(UtenteRegistrationDTO utenteRegistrazioneDTO) {
        Utente utente = new Utente();
        utente.setNome(utenteRegistrazioneDTO.getNome());
        utente.setCognome(utenteRegistrazioneDTO.getCognome());
        utente.setEmail(utenteRegistrazioneDTO.getEmail());
        utente.setPassword(utenteRegistrazioneDTO.getPassword());
        Utente savedUtente = utenteDAO.save(utente);
        return convertToDto(savedUtente);
    }

    @Override
    public UtenteDTO updateUtente(int id, UtenteDTO utenteDTO) {
        Utente utente = convertToEntity(utenteDTO);
        utente.setId(id);
        Utente updatedUtente = utenteDAO.save(utente);
        return convertToDto(updatedUtente);
    }

    @Override
    public void deleteUtente(int id) {
        utenteDAO.deleteById(id);
    }

    private UtenteDTO convertToDto(Utente utente) {
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setId(utente.getId());
        utenteDTO.setNome(utente.getNome());
        utenteDTO.setCognome(utente.getCognome());
        utenteDTO.setEmail(utente.getEmail());
        utenteDTO.setPassword(utente.getPassword());
        
      /*  List<RuoloDTO> ruoliDTO = new ArrayList<>();
        for (Ruolo ruolo : utente.getRuoli()) {
            RuoloDTO ruoloDTO = new RuoloDTO();
            ruoloDTO.setId(ruolo.getId());
            ruoloDTO.setTipologia(ruolo.getTipologia());
            ruoliDTO.add(ruoloDTO);
        }
        utenteDTO.setRuoli(ruoliDTO);

        List<CorsoDTO> corsiDTO = new ArrayList<>();
        for (Corso corso : utente.getCorsi()) {
            CorsoDTO corsoDTO = new CorsoDTO();
            corsoDTO.setId(corso.getId());
            corsoDTO.setNomeCorso(corso.getNomeCorso());
            corsiDTO.add(corsoDTO);
        }
        utenteDTO.setCorsi(corsiDTO);*/
        
        return utenteDTO;
    }

    private Utente convertToEntity(UtenteDTO utenteDTO) {
        Utente utente = new Utente();
        utente.setId(utenteDTO.getId());
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setEmail(utenteDTO.getEmail());
        utente.setPassword(utenteDTO.getPassword());
        
        return utente;
    }

	
}
