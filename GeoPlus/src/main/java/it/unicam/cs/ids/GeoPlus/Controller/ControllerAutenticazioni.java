package it.unicam.cs.ids.GeoPlus.Controller;


import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.AmministratoreComunale;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziComune;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziUtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.RichiestaLogin;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.RichiestaRegistrazione;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.RichiestaRegistrazioneAmministratore;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.RichiestaRegistrazioneConComune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/autenticazioni")
public class ControllerAutenticazioni {
    @Autowired
    private ServiziUtenteRegistrato serviziUtenteRegistrato;

    @Autowired
    private ServiziComune serviziComune;


    @PostMapping("/registrazione/TuristaAut")
    public ResponseEntity<String> registrazioneUtenteTuristaAutenticato(@Valid @RequestBody RichiestaRegistrazione richiestaRegistrazioneUtenteNonAutorizzato) {
        UtenteStandard nuovoUtente = serviziUtenteRegistrato.registraNuovoUtenteNonAutorizzato(richiestaRegistrazioneUtenteNonAutorizzato.getEmail(), richiestaRegistrazioneUtenteNonAutorizzato.getPassword(), richiestaRegistrazioneUtenteNonAutorizzato.getRuolo());
        return ResponseEntity.ok("Registrazione avvenuta con successo: " + nuovoUtente);
    }


    @PostMapping("/registrazione/utente")
    public ResponseEntity<String> registrazioneUtente(@Valid @RequestBody RichiestaRegistrazioneConComune richiestaRegistrazioneUtenteAutorizzato) {
        UtenteStandard nuovoUtente;
        Comune comune = serviziComune.getComune(richiestaRegistrazioneUtenteAutorizzato.getComune());
        if (comune != null) {
            nuovoUtente = serviziUtenteRegistrato.registraNuovoUtenteAutorizzato(richiestaRegistrazioneUtenteAutorizzato.getEmail(), richiestaRegistrazioneUtenteAutorizzato.getPassword(), richiestaRegistrazioneUtenteAutorizzato.getRuolo(), comune);
            return ResponseEntity.ok("Registrazione avvenuta con successo: " + nuovoUtente);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comune non trovato.");
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody RichiestaLogin richiestaLogin) {
        Boolean success = serviziUtenteRegistrato.loginUtente(richiestaLogin.getEmail(), richiestaLogin.getPassword());
        if (!success) {
            return ResponseEntity.ok("Login effettuato con successo");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credenziali errate.");
    }

    @PostMapping("/registrazione/amministratore")
    public ResponseEntity<String> registerAdminAndCreateComune(@Valid @RequestBody RichiestaRegistrazioneAmministratore request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String nomeComune = request.getNomeComune();
        String descrizione = request.getDescrizione();
        Coordinate coordinateCentrali = request.getCoordinateCentrali();
        AmministratoreComunale nuovoAmministratore = serviziUtenteRegistrato.registraNuovoAmministratoreComunale(email, password);
        Comune comune = serviziComune.creaComune(nomeComune, descrizione, coordinateCentrali);
        nuovoAmministratore.setComuneAppartenenza(comune);
        serviziUtenteRegistrato.salvaUtente(nuovoAmministratore);
        return ResponseEntity.ok("Registrazione amministratore avvenuta con successo: " + nuovoAmministratore.getIdUtente() + " e comune creato con successo.");
    }
}