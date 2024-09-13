package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Eccezioni.ComuneNonTrovatoException;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Repository.ComuniRepository;
import it.unicam.cs.ids.GeoPlus.Model.Repository.PoiRepository;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;
import it.unicam.cs.ids.GeoPlus.Model.Util.SistemaOSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiziPoi {

    @Autowired
    private PoiRepository poiRepository;

    @Autowired
    private ComuniRepository comuniRepository;

    @Autowired
    private SistemaOSM sistemaOSM;

    public Poi creaPoi(String nomePoi, String descrizionePoi, Coordinate posizionePoi, Comune comunePoi) throws ComuneNonTrovatoException {
        boolean corrispondenza = sistemaOSM.contieneCoordinate(posizionePoi, comunePoi.getNomeComune());
        if (!corrispondenza) {
            throw new ComuneNonTrovatoException("Comune non corrispondente alle coordinate fornite.");
        }
        if (verificaPosizione(posizionePoi)) {
            throw new IllegalArgumentException("Esiste già un Poi nella nelle coordinate fornite");
        }
        return new Poi(sistemaOSM.formattaNomeComune(nomePoi), descrizionePoi, posizionePoi);
    }

    public PoiTemporaneo creaPoi(String nomePoi, String descrizionePoi, Coordinate posizionePoi, Comune comunePoi, PeriodoTempo periodoTempo) throws ComuneNonTrovatoException {
        boolean corrispondenza = sistemaOSM.contieneCoordinate(posizionePoi, comunePoi.getNomeComune());
        if (!corrispondenza) {
            throw new ComuneNonTrovatoException("Comune non corrispondente alle coordinate fornite.");
        }
        if (verificaPosizione(posizionePoi)) {
            throw new IllegalArgumentException("Esiste già un Poi nella nelle coordinate fornite");
        }
        return new PoiTemporaneo(sistemaOSM.formattaNomeComune(nomePoi), descrizionePoi, posizionePoi, periodoTempo);
    }


    private boolean verificaPosizione(Coordinate posizionePoi) {
        return getPoi(posizionePoi) != null;
    }

    public void salvaPoi(Poi poi, Comune comunePoi) {
        poiRepository.save(poi);
        comunePoi.aggiungiPoi(poi);
        comuniRepository.save(comunePoi);
    }

    public void eliminaPoi(Poi poi, Comune comunePoi) {
        poiRepository.delete(poi);
        comunePoi.rimuoviPoi(poi);
        comuniRepository.save(comunePoi);
    }

    public Poi getPoi(Coordinate posizione) {
        return poiRepository.findByPosizionePoi(posizione);
    }

    public List<Poi> getPois(String nomePoi) {
        return poiRepository.findAllByNomePoi(nomePoi);
    }

    public void rimuoviPoiTemporaneiScaduti() {
        List<PoiTemporaneo> poisTemporanei = poiRepository.findAllTemporanei();
        LocalDateTime oraCorrente = LocalDateTime.now();
        for (PoiTemporaneo poi : poisTemporanei) {
            if (poi.getPeriodoApertura().verificaScadenza(oraCorrente)) {
                poiRepository.delete(poi);
            }
        }
    }
}
