package it.unicam.cs.ids.GeoPlus.Model.Util;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;

import java.util.ArrayList;
import java.util.List;

public class RisultatiRicercaGenerica {
    private Comune comune;
    private List<Poi> pois;
    private List<Itinerario> itinerari;
    private List<Contest> contests;


    public Comune getComune() {
        return comune;
    }

    public void setComune(Comune comune) {
        this.comune = comune;
    }

    public List<Poi> getPois() {
        return pois;
    }

    public void setPois(List<Poi> pois) {
        this.pois = pois;
    }

    public List<Itinerario> getItinerari() {
        return itinerari;
    }

    public void setItinerari(List<Itinerario> itinerari) {
        this.itinerari = itinerari;
    }

    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    public List<Object> getRisultati() {
        List<Object> risultati = new ArrayList<>();
        if (comune != null) {
            risultati.add(comune);
        }
        if (pois != null && !pois.isEmpty()) {
            risultati.addAll(pois);
        }
        if (itinerari != null && !itinerari.isEmpty()) {
            risultati.addAll(itinerari);
        }
        if (contests != null && !contests.isEmpty()) {
            risultati.addAll(contests);
        }
        return risultati;
    }
}
