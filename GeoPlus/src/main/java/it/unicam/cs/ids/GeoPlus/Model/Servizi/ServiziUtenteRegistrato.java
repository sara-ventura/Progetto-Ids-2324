package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.AmministratoreComunale;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.CredenzialiUtente;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Repository.UtenteRegistratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servizio che gestisce le operazioni relative agli utenti registrati sulla piattaforma.
 * Fornisce funzionalità per la creazione di nuovi utenti e utilizza i servizi relativi alle credenziali
 * e il repository per gli utenti registrati.
 */
@Service
public class ServiziUtenteRegistrato {

    @Autowired
    private UtenteRegistratoRepository utenteRegistratoRepository;

    @Autowired
    private ServiziCredenziali serviziCredenziali;


    /**
     * Registra un nuovo utente non autorizzato, assegnando email, password e ruolo.
     *
     * @param email    l'email dell'utente.
     * @param password la password dell'utente.
     * @param ruolo    il ruolo dell'utente.
     * @return l'istanza di {@link UtenteStandard} appena registrato.
     */
    public UtenteStandard registraNuovoUtenteNonAutorizzato(String email, String password, Ruoli ruolo) {
        CredenzialiUtente credenzialiUtente = serviziCredenziali.creaCredenziali(email, password);
        UtenteStandard nuovoUtente = new UtenteStandard(ruolo, credenzialiUtente.getIdCredenziali());
        return utenteRegistratoRepository.save(nuovoUtente);
    }

    /**
     * Registra un nuovo utente autorizzato, assegnando email, password, ruolo e comune di appartenenza.
     *
     * @param email    l'email dell'utente.
     * @param password la password dell'utente.
     * @param ruolo    il ruolo dell'utente.
     * @param comune   il comune di appartenenza dell'utente.
     * @return l'istanza di {@link UtenteStandard} appena registrato.
     */
    public UtenteStandard registraNuovoUtenteAutorizzato(String email, String password, Ruoli ruolo, Comune comune) {
        CredenzialiUtente credenzialiUtente = serviziCredenziali.creaCredenziali(email, password);
        UtenteStandard nuovoUtente = new UtenteStandard(ruolo, credenzialiUtente.getIdCredenziali());
        nuovoUtente.setComuneAppartenenza(comune);
        return utenteRegistratoRepository.save(nuovoUtente);
    }

    /**
     * Registra un nuovo amministratore comunale con email e password.
     *
     * @param email    l'email dell'amministratore.
     * @param password la password dell'amministratore.
     * @return l'istanza di {@link AmministratoreComunale} appena registrato.
     */
    public AmministratoreComunale registraNuovoAmministratoreComunale(String email, String password) {
        CredenzialiUtente credenzialiUtente = serviziCredenziali.creaCredenziali(email, password);
        AmministratoreComunale nuovoAmministratore = new AmministratoreComunale(credenzialiUtente.getIdCredenziali());
        return utenteRegistratoRepository.save(nuovoAmministratore);
    }

    /**
     * Effettua il login di un utente verificando email e password.
     *
     * @param email    l'email dell'utente.
     * @param password la password dell'utente.
     * @return {@code true} se il login ha successo, {@code false} altrimenti.
     */
    public Boolean loginUtente(String email, String password) {
        CredenzialiUtente credenziali = serviziCredenziali.getCredenziali(email);
        if (credenziali == null) {
            return false;
        }
        return serviziCredenziali.verificaPassword(password, credenziali.getIdCredenziali());
    }

    /**
     * Salva un utente registrato nel repository.
     *
     * @param utente l'utente da salvare.
     */
    public void salvaUtente(UtenteRegistrato utente) {
        utenteRegistratoRepository.save(utente);
    }

    /**
     * Elimina un utente standard dal repository e rimuove le relative credenziali.
     *
     * @param utente l'utente da eliminare.
     */
    public void eliminaUtente(UtenteStandard utente) {
        serviziCredenziali.eliminaCredenziali(serviziCredenziali.getCredenziali(utente.getIdCredenziali()));
        utenteRegistratoRepository.delete(utente);
    }

    /**
     * Restituisce l'utente registrato associato a una determinata email.
     *
     * @param email l'email dell'utente.
     * @return l'utente registrato, o {@code null} se non trovato.
     */
    public UtenteRegistrato getUtente(String email) {
        return utenteRegistratoRepository.findByIdCredenziali(serviziCredenziali.getCredenziali(email).getIdCredenziali());
    }

    /**
     * Restituisce la lista di tutti gli utenti standard presenti nel sistema.
     *
     * @return una lista di {@link UtenteStandard}.
     */
    public List<UtenteStandard> getAllUtentiStandard() {
        return utenteRegistratoRepository.findAllStandard();
    }


    /**
     * Restituisce un utente standard dato il suo ID.
     *
     * @param id l'ID dell'utente.
     * @return l'utente standard, se trovato.
     * @throws RuntimeException se l'utente non è trovato o non è un utente standard.
     */
    public UtenteStandard getUtenteStandard(Long id) {
        List<UtenteStandard> utentiStandard = utenteRegistratoRepository.findAllStandard();
        return utentiStandard.stream()
                .filter(utente -> utente.getIdUtente().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Utente non trovato o non è un UtenteStandard"));
    }

    /**
     * Restituisce la lista degli utenti registrati appartenenti a un determinato comune.
     *
     * @param comune il comune di appartenenza.
     * @return una lista di {@link UtenteRegistrato} appartenenti al comune specificato.
     */
    public List<UtenteRegistrato> getListaUtentiComune(Comune comune) {
        return utenteRegistratoRepository.findByComuneAppartenenza(comune);
    }


}
