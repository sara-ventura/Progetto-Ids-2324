package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.ContenutoMultimediale;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.ContenutoTestuale;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.*;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/caricamenti")
public class ControllerCaricamenti {
    @Autowired
    private ServiziPoi serviziPoi;
    @Autowired
    private ServiziComune serviziComune;
    @Autowired
    private ServiziRichieste serviziRichieste;
    @Autowired
    private ServiziContenuto serviziContenuto;
    @Autowired
    private ServiziUtenteRegistrato serviziUtenteRegistrato;
    @Autowired
    private ServiziItinerario serviziItinerario;


    @PostMapping("/Poi")
    public ResponseEntity<String> creaPoi(@Valid @RequestBody PoiBody poiBody) {
        Coordinate posizionePoi = poiBody.getPosizionePoi();
        Comune comunePoi = serviziComune.getComune(posizionePoi);
        Poi poi = serviziPoi.creaPoi(poiBody.getNomePoi(), poiBody.getDescrizionePoi(), posizionePoi, comunePoi);
        UtenteStandard autoreCaricamento = serviziUtenteRegistrato.getUtenteStandard(poiBody.getIdAutoreCaricamento());
        poi.setIdAutore(autoreCaricamento.getIdUtente());
        if (verificaUtente(autoreCaricamento, comunePoi)) {
            poi.setApprovato(true);
            serviziPoi.salvaPoi(poi, comunePoi);
            return ResponseEntity.ok("POI creato e salvato con successo.");
        } else {
            poi.setApprovato(false);
            serviziPoi.salvaPoi(poi, comunePoi);
            serviziRichieste.creaRichiestaCaricamento(autoreCaricamento, poi, comunePoi);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento creata con successo.");
        }
    }

    @PostMapping("/PoiTemporaneo")
    public ResponseEntity<String> creaPoiTemporaneo(@Valid @RequestBody PoiTemporaneoBody poiBody) {
        Coordinate posizionePoi = poiBody.getPosizionePoi();
        Comune comunePoi = serviziComune.getComune(posizionePoi);
        Poi poi = serviziPoi.creaPoi(poiBody.getNomePoi(), poiBody.getDescrizionePoi(), posizionePoi, comunePoi, poiBody.getPeriodoApertura());
        UtenteStandard autoreCaricamento =serviziUtenteRegistrato.getUtenteStandard( poiBody.getIdAutoreCaricamento());
        poi.setIdAutore(autoreCaricamento.getIdUtente());
        if (verificaUtente(autoreCaricamento, comunePoi)) {
            poi.setApprovato(true);
            serviziPoi.salvaPoi(poi, comunePoi);
            return ResponseEntity.ok("POI creato e salvato con successo.");
        } else {
            poi.setApprovato(false);
            serviziPoi.salvaPoi(poi, comunePoi);
            serviziRichieste.creaRichiestaCaricamento(autoreCaricamento, poi, comunePoi);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento creata con successo.");
        }
    }

