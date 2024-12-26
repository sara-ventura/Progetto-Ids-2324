package it.unicam.cs.ids.GeoPlus.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Segnalazione;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Gestori.GestoreSegnalazioni;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziAccount;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziSegnalazioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gestione/segnalazioni")
public class ControllerGestioneSegnalazioni {

    @Autowired
    private GestoreSegnalazioni gestoreSegnalazioni;

    @Autowired
    private ServiziAccount serviziAccount;

    @Autowired
    private ServiziSegnalazioni serviziSegnalazioni;


    @PostMapping("/accetta/{segnalazioneId}")
    public ResponseEntity<String> accettaSegnalazione(@PathVariable long segnalazioneId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account curatore = (Account) authentication.getPrincipal();
        curatore = serviziAccount.getAccountById(curatore.getId());
        if (verificaCuratore(curatore)) {
            return new ResponseEntity<>("L'utente non è autorizzato", HttpStatus.FORBIDDEN);
        }
        Segnalazione segnalazione = serviziSegnalazioni.getSegnalazione(segnalazioneId);
        if (segnalazione == null) {
            return new ResponseEntity<>("Segnalazione non trovata", HttpStatus.NOT_FOUND);
        }
        gestoreSegnalazioni.accettaSegnalazione(segnalazione);
        return new ResponseEntity<>("Segnalazione accettata e contenuto eliminato", HttpStatus.OK);
    }

    @PostMapping("/rifiuta/{segnalazioneId}")
    public ResponseEntity<String> rifiutaSegnalazione(@PathVariable long segnalazioneId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account curatore = (Account) authentication.getPrincipal();
        curatore = serviziAccount.getAccountById(curatore.getId());
        if (verificaCuratore(curatore)) {
            return new ResponseEntity<>("L'utente non è autorizzato", HttpStatus.FORBIDDEN);
        }
        Segnalazione segnalazione = serviziSegnalazioni.getSegnalazione(segnalazioneId);
        if (segnalazione == null) {
            return new ResponseEntity<>("Segnalazione non trovata", HttpStatus.NOT_FOUND);
        }
        gestoreSegnalazioni.rifiutaSegnalzione(segnalazione);
        return new ResponseEntity<>("Segnalazione rifiutata e cancellata", HttpStatus.OK);
    }

    @GetMapping("/listaSegnalazioniComune/{comuneId}")
    public ResponseEntity<List<Segnalazione>> ottieniSegnalazioniComune() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account curatore = (Account) authentication.getPrincipal();
        curatore = serviziAccount.getAccountById(curatore.getId());
        if (verificaCuratore(curatore)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(serviziSegnalazioni.getSegnalazioniPerComune(curatore.getComuneAppartenenza()));
    }

    private boolean verificaCuratore(Account utente) {
        return !Objects.equals(utente.getRuoloUtente(), Ruoli.CURATORE);
    }
}

