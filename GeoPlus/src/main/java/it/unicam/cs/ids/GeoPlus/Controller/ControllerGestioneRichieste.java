package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Richiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaSuContest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Gestori.GestoreRichieste;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziAccount;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziRichieste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gestione/richieste")
public class ControllerGestioneRichieste {

    @Autowired
    private GestoreRichieste gestoreRichieste;

    @Autowired
    private ServiziRichieste serviziRichieste;
    @Autowired
    private ServiziAccount serviziAccount;

    @PostMapping("/accettaRichiesta/{idRichiesta}")
    public ResponseEntity<String> accettaRichiesta(@PathVariable Long idRichiesta) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account curatore = (Account) authentication.getPrincipal();
        curatore = serviziAccount.getAccountById(curatore.getId());
        if (!Objects.equals(curatore.getRuoloUtente(), Ruoli.CURATORE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        Richiesta richiesta = serviziRichieste.getRichiesta(idRichiesta);
        validaRichiesta(richiesta, curatore);
        gestoreRichieste.approvaRichiesta(richiesta);
        return new ResponseEntity<>("Richiesta accettata con successo", HttpStatus.OK);
    }

    @PostMapping("/rifiutaRichiesta/{idRichiesta}")
    public ResponseEntity<String> rifiutaRichiesta(@PathVariable Long idRichiesta) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account curatore = (Account) authentication.getPrincipal();
        curatore = serviziAccount.getAccountById(curatore.getId());
        if (!Objects.equals(curatore.getRuoloUtente(), Ruoli.CURATORE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        Richiesta richiesta = serviziRichieste.getRichiesta(idRichiesta);
        validaRichiesta(richiesta, curatore);

        gestoreRichieste.rifiutaRichiesta(richiesta);
        return new ResponseEntity<>("Richiesta rifiutata con successo", HttpStatus.OK);
    }

    @GetMapping("/richiesteComune")
    public ResponseEntity<List<Richiesta>> getRichiestePerComune() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account curatore = (Account) authentication.getPrincipal();
        curatore = serviziAccount.getAccountById(curatore.getId());
        if (!Objects.equals(curatore.getRuoloUtente(), Ruoli.CURATORE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        Comune comuneCuratore = curatore.getComuneAppartenenza();
        List<Richiesta> richiesteComune = serviziRichieste.getRichieste(comuneCuratore);
        return new ResponseEntity<>(richiesteComune, HttpStatus.OK);
    }

    private void validaRichiesta(Richiesta richiesta, Account curatore) {
        if (!curatore.getComuneAppartenenza().equals(richiesta.getComune())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Richiesta non appartenete al comune");
        }
        if (richiesta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Richiesta non trovata");
        }
        if (richiesta instanceof RichiestaSuContest) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non puoi gestire richieste di tipo Invito Su Contest");
        }
    }
}
