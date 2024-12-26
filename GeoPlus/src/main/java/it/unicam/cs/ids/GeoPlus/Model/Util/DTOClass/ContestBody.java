package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;

import java.util.List;

public class ContestBody {
    private String nomeContest;
    private String descrizione;
    private String regole;
    private boolean pubblico;
    private PeriodoTempo periodoTempo;
    private List<Long> poiIds;

    public String getNomeContest() {
        return nomeContest;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getRegole() {
        return regole;
    }

    public boolean isPubblico() {
        return pubblico;
    }

    public PeriodoTempo getPeriodoTempo() {
        return periodoTempo;
    }

    public List<Long> getPoiIds() {
        return poiIds;
    }

}
