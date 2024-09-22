package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Segnalazione;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Gestori.GestoreSegnalazioni;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziSegnalazioni;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziUtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gestione/segnalazioni")
public class ControllerGestioneSegnalazioni {

    @Autowired
    private GestoreSegnalazioni gestoreSegnalazioni;

    @Autowired
    private ServiziUtenteRegistrato serviziUtente;

    @Autowired
    private ServiziSegnalazioni serviziSegnalazioni;


    @PostMapping("/accetta/{segnalazioneId}/{curatoreId}")
    public ResponseEntity<String> accettaSegnalazione(@PathVariable long segnalazioneId, @PathVariable long curatoreId) {
        UtenteRegistrato utente = serviziUtente.getUtenteStandard(curatoreId);
        if (verificaCuratore(utente)) {
            return new ResponseEntity<>("L'utente non è autorizzato", HttpStatus.FORBIDDEN);
        }
        Segnalazione segnalazione = serviziSegnalazioni.getSegnalazione(segnalazioneId);
        if (segnalazione == null) {
            return new ResponseEntity<>("Segnalazione non trovata", HttpStatus.NOT_FOUND);
        }
        gestoreSegnalazioni.accettaSegnalazione(segnalazione);
        return new ResponseEntity<>("Segnalazione accettata e contenuto eliminato", HttpStatus.OK);
    }

    @PostMapping("/rifiuta/{segnalazioneId}/{curatoreId}")
    public ResponseEntity<String> rifiutaSegnalazione(@PathVariable long segnalazioneId, @PathVariable long curatoreId) {
        UtenteRegistrato utente = serviziUtente.getUtenteStandard(curatoreId);
        if (verificaCuratore(utente)) {
            return new ResponseEntity<>("L'utente non è autorizzato", HttpStatus.FORBIDDEN);
        }
        Segnalazione segnalazione = serviziSegnalazioni.getSegnalazione(segnalazioneId);
        if (segnalazione == null) {
            return new ResponseEntity<>("Segnalazione non trovata", HttpStatus.NOT_FOUND);
        }
        gestoreSegnalazioni.rifiutaSegnalzione(segnalazione);
        return new ResponseEntity<>("Segnalazione rifiutata e cancellata", HttpStatus.OK);
    }

    @GetMapping("/listaSegnalazioniComune/{comuneId}/{curatoreId}")
    public ResponseEntity<List<Segnalazione>> ottieniSegnalazioniComune(@PathVariable long comuneId, @PathVariable long curatoreId) {
        UtenteRegistrato utente = serviziUtente.getUtenteStandard(curatoreId);
        if (verificaCuratore(utente)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(serviziSegnalazioni.getSegnalazioniPerComune(utente.getComuneAppartenenza()));
    }

    private boolean verificaCuratore(UtenteRegistrato utente) {
        return !Objects.equals(utente.getRuoloUtente(), Ruoli.CURATORE);
    }
}

