package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Richiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaSuContest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Gestori.GestoreRichieste;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziContest;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziPoi;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziRichieste;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziUtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.ContestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ServiziUtenteRegistrato serviziUtente;

    @Autowired
    private ServiziPoi serviziPoi;

    @Autowired
    private GestoreRichieste gestoreRichieste;
    @Autowired
    private ServiziRichieste serviziRichieste;

    @PostMapping("/creaContest")
    public ResponseEntity<String> creaContest(@Valid @RequestBody ContestBody contestBody) {
        UtenteStandard autoreContest = validaAnimatore(contestBody.getAutoreContestId());
        List<Poi> riferimenti = validaPoiList(contestBody.getPoiIds());
        serviziContest.creaContest(
                contestBody.getNomeContest(),
                contestBody.getDescrizione(),
                autoreContest,
                contestBody.getRegole(),
                contestBody.isPubblico(),
                contestBody.getPeriodoTempo(),
                riferimenti
        );

        return new ResponseEntity<>("Contest creato con successo", HttpStatus.CREATED);
    }

    @PostMapping("/invitaUtente/{idAnimatore}/{idContest}/{idUtente}")
    public ResponseEntity<String> invitaUtente(@PathVariable Long idAnimatore, @PathVariable Long idContest, @PathVariable Long idUtente) {
        validaAnimatore(idAnimatore);
        Contest contest = validaContest(idContest);
        validaUtenteInvito(contest, idUtente);
        serviziContest.creaInvito(serviziUtente.getUtenteStandard(idUtente), contest);
        return new ResponseEntity<>("Invito creato con successo", HttpStatus.CREATED);
    }

    @PostMapping("/accettaRichiesta/{idAnimatore}/{idRichiesta}")
    public ResponseEntity<String> accettaRichiesta(@PathVariable Long idAnimatore, @PathVariable Long idRichiesta) {
        validaAnimatore(idAnimatore);
        Richiesta richiesta = serviziRichieste.getRichiesta(idRichiesta);
        ResponseEntity<String> response = validaRichiesta(richiesta);
        if (response != null) {
            return response;
        }
        gestoreRichieste.approvaRichiesta(richiesta);
        return new ResponseEntity<>("Richiesta accettata con successo", HttpStatus.OK);
    }

    @PostMapping("/rifiutaRichiesta/{idAnimatore}/{idRichiesta}")
    public ResponseEntity<String> rifiutaRichiesta(@PathVariable Long idAnimatore, @PathVariable Long idRichiesta) {
        validaAnimatore(idAnimatore);
        Richiesta richiesta = serviziRichieste.getRichiesta(idRichiesta);
        ResponseEntity<String> response = validaRichiesta(richiesta);
        if (response != null) {
            return response;
        }
        gestoreRichieste.rifiutaRichiesta(richiesta);

        return new ResponseEntity<>("Richiesta rifiutata con successo", HttpStatus.OK);
    }

    @PostMapping("/decretaVincitore/{idAnimatore}/{idContest}/{idPartecipante}")
    public ResponseEntity<String> decretaVincitore(@PathVariable Long idAnimatore, @PathVariable Long idContest, @PathVariable Long idPartecipante) {
        validaAnimatore(idAnimatore);
        Contest contest = validaContest(idContest);
        if (contest.getVincitoreContest() != null) {
            return new ResponseEntity<>("Il contest ha già un vincitore.", HttpStatus.BAD_REQUEST);
        }
        UtenteStandard partecipante = validaUtente(idPartecipante);
        if (!contest.isUtentePartecipante(partecipante)) {
            return new ResponseEntity<>("L'utente specificato non è un partecipante del contest.", HttpStatus.BAD_REQUEST);
        }
        contest.setVincitoreContest(partecipante);
        serviziContest.salvaContest(contest);
        return new ResponseEntity<>("Vincitore decretato con successo.", HttpStatus.OK);
    }

    private UtenteStandard validaUtente(Long idUtente) {
        UtenteStandard utente = serviziUtente.getUtenteStandard(idUtente);
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
        UtenteStandard utenteDaInvitare = serviziUtente.getUtenteStandard(idUtente);
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

    private UtenteStandard validaAnimatore(long idUtente) {
        UtenteStandard utente = serviziUtente.getUtenteStandard(idUtente);
        if (utente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
        if (!utente.getRuoloUtente().equals(Ruoli.ANIMATORE)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "L'utente non è autorizzato");
        }
        return utente;
    }
}