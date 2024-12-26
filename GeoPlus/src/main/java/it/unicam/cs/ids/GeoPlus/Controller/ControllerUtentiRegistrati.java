package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/utenti")
public class ControllerUtentiRegistrati {
    @Autowired
    private ServiziAccount serviziAccount;


    @PostMapping("/assegnaRuolo/{idUtente}")
    public ResponseEntity<String> asseganRuoloUtente(@PathVariable long idUtente, @Valid @RequestParam Ruoli ruolo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account curatore = (Account) authentication.getPrincipal();
        curatore = serviziAccount.getAccountById(curatore.getId());
        if (!Objects.equals(curatore.getRuoloUtente(), Ruoli.AMMINISTRATORE_COMUNALE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        Account utente = serviziAccount.getAccountById(idUtente);
        if (Objects.isNull(utente)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (Objects.equals(utente.getComuneAppartenenza().getIdComune(), curatore.getComuneAppartenenza().getIdComune())) {
            utente.setRuoloUtente(ruolo);
            serviziAccount.salvaUtente(utente);
            return ResponseEntity.ok("Ruolo assegnato con successo all'utente selezionato");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("L'utente non appartiene al comune");
    }

    @GetMapping("/listaUtentiComune")
    public ResponseEntity<List<Account>> ottieniListaUtentiComune() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account amministratore = (Account) authentication.getPrincipal();
        amministratore = serviziAccount.getAccountById(amministratore.getId());
        if (!Objects.equals(amministratore.getRuoloUtente(), Ruoli.AMMINISTRATORE_COMUNALE)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        Comune comune = amministratore.getComuneAppartenenza();
        if (comune == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(serviziAccount.getListaUtentiComune(comune));
    }

}

