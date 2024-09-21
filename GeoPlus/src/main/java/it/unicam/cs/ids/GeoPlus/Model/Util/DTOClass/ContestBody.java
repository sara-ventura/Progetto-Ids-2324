package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;

import java.util.List;

public class ContestBody {
    private String nomeContest;
    private String descrizione;
    private Long autoreContestId;
    private String regole;
    private boolean pubblico;
    private PeriodoTempo periodoTempo;
    private List<Long> poiIds;

    public String getNomeContest() {
        return nomeContest;
    }

    public void setNomeContest(String nomeContest) {
        this.nomeContest = nomeContest;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getAutoreContestId() {
        return autoreContestId;
    }

    public void setAutoreContestId(Long autoreContestId) {
        this.autoreContestId = autoreContestId;
    }

    public String getRegole() {
        return regole;
    }

    public void setRegole(String regole) {
        this.regole = regole;
    }

    public boolean isPubblico() {
        return pubblico;
    }

    public void setPubblico(boolean pubblico) {
        this.pubblico = pubblico;
    }

    public PeriodoTempo getPeriodoTempo() {
        return periodoTempo;
    }

    public void setPeriodoTempo(PeriodoTempo periodoTempo) {
        this.periodoTempo = periodoTempo;
    }

    public List<Long> getPoiIds() {
        return poiIds;
    }

    public void setPoiIds(List<Long> poiIds) {
        this.poiIds = poiIds;
    }
}
