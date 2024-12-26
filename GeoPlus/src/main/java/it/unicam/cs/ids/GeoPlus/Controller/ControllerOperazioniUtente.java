package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import it.unicam.cs.ids.GeoPlus.Model.Gestori.GestoreSalvataggi;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziAccount;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziComune;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziContenuto;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziSegnalazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/operazioni")
public class ControllerOperazioniUtente {

    @Autowired
    private ServiziAccount serviziAccount;

    @Autowired
    private ServiziSegnalazioni serviziSegnalazioni;

    @Autowired
    private ServiziContenuto serviziContenuto;

    @Autowired
    private ServiziComune serviziComune;

    @Autowired
    private GestoreSalvataggi gestoreSalvataggi;


    @PostMapping("/segnalaContenuto/{idContenuto}")
    public ResponseEntity<String> segnalaContenuto(@PathVariable long idContenuto, @RequestBody String motivoSegnalazione) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account utente = (Account) authentication.getPrincipal();
        utente = serviziAccount.getAccountById(utente.getId());
        Comune comune = serviziComune.getComune(validaContenuto(idContenuto).getPoi().getPosizionePoi());
        if (comune == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
        serviziSegnalazioni.creaSegnalazione(
                serviziComune.getComune(comune.getIdComune()),
                serviziAccount.getAccountById(utente.getId()),
                serviziContenuto.getContenuto(idContenuto), motivoSegnalazione
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("La segnalazione è stata effettuata con successo.");
    }

    @PostMapping("/salvaContenuto/{idContenuto}")
    public ResponseEntity<String> salvaContenuto(@PathVariable long idContenuto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account utente = (Account) authentication.getPrincipal();
        utente = serviziAccount.getAccountById(utente.getId());
        validaContenuto(idContenuto);
        boolean salvato = gestoreSalvataggi.salvaContenuto(
                serviziContenuto.getContenuto(idContenuto),
                serviziAccount.getAccountById(utente.getId())
        );
        if (salvato) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Il contenuto è stato salvato con successo");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contenuto già salvato");
    }

    @PostMapping("/rimuoviContenutoSalvato/{idContenuto}")
    public ResponseEntity<String> rimuoviContenutoSalvato(@PathVariable long idContenuto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account utente = (Account) authentication.getPrincipal();
        utente = serviziAccount.getAccountById(utente.getId());
        validaContenuto(idContenuto);
        boolean rimosso = gestoreSalvataggi.eliminaContenutoDaiSalvati(
                serviziContenuto.getContenuto(idContenuto),
                serviziAccount.getAccountById(utente.getId())
        );
        if (rimosso) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Il contenuto è stato rimosso con successo");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Il contenuto non è stato trovato nei salvataggi");
    }

    private Contenuto validaContenuto(Long idContenuto) {
        Contenuto contenuto = serviziContenuto.getContenuto(idContenuto);
        if (contenuto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenuto non trovato");
        }
        return contenuto;
    }
}

