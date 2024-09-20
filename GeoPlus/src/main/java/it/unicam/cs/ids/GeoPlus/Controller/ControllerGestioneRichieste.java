package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Richiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaSuContest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Gestori.GestoreRichieste;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziRichieste;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziUtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/gestione/richieste")
public class ControllerGestioneRichieste {

    @Autowired
    private GestoreRichieste gestoreRichieste;

    @Autowired
    private ServiziRichieste serviziRichieste;
    @Autowired
    private ServiziUtenteRegistrato serviziUtenteRegistrato;

    @PostMapping("/accettaRichiesta/{idCuratore}/{idRichiesta}")
    public ResponseEntity<String> accettaRichiesta(@PathVariable Long idCuratore, @PathVariable Long idRichiesta) {
        validateCuratore(idCuratore);
        validateRichiesta(idRichiesta);
        Richiesta richiesta = serviziRichieste.getRichiesta(idRichiesta);
        gestoreRichieste.approvaRichiesta(richiesta);
        return new ResponseEntity<>("Richiesta accettata con successo", HttpStatus.OK);
    }

    @PostMapping("/rifiutaRichiesta/{idCuratore}/{idRichiesta}")
    public ResponseEntity<String> rifiutaRichiesta(@PathVariable Long idCuratore, @PathVariable Long idRichiesta) {
        validateCuratore(idCuratore);
        validateRichiesta(idRichiesta);
        Richiesta richiesta = serviziRichieste.getRichiesta(idRichiesta);
        gestoreRichieste.rifiutaRichiesta(richiesta);
        return new ResponseEntity<>("Richiesta rifiutata con successo", HttpStatus.OK);
    }

    @GetMapping("/richiesteComune/{idCuratore}")
    public ResponseEntity<List<Richiesta>> getRichiestePerComune(@PathVariable Long idCuratore) {
        UtenteRegistrato curatore = validateCuratore(idCuratore);
        Comune comuneCuratore = curatore.getComuneAppartenenza();
        List<Richiesta> richiesteComune = serviziRichieste.getRichieste(comuneCuratore);
        return new ResponseEntity<>(richiesteComune, HttpStatus.OK);
    }

    private UtenteRegistrato validateCuratore(Long idCuratore) {
        UtenteRegistrato curatore = serviziUtenteRegistrato.getUtente(idCuratore);
        if (curatore == null) {
            System.out.println("Utente non trovato");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }

        if (!Ruoli.CURATORE.equals(curatore.getRuoloUtente())) {
            System.out.println("Ruolo utente non valido: " + curatore.getRuoloUtente());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "L'utente non è autorizzato");
        }

        System.out.println("Utente autorizzato con ruolo: " + curatore.getRuoloUtente());
        return curatore;
    }

    private void validateRichiesta(Long idRichiesta) {
        Richiesta richiesta = serviziRichieste.getRichiesta(idRichiesta);
        if (richiesta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Richiesta non trovata");
        }
        if (richiesta instanceof RichiestaSuContest) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non puoi gestire richieste di tipo Invito Su Contest");
        }
    }
}
