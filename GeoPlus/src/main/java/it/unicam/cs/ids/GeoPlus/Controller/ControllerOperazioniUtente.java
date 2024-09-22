package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Gestori.GestoreSalvataggi;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziComune;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziContenuto;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziSegnalazioni;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziUtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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



    @PostMapping("/segnalaContenuto/{idComune}/{idContenuto}/{idUtente}")
    public ResponseEntity<String> segnalaContenuto(@PathVariable long idComune, @PathVariable long idContenuto, @PathVariable long idUtente) {
        serviziSegnalazioni.creaSegnalazione(
                serviziComune.getComune(idComune),
                serviziUtenteRegistrato.getUtenteStandard(idUtente),
                serviziContenuto.getContenuto(idContenuto)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("La segnalazione è stata effettuata con successo.");
    }

    @PostMapping("/salvaContenuto/{idContenuto}/{idUtente}")
    public ResponseEntity<String> salvaContenuto(@PathVariable long idContenuto, @PathVariable long idUtente) {
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
        boolean rimosso = gestoreSalvataggi.eliminaContenutoDaiSalvati(
                serviziContenuto.getContenuto(idContenuto),
                serviziUtenteRegistrato.getUtenteStandard(idUtente)
        );
        if (rimosso) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Il contenuto è stato rimosso con successo");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Il contenuto non è stato trovato nei salvataggi");
    }
}

