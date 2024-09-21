package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.*;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.RichiestaModificaOrarioBody;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.RichiestaModificaPeriodoTempoBody;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.RichiestaModificaTestoBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/utente/modifiche")
public class ControllerModifiche {
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
    public ResponseEntity<String> modificaTestoPoi(@Valid @RequestBody RichiestaModificaTestoBody richiesteModificaTestoBody) {
        Poi poi = serviziPoi.getPoi(richiesteModificaTestoBody.getEntitaId());
        if (poi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Poi non trovato");
        }
        UtenteStandard autoreRichiesta = serviziUtenteRegistrato.getUtenteStandard(richiesteModificaTestoBody.getAutoreRichiestaId());
        validateUtente(autoreRichiesta);
        if (poi.getIdAutore().equals(autoreRichiesta.getIdUtente())) {
            if (verificaAutorizzazioneUtente(autoreRichiesta)) {
                switch (richiesteModificaTestoBody.getTipoModifica()) {
                    case NOME -> poi.setNomePoi(richiesteModificaTestoBody.getModificaTesto());
                    case DESCRIZIONE -> poi.setDescrizionePoi(richiesteModificaTestoBody.getModificaTesto());
                }
                serviziPoi.aggiornaPoi(poi);
                return ResponseEntity.status(HttpStatus.OK).body(" testo aggiornato con successo");
            } else {
                serviziRichieste.creaRichiestaModificaTesto(autoreRichiesta, poi, serviziComune.getComune(poi.getPosizionePoi()), richiesteModificaTestoBody.getModificaTesto(), richiesteModificaTestoBody.getTipoModifica());
                return ResponseEntity.status(HttpStatus.OK).body("Richiesta di modifica creata con successo");
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai le autorizzazioni per modificare questo contenuto");
    }


    @PostMapping("/Itinerario")
    public ResponseEntity<String> modificaTestoItinerario(@Valid @RequestBody RichiestaModificaTestoBody richiesteModificaTestoBody) {
        Itinerario itinerario = serviziItinerario.getItinerario(richiesteModificaTestoBody.getEntitaId());
        if (itinerario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("itinerario non trovato");
        }
        UtenteStandard autoreRichiesta = serviziUtenteRegistrato.getUtenteStandard(richiesteModificaTestoBody.getAutoreRichiestaId());
        validateUtente(autoreRichiesta);
        if (itinerario.getIdAutore().equals(autoreRichiesta.getIdUtente())) {
            if (verificaAutorizzazioneUtente(autoreRichiesta)) {
                switch (richiesteModificaTestoBody.getTipoModifica()) {
                    case NOME -> itinerario.setNomeItinerario(richiesteModificaTestoBody.getModificaTesto());
                    case DESCRIZIONE ->
                            itinerario.setDescrizioneItinerario(richiesteModificaTestoBody.getModificaTesto());
                }
                serviziItinerario.aggiornaItinerario(itinerario);
                return ResponseEntity.status(HttpStatus.OK).body(" testo aggiornato con successo");
            } else {
                serviziRichieste.creaRichiestaModificaTesto(autoreRichiesta, itinerario, itinerario.getComune(), richiesteModificaTestoBody.getModificaTesto(), richiesteModificaTestoBody.getTipoModifica());
                return ResponseEntity.status(HttpStatus.OK).body("Richiesta di modifica creata con successo");
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai le autorizzazioni per modificare questo contenuto");
    }


    @PostMapping("/Contenuto")
    public ResponseEntity<String> modificaTestoContenuto(@Valid @RequestBody RichiestaModificaTestoBody richiesteModificaTestoBody) {
        Contenuto contenuto = serviziContenuto.getContenuto(richiesteModificaTestoBody.getEntitaId());
        if (contenuto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("contenuto non trovato");
        }
        UtenteStandard autoreRichiesta = serviziUtenteRegistrato.getUtenteStandard(richiesteModificaTestoBody.getAutoreRichiestaId());
        validateUtente(autoreRichiesta);
        if (contenuto.getIdAutore().equals(autoreRichiesta.getIdUtente())) {
            if (verificaAutorizzazioneUtente(autoreRichiesta)) {
                contenuto.setTesto(richiesteModificaTestoBody.getModificaTesto());
                serviziContenuto.aggiornaContenuto(contenuto);
                return ResponseEntity.status(HttpStatus.OK).body(" testo aggiornato con successo");
            } else {
                serviziRichieste.creaRichiestaModificaTesto(autoreRichiesta, contenuto, serviziComune.getComune(contenuto.getPoi().getPosizionePoi()), richiesteModificaTestoBody.getModificaTesto(), richiesteModificaTestoBody.getTipoModifica());
                return ResponseEntity.status(HttpStatus.OK).body("Richiesta di modifica creata con successo");
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai le autorizzazioni per modificare questo contenuto");
    }

    @PostMapping("/Poi/orario")
    public ResponseEntity<String> modificaOrarioPoi(@Valid @RequestBody RichiestaModificaOrarioBody richiestaModificaOrarioBody) {
        Poi poi = serviziPoi.getPoi(richiestaModificaOrarioBody.getPoiId());
        if (poi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Poi non trovato");
        }
        UtenteStandard autoreRichiesta = serviziUtenteRegistrato.getUtenteStandard(richiestaModificaOrarioBody.getAutoreRichiestaId());
        validateUtente(autoreRichiesta);
        if (poi.getIdAutore().equals(autoreRichiesta.getIdUtente())) {
            if (verificaAutorizzazioneUtente(autoreRichiesta)) {
                poi.setOrarioPoi(richiestaModificaOrarioBody.getGiorno(), richiestaModificaOrarioBody.getOrarioApertura(), richiestaModificaOrarioBody.getOrarioChiusura());
                serviziPoi.aggiornaPoi(poi);
                return ResponseEntity.status(HttpStatus.OK).body("Orario del POI aggiornato con successo");
            } else {
                serviziRichieste.creaRichiestaModificaOrario(autoreRichiesta, poi, serviziComune.getComune(poi.getPosizionePoi()), richiestaModificaOrarioBody.getGiorno(), richiestaModificaOrarioBody.getOrarioApertura(), richiestaModificaOrarioBody.getOrarioChiusura());
                return ResponseEntity.status(HttpStatus.OK).body("Richiesta di modifica creata con successo");
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai le autorizzazioni per modificare questo contenuto");
    }

    @PostMapping("/Poi/periodoTempo")
    public ResponseEntity<String> modificaPeriodotempoPoiT(@Valid @RequestBody RichiestaModificaPeriodoTempoBody richiestaModificaPeriodoTempoBody) {
        PoiTemporaneo poiTemporaneo = serviziPoi.getPoiTemporaneo(richiestaModificaPeriodoTempoBody.getPoiId());
        if (poiTemporaneo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Poi temporaneo non trovato");
        }
        UtenteStandard autoreRichiesta = serviziUtenteRegistrato.getUtenteStandard(richiestaModificaPeriodoTempoBody.getAutoreRichiestaId());
        validateUtente(autoreRichiesta);
        if (poiTemporaneo.getIdAutore().equals(autoreRichiesta.getIdUtente())) {
            if (verificaAutorizzazioneUtente(autoreRichiesta)) {
                poiTemporaneo.setDataApertura(richiestaModificaPeriodoTempoBody.getDataApertura());
                poiTemporaneo.setDataChiusura(richiestaModificaPeriodoTempoBody.getDataChiusura());
                serviziPoi.aggiornaPoi(poiTemporaneo);
                return ResponseEntity.status(HttpStatus.OK).body("Periodo di tempo del POI aggiornato con successo");
            } else {
                serviziRichieste.creaRichiestaModificaPeriodoTempo(
                        autoreRichiesta,
                        poiTemporaneo,
                        serviziComune.getComune(poiTemporaneo.getPosizionePoi()),
                        richiestaModificaPeriodoTempoBody.getDataApertura(),
                        richiestaModificaPeriodoTempoBody.getDataChiusura()
                );
                return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di modifica periodo creata con successo");
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai le autorizzazioni per modificare questo contenuto");
    }

    private void validateUtente(UtenteStandard utente) {
        if (utente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
    }


    private boolean verificaAutorizzazioneUtente(UtenteRegistrato autoreCaricamento) {
        Ruoli ruolo = autoreCaricamento.getRuoloUtente();
        return (ruolo.toString().equals(Ruoli.CONTRIBUTOR_AUTORIZZATO.toString()) ||
                ruolo.toString().equals(Ruoli.CURATORE.toString()) ||
                ruolo.toString().equals(Ruoli.ANIMATORE.toString()));
    }


}

