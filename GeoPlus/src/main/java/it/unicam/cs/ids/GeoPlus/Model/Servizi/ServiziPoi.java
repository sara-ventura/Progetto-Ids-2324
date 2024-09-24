package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.ComuneNonTrovatoException;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Repository.ComuniRepository;
import it.unicam.cs.ids.GeoPlus.Model.Repository.PoiRepository;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni.PoiGiaEsistenteException;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;
import it.unicam.cs.ids.GeoPlus.Model.Util.SistemaOSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
            throw new PoiGiaEsistenteException();
        }
        return new Poi(sistemaOSM.formattaNomeComune(nomePoi), descrizionePoi, posizionePoi);
    }

    public PoiTemporaneo creaPoi(String nomePoi, String descrizionePoi, Coordinate posizionePoi, Comune comunePoi, PeriodoTempo periodoTempo) throws ComuneNonTrovatoException {
        boolean corrispondenza = sistemaOSM.contieneCoordinate(posizionePoi, comunePoi.getNomeComune());
        if (!corrispondenza) {
            throw new ComuneNonTrovatoException("Comune non corrispondente alle coordinate fornite.");
        }
        if (verificaPosizione(posizionePoi)) {
            throw new PoiGiaEsistenteException();
        }
        return new PoiTemporaneo(sistemaOSM.formattaNomeComune(nomePoi), descrizionePoi, posizionePoi, periodoTempo);
    }


    private boolean verificaPosizione(Coordinate posizionePoi) {
        Poi poi = getPoi(posizionePoi);
        return (poi != null && poi.isApprovato());
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

    public void aggiornaPoi(Poi poi) {
        this.poiRepository.save(poi);
    }

    public Poi getPoi(Coordinate posizione) {
        return poiRepository.findByPosizionePoi(posizione);
    }

    public List<Poi> getPoi(String nomePoi) {
        return poiRepository.findAllByNomePoi(nomePoi);
    }

    public List<Poi> getPoiApprovati(String nomePoi) {
        return poiRepository.findAllByApprovatoAndNomePoi(true, nomePoi);
    }
    public Poi getPoi(Long idPoi) {
        return poiRepository.findById(idPoi).orElse(null);
    }

    public PoiTemporaneo getPoiTemporaneo(Long idPoi) {
        List<PoiTemporaneo> poisTemporanei = poiRepository.findAllTemporanei();
        for (PoiTemporaneo poiTemporaneo : poisTemporanei) {
            if (poiTemporaneo.getId().equals(idPoi)) {
                return poiTemporaneo;
            }
        }
        return null;
    }
    @Scheduled(cron = "0 0 0 * * ?")
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
