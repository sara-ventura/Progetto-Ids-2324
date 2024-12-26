package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.ContenutoMultimediale;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.ContenutoTestuale;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.*;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private ServiziAccount serviziAccount;
    @Autowired
    private ServiziItinerario serviziItinerario;


    @PostMapping("/Poi")
    public ResponseEntity<String> creaPoi(@Valid @RequestBody PoiBody poiBody) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        user = serviziAccount.getAccountById(user.getId());
        if (user.getRuoloUtente().toString().equals(Ruoli.TURISTA_AUTENTICATO)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Coordinate posizionePoi = poiBody.getPosizionePoi();
        Comune comunePoi = serviziComune.getComune(posizionePoi);
        if (user.getComuneAppartenenza().toString().equals(comunePoi.getNomeComune())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Poi poi = serviziPoi.creaPoi(poiBody.getNomePoi(), poiBody.getDescrizionePoi(), posizionePoi, comunePoi);
        poi.setIdAutore(user.getId());
        if (user.getRuoloUtente().toString().equals(Ruoli.CONTRIBUTOR_AUTORIZZATO.toString()) ||
                user.getRuoloUtente().toString().equals(Ruoli.CURATORE.toString()) ||
                user.getRuoloUtente().toString().equals(Ruoli.ANIMATORE.toString())) {
            poi.setApprovato(true);
            serviziPoi.salvaPoi(poi, comunePoi);
            return ResponseEntity.ok("POI creato e salvato con successo.");
        } else {
            poi.setApprovato(false);
            serviziPoi.salvaPoi(poi, comunePoi);
            serviziRichieste.creaRichiestaCaricamento(user, poi, comunePoi);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento creata con successo.");
        }
    }

    @PostMapping("/PoiTemporaneo")
    public ResponseEntity<String> creaPoiTemporaneo(@Valid @RequestBody PoiTemporaneoBody poiBody) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        user = serviziAccount.getAccountById(user.getId());
        if (user.getRuoloUtente().toString().equals(Ruoli.TURISTA_AUTENTICATO)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Coordinate posizionePoi = poiBody.getPosizionePoi();
        Comune comunePoi = serviziComune.getComune(posizionePoi);
        if (user.getComuneAppartenenza().toString().equals(comunePoi.getNomeComune())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Poi poi = serviziPoi.creaPoi(poiBody.getNomePoi(), poiBody.getDescrizionePoi(), posizionePoi, comunePoi, poiBody.getPeriodoApertura());
        poi.setIdAutore(user.getId());
        if (user.getRuoloUtente().toString().equals(Ruoli.CONTRIBUTOR_AUTORIZZATO.toString()) ||
                user.getRuoloUtente().toString().equals(Ruoli.CURATORE.toString()) ||
                user.getRuoloUtente().toString().equals(Ruoli.ANIMATORE.toString())) {
            poi.setApprovato(true);
            serviziPoi.salvaPoi(poi, comunePoi);
            return ResponseEntity.ok("POI creato e salvato con successo.");
        } else {
            poi.setApprovato(false);
            serviziPoi.salvaPoi(poi, comunePoi);
            serviziRichieste.creaRichiestaCaricamento(user, poi, comunePoi);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento creata con successo.");
        }
    }

    @PostMapping("/ContenutoTestuale")
    public ResponseEntity<String> creaContenutoTestuale(@Valid @RequestBody ContenutoTestualeBody contenutoTestualeBody) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        user = serviziAccount.getAccountById(user.getId());
        ContenutoTestuale contenutoTestuale = serviziContenuto.creaContenutoTestuale(serviziPoi.getPoi(contenutoTestualeBody.getIdPoi()), contenutoTestualeBody.getTesto());
        Comune comuneContenuto = serviziComune.getComune(serviziPoi.getPoi(contenutoTestualeBody.getIdPoi()).getPosizionePoi());
        contenutoTestuale.setIdAutore(user.getId());
        if (verificaUtente(user, comuneContenuto)) {
            contenutoTestuale.setApprovato(true);
            serviziContenuto.salvaContenuto(contenutoTestuale);
            return ResponseEntity.ok("Contenuto creato e salvato con successo.");
        } else {
            contenutoTestuale.setApprovato(false);
            serviziContenuto.salvaContenuto(contenutoTestuale);
            serviziRichieste.creaRichiestaCaricamento(user, contenutoTestuale, comuneContenuto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento creata con successo.");
        }
    }

    @PostMapping("/ContenutoMultimediale")
    public ResponseEntity<String> creaContenutoMultimediale(@Valid @RequestBody ContenutoMultimedialeBody contenutoMultimedialeBody) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        user = serviziAccount.getAccountById(user.getId());
        ContenutoMultimediale contenutoMultimediale = serviziContenuto.creaContenutoMultimediale(serviziPoi.getPoi(contenutoMultimedialeBody.getIdPoi()), contenutoMultimedialeBody.getFile(), contenutoMultimedialeBody.getTesto());
        Comune comuneContenuto = serviziComune.getComune(serviziPoi.getPoi(contenutoMultimedialeBody.getIdPoi()).getPosizionePoi());
        contenutoMultimediale.setIdAutore(user.getId());
        if (verificaUtente(user, comuneContenuto)) {
            contenutoMultimediale.setApprovato(true);
            serviziContenuto.salvaContenuto(contenutoMultimediale);
            return ResponseEntity.ok("Contenuto creato e salvato con successo.");
        } else {
            contenutoMultimediale.setApprovato(false);
            serviziContenuto.salvaContenuto(contenutoMultimediale);
            serviziRichieste.creaRichiestaCaricamento(user, contenutoMultimediale, comuneContenuto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento creata con successo.");
        }
    }

    @PostMapping("/Itinerario")
    public ResponseEntity<String> creaItinerario(@Valid @RequestBody ItinerarioBody itinerarioBody) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        user = serviziAccount.getAccountById(user.getId());
        if (user.getRuoloUtente().toString().equals(Ruoli.TURISTA_AUTENTICATO)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Comune comune = serviziComune.getComune(itinerarioBody.getIdComune());
        if (user.getComuneAppartenenza().toString().equals(comune.getNomeComune())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Long> listaIdPoi = itinerarioBody.getListaPoi();
        List<Poi> listaPoi = listaIdPoi.stream()
                .map(id -> serviziPoi.getPoi(id))
                .toList();
        Itinerario itinerario = serviziItinerario.creaItinerario(itinerarioBody.getNomeItinerario(), itinerarioBody.getDescrizioneItinerario(), comune, listaPoi);
        itinerario.setIdAutore(user.getId());
        if (user.getRuoloUtente().toString().equals(Ruoli.CONTRIBUTOR_AUTORIZZATO.toString()) ||
                user.getRuoloUtente().toString().equals(Ruoli.CURATORE.toString()) ||
                user.getRuoloUtente().toString().equals(Ruoli.ANIMATORE.toString())) {
            itinerario.setApprovato(true);
            serviziItinerario.salvaItinerario(itinerario, comune);
            return ResponseEntity.ok("Itinerario creato e salvato con successo.");
        } else {
            itinerario.setApprovato(false);
            serviziItinerario.salvaItinerario(itinerario, comune);
            serviziRichieste.creaRichiestaCaricamento(user, itinerario, comune);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di caricamento creata con successo.");
        }
    }


    @DeleteMapping("/elimina/Poi/{idPoi}")
    public ResponseEntity<String> eliminaPoi(@PathVariable Long idPoi) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        user = serviziAccount.getAccountById(user.getId());
        Poi poi = serviziPoi.getPoi(idPoi);
        if (poi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("POI non trovato.");
        }
        if (poi.getIdAutore().equals(user.getId())) {
            Comune comune = serviziComune.getComune(poi.getPosizionePoi());
            serviziPoi.eliminaPoi(poi, comune);
            return ResponseEntity.ok("POI eliminato con successo.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai i permessi per eliminare questo POI.");
        }
    }

    @DeleteMapping("/elimina/Contenuto/{idContenuto}")
    public ResponseEntity<String> eliminaContenuto(@PathVariable Long idContenuto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        user = serviziAccount.getAccountById(user.getId());
        Contenuto contenuto = serviziContenuto.getContenuto(idContenuto);
        if (contenuto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contenuto non trovato.");
        }
        if (contenuto.getIdAutore().equals(user.getId())) {
            serviziContenuto.eliminaContenuto(contenuto);
            return ResponseEntity.ok("Contenuto eliminato con successo.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai i permessi per eliminare questo contenuto.");
        }
    }

    @DeleteMapping("/elimina/Itinerario/{id}")
    public ResponseEntity<String> eliminaItinerario(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account user = (Account) authentication.getPrincipal();
        user = serviziAccount.getAccountById(user.getId());
        Itinerario itinerario = serviziItinerario.getItinerario(id);
        if (itinerario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Itinerario non trovato.");
        }
        if (itinerario.getIdAutore().equals(user.getId())) {
            serviziItinerario.eliminaItinerario(itinerario);
            return ResponseEntity.ok("Itinerario eliminato con successo.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non hai i permessi per eliminare questo itinerario.");
        }
    }

    private boolean verificaUtente(Account autoreCaricamento, Comune comune) {
        Ruoli ruolo = autoreCaricamento.getRuoloUtente();

        return (
                ruolo.toString().equals(Ruoli.CONTRIBUTOR_AUTORIZZATO.toString()) ||
                        ruolo.toString().equals(Ruoli.CURATORE.toString()) ||
                        ruolo.toString().equals(Ruoli.ANIMATORE.toString())) &&
                autoreCaricamento.getComuneAppartenenza().toString().equals(comune.toString());
    }
}
