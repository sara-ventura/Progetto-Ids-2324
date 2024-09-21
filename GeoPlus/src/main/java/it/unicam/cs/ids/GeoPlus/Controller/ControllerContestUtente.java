package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.ContenutoMultimediale;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.ContenutoTestuale;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.InvitoContest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Gestori.GestoreInvitiContest;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.*;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.ContenutoMultimedialeBody;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.ContenutoTestualeBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/contest")
public class ControllerContestUtente {

    @Autowired
    private GestoreInvitiContest gestoreInvitiContest;

    @Autowired
    private ServiziUtenteRegistrato serviziUtenteRegistrato;

    @Autowired
    private ServiziContest serviziContest;

    @Autowired
    private ServiziContenuto serviziContenuto;

    @Autowired
    private ServiziRichieste serviziRichieste;

    @Autowired
    private ServiziPoi serviziPoi;

    @Autowired
    private ServiziComune serviziComune;

    @PostMapping("/accettaInvito/{idUtente}/{idInvito}")
    public ResponseEntity<String> accettaInvitoContest(@PathVariable Long idUtente, @PathVariable Long idInvito) {
        InvitoContest invitoContest = validaInvito(idInvito, idUtente);
        gestoreInvitiContest.accettaInvitoContest(invitoContest);
        return ResponseEntity.ok("Invito accettato con successo");
    }

    @PostMapping("/rifiutaInvito/{idUtente}/{idInvito}")
    public ResponseEntity<String> rifiutaInvitoContest(@PathVariable Long idUtente, @PathVariable Long idInvito) {
        InvitoContest invitoContest = validaInvito(idInvito, idUtente);
        gestoreInvitiContest.rifiutaInvitoContest(invitoContest);
        return ResponseEntity.ok("Invito rifiutato con successo");
    }

