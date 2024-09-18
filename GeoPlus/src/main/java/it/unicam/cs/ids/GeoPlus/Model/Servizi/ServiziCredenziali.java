package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.CredenzialiUtente;
import it.unicam.cs.ids.GeoPlus.Model.Repository.CredenzialiUtenteRepository;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.EmailGiaUtilizzata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
/**
 * Servizio che gestisce le operazioni relative alle credenziali degli utenti.
 * Fornisce metodi per creare, eliminare, recuperare e verificare le credenziali utente.
 * Utilizza {@link CredenzialiUtenteRepository} per accedere al database.
 */
@Service
public class ServiziCredenziali {

    @Autowired
    private CredenzialiUtenteRepository credenzialiUtenteRepository;

    /**
     * Crea un nuovo oggetto {@link CredenzialiUtente} con l'email e la password fornite.
     * Se l'email è già associata a un'altra credenziale, viene lanciata un'eccezione {@link EmailGiaUtilizzata}.
     *
     * @param email l'email dell'utente da registrare.
     * @param password la password dell'utente da registrare.
     * @return l'oggetto {@link CredenzialiUtente} appena creato e salvato.
     * @throws EmailGiaUtilizzata se l'email è già in uso da un altro profilo.
     */
    public CredenzialiUtente creaCredenziali(String email, String password) {
        if (credenzialiUtenteRepository.findByEmail(email).isPresent()) {
            throw new EmailGiaUtilizzata();
        }
        CredenzialiUtente credenzialiUtente = new CredenzialiUtente(email, password);
        credenzialiUtenteRepository.save(credenzialiUtente);
        return credenzialiUtente;
    }

    /**
     * Elimina le credenziali fornite dal database.
     *
     * @param credenziali l'oggetto {@link CredenzialiUtente} da eliminare.
     */
    public void eliminaCredenziali(CredenzialiUtente credenziali) {
        credenzialiUtenteRepository.delete(credenziali);
    }

    /**
     * Recupera le credenziali di un utente dato il suo ID.
     *
     * @param id l'ID dell'utente.
     * @return l'oggetto {@link CredenzialiUtente} associato all'ID specificato, oppure {@code null} se non esiste.
     */
    public CredenzialiUtente getCredenziali(Long id) {
        return credenzialiUtenteRepository.findById(id).orElse(null);
    }

    /**
     * Recupera le credenziali di un utente dato la sua email.
     *
     * @param email l'email dell'utente.
     * @return l'oggetto {@link CredenzialiUtente} associato all'email specificata, oppure {@code null} se non esiste.
     */
    public CredenzialiUtente getCredenziali(String email) {
        return credenzialiUtenteRepository.findByEmail(email).orElse(null);
    }

    /**
     * Verifica se la password fornita corrisponde alla password associata alle credenziali dell'utente specificato.
     *
     * @param password la password da verificare.
     * @param idCredenziali l'ID delle credenziali dell'utente.
     * @return {@code true} se la password corrisponde, {@code false} altrimenti.
     */
    public boolean verificaPassword(String password, Long idCredenziali) {
        return Objects.equals(password, this.getCredenziali(idCredenziali).getPassword());
    }
}
