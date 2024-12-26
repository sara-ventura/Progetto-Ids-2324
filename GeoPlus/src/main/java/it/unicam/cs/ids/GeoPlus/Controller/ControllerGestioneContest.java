package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Richiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaSuContest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Gestori.GestoreRichieste;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziAccount;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziContest;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziPoi;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziRichieste;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.ContestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gestione/contest")
public class ControllerGestioneContest {

    @Autowired
    private ServiziContest serviziContest;

    @Autowired
    private ServiziAccount serviziAccount;

    @Autowired
    private ServiziPoi serviziPoi;

    @Autowired
    private GestoreRichieste gestoreRichieste;
    @Autowired
    private ServiziRichieste serviziRichieste;

    @PostMapping("/creaContest")
    public ResponseEntity<String> creaContest(@Valid @RequestBody ContestBody contestBody) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        user = validaAnimatore(user);
        List<Poi> riferimenti = validaPoiList(contestBody.getPoiIds());
        serviziContest.creaContest(
                contestBody.getNomeContest(),
                contestBody.getDescrizione(),
                user,
                contestBody.getRegole(),
                contestBody.isPubblico(),
                contestBody.getPeriodoTempo(),
                riferimenti
        );

        return new ResponseEntity<>("Contest creato con successo", HttpStatus.CREATED);
    }

    @PostMapping("/invitaUtente/{idContest}/{idUtente}")
    public ResponseEntity<String> invitaUtente(@PathVariable Long idContest, @PathVariable Long idUtente) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        validaAnimatore(user);
        Contest contest = validaContest(idContest);
        validaUtenteInvito(contest, idUtente);
        serviziContest.creaInvito(serviziAccount.getAccountById(idUtente), contest);
        return new ResponseEntity<>("Invito creato con successo", HttpStatus.CREATED);
    }

    @PostMapping("/accettaRichiesta/{idRichiesta}")
    public ResponseEntity<String> accettaRichiesta( @PathVariable Long idRichiesta) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        validaAnimatore(user);
        Richiesta richiesta = serviziRichieste.getRichiesta(idRichiesta);
        ResponseEntity<String> response = validaRichiesta(richiesta);
        if (response != null) {
            return response;
        }
        gestoreRichieste.approvaRichiesta(richiesta);
        return new ResponseEntity<>("Richiesta accettata con successo", HttpStatus.OK);
    }

    @PostMapping("/rifiutaRichiesta/{idRichiesta}")
    public ResponseEntity<String> rifiutaRichiesta(@PathVariable Long idRichiesta) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        validaAnimatore(user);
        Richiesta richiesta = serviziRichieste.getRichiesta(idRichiesta);
        ResponseEntity<String> response = validaRichiesta(richiesta);
        if (response != null) {
            return response;
        }
        gestoreRichieste.rifiutaRichiesta(richiesta);

        return new ResponseEntity<>("Richiesta rifiutata con successo", HttpStatus.OK);
    }

    @PostMapping("/decretaVincitore/{idContest}/{idPartecipante}")
    public ResponseEntity<String> decretaVincitore(@PathVariable Long idContest, @PathVariable Long idPartecipante) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        validaAnimatore(user);
        Contest contest = validaContest(idContest);
        if (contest.getVincitoreContest() != null) {
            return new ResponseEntity<>("Il contest ha già un vincitore.", HttpStatus.BAD_REQUEST);
        }
        Account partecipante = validaUtente(idPartecipante);
        if (!contest.isUtentePartecipante(partecipante)) {
            return new ResponseEntity<>("L'utente specificato non è un partecipante del contest.", HttpStatus.BAD_REQUEST);
        }
        contest.setVincitoreContest(partecipante);
        serviziContest.salvaContest(contest);
        return new ResponseEntity<>("Vincitore decretato con successo.", HttpStatus.OK);
    }

    private Account validaUtente(Long idUtente) {
        Account utente = serviziAccount.getAccountById(idUtente);
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

    private List<Poi> validaPoiList(List<Long> poiIds) {
        List<Poi> riferimenti = new ArrayList<>();
        for (Long poiId : poiIds) {
            Poi poi = serviziPoi.getPoi(poiId);
            if (poi == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Uno o più POI non trovati");
            }
            riferimenti.add(poi);
        }
        return riferimenti;
    }

    private void validaUtenteInvito(Contest contest, Long idUtente) {
        if (contest.isPubblico()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Il contest è pubblico");
        }
        Account utenteDaInvitare = serviziAccount.getAccountById(idUtente);
        if (utenteDaInvitare == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
    }

    private ResponseEntity<String> validaRichiesta(Richiesta richiesta) {
        if (richiesta == null) {
            return new ResponseEntity<>("Richiesta non trovata", HttpStatus.NOT_FOUND);
        }
        if (!(richiesta instanceof RichiestaSuContest)) {
            return new ResponseEntity<>("La richiesta non è di tipo RichiestaSuContest", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private Account validaAnimatore(Account utente) {
        if (utente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
        if (!utente.getRuoloUtente().equals(Ruoli.ANIMATORE)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "L'utente non è autorizzato");
        }
        return utente;
    }
}