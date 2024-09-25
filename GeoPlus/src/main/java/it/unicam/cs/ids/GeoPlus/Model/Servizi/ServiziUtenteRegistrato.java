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

@Service
public class ServiziUtenteRegistrato {

    @Autowired
    private UtenteRegistratoRepository utenteRegistratoRepository;

    @Autowired
    private ServiziCredenziali serviziCredenziali;

    public UtenteStandard registraNuovoUtenteNonAutorizzato(String email, String password, Ruoli ruolo) {
        CredenzialiUtente credenzialiUtente = serviziCredenziali.creaCredenziali(email, password);
        UtenteStandard nuovoUtente = new UtenteStandard(ruolo, credenzialiUtente.getIdCredenziali());
        return utenteRegistratoRepository.save(nuovoUtente);
    }

    public UtenteStandard registraNuovoUtenteAutorizzato(String email, String password, Ruoli ruolo, Comune comune) {
        CredenzialiUtente credenzialiUtente = serviziCredenziali.creaCredenziali(email, password);
        UtenteStandard nuovoUtente = new UtenteStandard(ruolo, credenzialiUtente.getIdCredenziali());
        nuovoUtente.setComuneAppartenenza(comune);
        return utenteRegistratoRepository.save(nuovoUtente);
    }

    public AmministratoreComunale registraNuovoAmministratoreComunale(String email, String password) {
        CredenzialiUtente credenzialiUtente = serviziCredenziali.creaCredenziali(email, password);
        AmministratoreComunale nuovoAmministratore = new AmministratoreComunale(credenzialiUtente.getIdCredenziali());
        return utenteRegistratoRepository.save(nuovoAmministratore);
    }

    public UtenteRegistrato loginUtente(String email, String password) {
        CredenzialiUtente credenziali = serviziCredenziali.getCredenziali(email);
        if (credenziali == null) {
            throw new IllegalArgumentException("Credenziali non trovate");

        }
        if (serviziCredenziali.verificaPassword(password, credenziali.getIdCredenziali())) {
            return getUtente(email);
        }
        throw new IllegalArgumentException("Password errata");
    }

    public void salvaUtente(UtenteRegistrato utente) {
        utenteRegistratoRepository.save(utente);
    }

    public void eliminaUtente(UtenteStandard utente) {
        serviziCredenziali.eliminaCredenziali(serviziCredenziali.getCredenziali(utente.getIdCredenziali()));
        utenteRegistratoRepository.delete(utente);
    }

    public List<UtenteStandard> getAllUtentiStandard() {
        return utenteRegistratoRepository.findAllStandard();
    }

    public UtenteRegistrato getUtente(Long id) {
        return utenteRegistratoRepository.findById(id).orElse(null);
    }

    public UtenteRegistrato getUtente(String email) {
        return utenteRegistratoRepository.findByIdCredenziali(serviziCredenziali.getCredenziali(email).getIdCredenziali());
    }

    public UtenteStandard getUtenteStandard(Long id) {
        List<UtenteStandard> utentiStandard = utenteRegistratoRepository.findAllStandard();
        return utentiStandard.stream()
                .filter(utente -> utente.getIdUtente().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Utente non trovato o non è un UtenteStandard"));
    }

    public List<UtenteRegistrato> getListaUtentiComune(Comune comune) {
        return utenteRegistratoRepository.findByComuneAppartenenza(comune);
    }


}
