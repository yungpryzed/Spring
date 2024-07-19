package it.guidera.gabriele.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.guidera.gabriele.model.Utente;
import java.util.Optional;

@Repository
public interface UtenteDAO extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByEmail(String email);
}