    @PostMapping("/ContenutoTestuale")
    public ResponseEntity<String> creaContenutoTestuale(@Valid @RequestBody ContenutoTestualeBody contenutoTestualeBody) {
        ContenutoTestuale contenutoTestuale = serviziContenuto.creaContenutoTestuale(serviziPoi.getPoi(contenutoTestualeBody.getIdPoi()), contenutoTestualeBody.getTesto());
        Comune comuneContenuto = serviziComune.getComune(serviziPoi.getPoi(contenutoTestualeBody.getIdPoi()).getPosizionePoi());
        UtenteStandard autoreCaricamento =serviziUtenteRegistrato.getUtenteStandard( contenutoTestualeBody.getIdAutoreCaricamento());
        contenutoTestuale.setIdAutore(autoreCaricamento.getIdUtente());
        if (verificaUtente(autoreCaricamento, comuneContenuto)) {
            contenutoTestuale.setApprovato(true);
            serviziContenuto.salvaContenuto(contenutoTestuale);
            return ResponseEntity.ok("Contenuto creato e salvato con successo.");
        } else {
            contenutoTestuale.setApprovato(false);
            serviziContenuto.salvaContenuto(contenutoTestuale);
            serviziRichieste.creaRichiestaCaricamento(autoreCaricamento, contenutoTestuale, comuneContenuto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento creata con successo.");
        }
    }

    @PostMapping("/ContenutoMultimediale")
    public ResponseEntity<String> creaContenutoMultimediale(@Valid @RequestBody ContenutoMultimedialeBody contenutoMultimedialeBody) {
        ContenutoMultimediale contenutoMultimediale = serviziContenuto.creaContenutoMultimediale(serviziPoi.getPoi(contenutoMultimedialeBody.getIdPoi()), contenutoMultimedialeBody.getFile(), contenutoMultimedialeBody.getTesto());
        Comune comuneContenuto = serviziComune.getComune(serviziPoi.getPoi(contenutoMultimedialeBody.getIdPoi()).getPosizionePoi());
        UtenteStandard autoreCaricamento =serviziUtenteRegistrato.getUtenteStandard( contenutoMultimedialeBody.getIdAutoreCaricamento());
        contenutoMultimediale.setIdAutore(autoreCaricamento.getIdUtente());
        if (verificaUtente(autoreCaricamento, comuneContenuto)) {
            contenutoMultimediale.setApprovato(true);
            serviziContenuto.salvaContenuto(contenutoMultimediale);
            return ResponseEntity.ok("Contenuto creato e salvato con successo.");
        } else {
            contenutoMultimediale.setApprovato(false);
            serviziContenuto.salvaContenuto(contenutoMultimediale);
            serviziRichieste.creaRichiestaCaricamento(autoreCaricamento, contenutoMultimediale, comuneContenuto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento creata con successo.");
        }
    }

    @PostMapping("/Itinerario")
    public ResponseEntity<String> creaItinerario(@Valid @RequestBody ItinerarioBody itinerarioBody) {
        Comune comune = serviziComune.getComune(itinerarioBody.getIdComune());
        List<Long> listaIdPoi = itinerarioBody.getListaPoi();
        List<Poi> listaPoi = listaIdPoi.stream()
                .map(id -> serviziPoi.getPoi(id))
                .toList();
        Itinerario itinerario = serviziItinerario.creaItinerario(itinerarioBody.getNomeItinerario(), itinerarioBody.getDescrizioneItinerario(),comune, listaPoi);
        UtenteStandard autoreCaricamento =serviziUtenteRegistrato.getUtenteStandard( itinerarioBody.getIdAutoreCaricamento());
        itinerario.setIdAutore(autoreCaricamento.getIdUtente());
        if (verificaUtente(autoreCaricamento, comune)) {
            itinerario.setApprovato(true);
            serviziItinerario.salvaItinerario(itinerario, comune);
            return ResponseEntity.ok("Itinerario creato e salvato con successo.");
        } else {
            itinerario.setApprovato(false);
            serviziItinerario.salvaItinerario(itinerario, comune);
            serviziRichieste.creaRichiestaCaricamento(autoreCaricamento, itinerario, comune);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento creata con successo.");
        }
    }


    @DeleteMapping("/elimina/Poi/{idPoi}/{idUtente}")
    public ResponseEntity<String> eliminaPoi(@PathVariable Long idPoi, @PathVariable Long idUtente) {
        Poi poi = serviziPoi.getPoi(idPoi);
        if (poi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("POI non trovato.");
        }
        if (poi.getIdAutore().equals(idUtente)) {
            Comune comune = serviziComune.getComune(poi.getPosizionePoi());
            serviziPoi.eliminaPoi(poi, comune);
            return ResponseEntity.ok("POI eliminato con successo.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai i permessi per eliminare questo POI.");
        }
    }

    @DeleteMapping("/elimina/Contenuto/{idContenuto}/{idUtente}")
    public ResponseEntity<String> eliminaContenuto(@PathVariable Long idContenuto,  @PathVariable Long idUtente) {
        Contenuto contenuto = serviziContenuto.getContenuto(idContenuto);
        if (contenuto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contenuto non trovato.");
        }
        if (contenuto.getIdAutore().equals(idUtente)) {
            serviziContenuto.eliminaContenuto(contenuto);
            return ResponseEntity.ok("Contenuto eliminato con successo.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai i permessi per eliminare questo contenuto.");
        }
    }

    @DeleteMapping("/elimina/Itinerario/{id}/{idUtente}")
    public ResponseEntity<String> eliminaItinerario(@PathVariable Long id,  @PathVariable Long idUtente) {
        Itinerario itinerario = serviziItinerario.getItinerario(id);
        if (itinerario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Itinerario non trovato.");
        }
        if (itinerario.getIdAutore().equals(idUtente)) {
            serviziItinerario.eliminaItinerario(itinerario);
            return ResponseEntity.ok("Itinerario eliminato con successo.");
        } else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai i permessi per eliminare questo itinerario.");
        }
    }

    private boolean verificaUtente(UtenteRegistrato autoreCaricamento, Comune comune) {
        Ruoli ruolo = autoreCaricamento.getRuoloUtente();
        return (ruolo.toString().equals(Ruoli.CONTRIBUTOR_AUTORIZZATO.toString()) ||
                ruolo.toString().equals(Ruoli.CURATORE.toString()) ||
                ruolo.toString().equals(Ruoli.ANIMATORE.toString())) &&
                autoreCaricamento.getComuneAppartenenza().toString().equals(comune.toString());
    }
}
