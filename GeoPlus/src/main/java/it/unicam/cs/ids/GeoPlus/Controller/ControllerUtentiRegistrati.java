package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziUtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/utenti")
public class ControllerUtentiRegistrati {
    @Autowired
    private ServiziUtenteRegistrato serviziUtenteRegistrato;


    @PostMapping("/assegnaRuolo/{idCuratore}/{idUtente}")
    public ResponseEntity<String> asseganRuoloUtente(@PathVariable long idCuratore, @PathVariable long idUtente, @Valid @RequestParam Ruoli ruolo) {
        UtenteStandard utente = serviziUtenteRegistrato.getUtenteStandard(idUtente);
        UtenteRegistrato curatore = serviziUtenteRegistrato.getUtenteStandard(idCuratore);
        if (Objects.isNull(utente)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (Objects.equals(utente.getComuneAppartenenza().getIdComune(), curatore.getComuneAppartenenza().getIdComune())) {
            utente.setRuoloUtente(ruolo);
            serviziUtenteRegistrato.salvaUtente(utente);
            return ResponseEntity.ok("Ruolo assegnato con successo all'utente selezionato");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("L'utente non appartiene al comune");
    }

    @GetMapping("/listaUtentiComune/{idAmministratore}")
    public ResponseEntity<List<UtenteRegistrato>> ottieniListaUtentiComune(@PathVariable long idAmministratore) {
        UtenteRegistrato utente = serviziUtenteRegistrato.getUtenteStandard(idAmministratore);
        if (verificaCuratore(utente)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        Comune comune = utente.getComuneAppartenenza();
        if (comune == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(serviziUtenteRegistrato.getListaUtentiComune(comune));
    }

    private boolean verificaCuratore(UtenteRegistrato utente) {
        return !Objects.equals(utente.getRuoloUtente(), Ruoli.AMMINISTRATORE_COMUNALE);
    }

}

