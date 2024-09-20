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
@RequestMapping("/operazioni")
public class ControllerOperazioniUtente {
    @Autowired
    private GestoreSegnalazioni gestoreSegnalazioni;

    @Autowired
    private ServiziUtenteRegistrato serviziUtente;

    @Autowired
    private ServiziSegnalazioni serviziSegnalazioni;

    @PostMapping("/accetta/{segnalazioneId}/{curatoreId}")
    public ResponseEntity<String> accettaSegnalazione(@PathVariable long segnalazioneId, @PathVariable long curatoreId) {
        ResponseEntity<String> utenteResponse = verificaUtente(curatoreId);
        if (utenteResponse != null) {
            return utenteResponse;
        }

        Segnalazione segnalazione = serviziSegnalazioni.getSegnalazione(segnalazioneId);
        ResponseEntity<String> segnalazioneResponse = verificaSegnalazione(segnalazione);
        if (segnalazioneResponse != null) {
            return segnalazioneResponse;
        }

        gestoreSegnalazioni.accettaSegnalazione(segnalazione);
        return new ResponseEntity<>("Segnalazione accettata e contenuto eliminato", HttpStatus.OK);
    }

    @PostMapping("/rifiuta/{segnalazioneId}/{curatoreId}")
    public ResponseEntity<String> rifiutaSegnalazione(@PathVariable long segnalazioneId, @PathVariable long curatoreId) {
        ResponseEntity<String> utenteResponse = verificaUtente(curatoreId);
        if (utenteResponse != null) {
            return utenteResponse;
        }

        Segnalazione segnalazione = serviziSegnalazioni.getSegnalazione(segnalazioneId);
        ResponseEntity<String> segnalazioneResponse = verificaSegnalazione(segnalazione);
        if (segnalazioneResponse != null) {
            return segnalazioneResponse;
        }

        gestoreSegnalazioni.rifiutaSegnalzione(segnalazione);
        return new ResponseEntity<>("Segnalazione rifiutata e cancellata", HttpStatus.OK);
    }

    @GetMapping("/listaSegnalazioniComune/{comuneId}/{curatoreId}")
    public ResponseEntity<List<Segnalazione>> ottieniSegnalazioniComune(@PathVariable long comuneId, @PathVariable long curatoreId) {
        ResponseEntity<String> utenteResponse = verificaUtente(curatoreId);
        if (utenteResponse != null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        UtenteRegistrato utente = serviziUtente.getUtente(curatoreId);
        return ResponseEntity.ok(serviziSegnalazioni.getSegnalazioniPerComune(utente.getComuneAppartenenza()));
    }


    private ResponseEntity<String> verificaUtente(long curatoreId) {
        UtenteRegistrato utente = serviziUtente.getUtente(curatoreId);
        if (utente == null || !Objects.equals(utente.getRuoloUtente(), Ruoli.CURATORE)) {
            return new ResponseEntity<>("L'utente non è autorizzato", HttpStatus.FORBIDDEN);
        }
        return null;
    }

    private ResponseEntity<String> verificaSegnalazione(Segnalazione segnalazione) {
        if (segnalazione == null) {
            return new ResponseEntity<>("Segnalazione non trovata", HttpStatus.NOT_FOUND);
        }
        return null;

    }}

