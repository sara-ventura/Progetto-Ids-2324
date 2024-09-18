package it.unicam.cs.ids.GeoPlus.Model.Repository;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.CredenzialiUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository per la gestione delle operazioni di persistenza degli oggetti {@link CredenzialiUtente}.
 * Questa interfaccia definisce metodi personalizzati per il recupero delle credenziali utente
 * in base all'email o all'ID dell'utente.
 *
 */
@Repository
public interface CredenzialiUtenteRepository extends JpaRepository<CredenzialiUtente, Long> {
    Optional<CredenzialiUtente> findByEmail(String email);


}
