package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Gestori.GestoreSalvataggi;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziComune;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziContenuto;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziSegnalazioni;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziUtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/operazioni")
public class ControllerOperazioniUtente {

    @Autowired
    private ServiziUtenteRegistrato serviziUtenteRegistrato;

    @Autowired
    private ServiziSegnalazioni serviziSegnalazioni;

    @Autowired
    private ServiziContenuto serviziContenuto;

    @Autowired
    private ServiziComune serviziComune;

    @Autowired
    private GestoreSalvataggi gestoreSalvataggi;


    @PostMapping("/segnalaContenuto/{idContenuto}/{idUtente}")
    public ResponseEntity<String> segnalaContenuto(@PathVariable long idContenuto, @PathVariable long idUtente, @RequestBody String motivoSegnalazione) {
        validaUtente(idUtente);
        Comune comune = serviziComune.getComune(validaContenuto(idContenuto).getPoi().getPosizionePoi());
        if (comune == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
        serviziSegnalazioni.creaSegnalazione(
                serviziComune.getComune(comune.getIdComune()),
                serviziUtenteRegistrato.getUtenteStandard(idUtente),
                serviziContenuto.getContenuto(idContenuto), motivoSegnalazione
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("La segnalazione è stata effettuata con successo.");
    }

    @PostMapping("/salvaContenuto/{idContenuto}/{idUtente}")
    public ResponseEntity<String> salvaContenuto(@PathVariable long idContenuto, @PathVariable long idUtente) {
        validaUtente(idUtente);
        validaContenuto(idContenuto);
        boolean salvato = gestoreSalvataggi.salvaContenuto(
                serviziContenuto.getContenuto(idContenuto),
                serviziUtenteRegistrato.getUtenteStandard(idUtente)
        );
        if (salvato) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Il contenuto è stato salvato con successo");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contenuto già salvato");
    }

    @PostMapping("/rimuoviContenutoSalvato/{idContenuto}/{idUtente}")
    public ResponseEntity<String> rimuoviContenutoSalvato(@PathVariable long idContenuto, @PathVariable long idUtente) {
        validaUtente(idUtente);
        validaContenuto(idContenuto);
        boolean rimosso = gestoreSalvataggi.eliminaContenutoDaiSalvati(
                serviziContenuto.getContenuto(idContenuto),
                serviziUtenteRegistrato.getUtenteStandard(idUtente)
        );
        if (rimosso) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Il contenuto è stato rimosso con successo");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Il contenuto non è stato trovato nei salvataggi");
    }

    private void validaUtente(Long idUtente) {
        UtenteStandard utente = serviziUtenteRegistrato.getUtenteStandard(idUtente);
        if (utente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
    }

    private Contenuto validaContenuto(Long idContenuto) {
        Contenuto contenuto = serviziContenuto.getContenuto(idContenuto);
        if (contenuto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenuto non trovato");
        }
        return contenuto;
    }
}

