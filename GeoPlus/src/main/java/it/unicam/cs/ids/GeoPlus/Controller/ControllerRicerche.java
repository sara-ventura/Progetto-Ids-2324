package it.unicam.cs.ids.GeoPlus.Controller;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziComune;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziContest;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziItinerario;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziPoi;
import it.unicam.cs.ids.GeoPlus.Model.Util.RisultatiRicercaGenerica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cerca")
public class ControllerRicerche {
    @Autowired
    private ServiziPoi serviziPoi;
    @Autowired
    private ServiziComune serviziComune;
    @Autowired
    private ServiziContest serviziContest;
    @Autowired
    private ServiziItinerario serviziItinerario;

    @GetMapping("generico")
    public ResponseEntity<List<Object>> ricercaGenerica(@RequestParam String nome) {
        RisultatiRicercaGenerica risultati = new RisultatiRicercaGenerica();
        Comune comune = serviziComune.getComune(nome);
        risultati.setComune(comune);
        List<Poi> pois = serviziPoi.getPoiApprovati(nome);
        risultati.setPois(pois);
        List<Itinerario> itinerari = serviziItinerario.getItinerariApprovati(nome);
        risultati.setItinerari(itinerari);
        List<Contest> contests = serviziContest.getContests(nome);
        risultati.setContests(contests);
        if (risultati.getComune() == null &&
                (risultati.getPois() == null || risultati.getPois().isEmpty()) &&
                (risultati.getItinerari() == null || risultati.getItinerari().isEmpty()) &&
                (risultati.getContests() == null || risultati.getContests().isEmpty())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(risultati.getRisultati());
    }


    @GetMapping("Comune")
    public ResponseEntity<Comune> cercaComune(@RequestParam String nomeComune) {
        Comune comune = serviziComune.getComune(nomeComune);
        return ResponseEntity.ok(comune);
    }

    @GetMapping("Poi")
    public ResponseEntity<List<Poi>> cercaPoi(@RequestParam String nomePoi) {
        List<Poi> pois = serviziPoi.getPoiApprovati(nomePoi);
        return ResponseEntity.ok(pois);
    }

    @GetMapping("Itinerario")
    public ResponseEntity<List<Itinerario>> cercaItinerario(@RequestParam String nomeItinerario) {
        List<Itinerario> itinerari = serviziItinerario.getItinerariApprovati(nomeItinerario);
        return ResponseEntity.ok(itinerari);
    }

    @GetMapping("Contest")
    public ResponseEntity<List<Contest>> cercaContest(@RequestParam String nomeContest) {
        List<Contest> contest = serviziContest.getContests(nomeContest);
        return ResponseEntity.ok(contest);
    }

    @GetMapping("/ottieniListaPoi")
    public ResponseEntity<List<Poi>> ottieniListaPoi(@RequestParam String nomeComune) {
        Comune comune = serviziComune.getComune(nomeComune);
        List<Poi> poiApprovati = comune.getPoiAssociati().stream()
                .filter(Poi::isApprovato)
                .collect(Collectors.toList());

        return ResponseEntity.ok(poiApprovati);
    }

    @GetMapping("ottieniListaItinerari")
    public ResponseEntity<List<Itinerario>> ottieniListaItinerari(@RequestParam String nomeComune) {
        Comune comune = serviziComune.getComune(nomeComune);
        List<Itinerario> itinerariApprovati = comune.getItinerariAssociati().stream()
                .filter(Itinerario::isApprovato)
                .collect(Collectors.toList());
        return ResponseEntity.ok(itinerariApprovati);
    }
}