    @PostMapping("/iscrivitiContest/{idUtente}/{idContest}")
    public ResponseEntity<String> iscrivitiContest(@PathVariable Long idUtente, @PathVariable Long idContest) {
        UtenteStandard utente = validaUtente(idUtente);
        Contest contest = validaContest(idContest);
        if (contest.isUtentePartecipante(utente)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Utente già iscritto al contest");
        }
        contest.aggiungiPartecipanteContest(utente);
        serviziContest.salvaContest(contest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Iscrizione al contest avvenuta con successo");
    }

    @PostMapping("/creaContenutoTestuale/{idContest}")
    public ResponseEntity<String> creaContenutoTestuale(@PathVariable Long idContest, @Valid @RequestBody ContenutoTestualeBody contenutoTestualeBody) {
        Contest contest = validaContest(idContest);
        UtenteStandard autore = validaAutore(contenutoTestualeBody.getIdAutoreCaricamento(), contest);
        Poi poi = validaPoi(contenutoTestualeBody.getIdPoi(), contest);
        Comune comune = serviziComune.getComune(poi.getPosizionePoi());
        ContenutoTestuale contenutoTestuale = serviziContenuto.creaContenutoTestuale(poi, contenutoTestualeBody.getTesto());
        contenutoTestuale.setIdAutore(contenutoTestualeBody.getIdAutoreCaricamento());
        if (verificaUtente(autore, comune)) {
            contenutoTestuale.setApprovato(true);
            serviziContenuto.salvaContenuto(contenutoTestuale);
            contest.aggiungiContenutoContest(contenutoTestuale);
            serviziContest.salvaContest(contest);
            return ResponseEntity.ok("Contenuto testuale creato, approvato e aggiunto al contest con successo.");
        } else {
            contenutoTestuale.setApprovato(false);
            serviziContenuto.salvaContenuto(contenutoTestuale);
            serviziRichieste.creaRichiestaCaricamentoContenutoContest(autore, contenutoTestuale, contest, comune);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento del contenuto testuale su Contest creata con successo.");
        }
    }

    @PostMapping("/creaContenutoMultimediale/{idContest}")
    public ResponseEntity<String> creaContenutoMultimediale(@PathVariable Long idContest, @Valid @RequestBody ContenutoMultimedialeBody contenutoMultimedialeBody) {
        Contest contest = validaContest(idContest);
        UtenteStandard autore = validaAutore(contenutoMultimedialeBody.getIdAutoreCaricamento(), contest);
        Poi poi = validaPoi(contenutoMultimedialeBody.getIdPoi(), contest);
        Comune comune = serviziComune.getComune(poi.getPosizionePoi());
        ContenutoMultimediale contenutoMultimediale = serviziContenuto.creaContenutoMultimediale(poi, contenutoMultimedialeBody.getFile(), contenutoMultimedialeBody.getTesto());
        contenutoMultimediale.setIdAutore(contenutoMultimedialeBody.getIdAutoreCaricamento());
        if (verificaUtente(autore, comune)) {
            contenutoMultimediale.setApprovato(true);
            serviziContenuto.salvaContenuto(contenutoMultimediale);
            contest.aggiungiContenutoContest(contenutoMultimediale);
            serviziContest.salvaContest(contest);
            return ResponseEntity.ok("Contenuto multimediale creato, approvato e aggiunto al contest con successo.");
        } else {
            contenutoMultimediale.setApprovato(false);
            serviziContenuto.salvaContenuto(contenutoMultimediale);
            serviziRichieste.creaRichiestaCaricamentoContenutoContest(autore, contenutoMultimediale, contest, comune);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento del contenuto multimediale su Contest creata con successo.");
        }
    }

    @DeleteMapping("/cancellaContenuto/{idUtente}/{idContest}/{idContenuto}")
    public ResponseEntity<String> cancellaContenuto(@PathVariable Long idUtente, @PathVariable Long idContest, @PathVariable Long idContenuto) {
        validaUtente(idUtente);
        Contest contest = validaContest(idContest);
        Contenuto contenuto = validaContenuto(idContenuto);
        if (!contenuto.getIdAutore().equals(idUtente)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utente non autorizzato a cancellare questo contenuto");
        }
        contest.rimuoviContenutoContest(contenuto);
        serviziContenuto.eliminaContenuto(contenuto);
        serviziContest.salvaContest(contest);
        return ResponseEntity.ok("Contenuto cancellato con successo");
    }

    private InvitoContest validaInvito(Long idInvito, Long idUtente) {
        InvitoContest invitoContest = serviziContest.getInvito(idInvito);
        if (invitoContest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invito non trovato");
        }
        if (!invitoContest.getUtenteInvitato().getIdUtente().equals(idUtente)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Utente non autorizzato per questo invito");
        }
        return invitoContest;
    }

    private UtenteStandard validaUtente(Long idUtente) {
        UtenteStandard utente = serviziUtenteRegistrato.getUtenteStandard(idUtente);
        if (utente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
        return utente;
    }

    private Contest validaContest(Long idContest) {
        Contest contest = serviziContest.getContest(idContest);
        if (contest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contest non trovato");
        }
        return contest;
    }

    private UtenteStandard validaAutore(Long idAutore, Contest contest) {
        UtenteStandard autore = serviziUtenteRegistrato.getUtenteStandard(idAutore);
        if (autore == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autore non trovato");
        }
        if (!contest.isUtentePartecipante(autore)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Autore non iscritto al contest");
        }
        return autore;
    }

    private Poi validaPoi(Long idPoi, Contest contest) {
        Poi poi = serviziPoi.getPoi(idPoi);
        if (poi == null || !contest.getRiferimenti().contains(poi)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "POI non trovato");
        }
        return poi;
    }

    private Contenuto validaContenuto(Long idContenuto) {
        Contenuto contenuto = serviziContenuto.getContenuto(idContenuto);
        if (contenuto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenuto non trovato");
        }
        return contenuto;
    }
    private boolean verificaUtente(UtenteRegistrato autoreCaricamento, Comune comune) {
        Ruoli ruolo = autoreCaricamento.getRuoloUtente();
        return (ruolo.toString().equals(Ruoli.CONTRIBUTOR_AUTORIZZATO.toString()) ||
                ruolo.toString().equals(Ruoli.CURATORE.toString()) ||
                ruolo.toString().equals(Ruoli.ANIMATORE.toString())) &&
                autoreCaricamento.getComuneAppartenenza().toString().equals(comune.toString());
    }
}