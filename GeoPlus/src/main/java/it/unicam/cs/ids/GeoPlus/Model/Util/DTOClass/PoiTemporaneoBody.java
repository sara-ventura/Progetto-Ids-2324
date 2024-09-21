package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PoiTemporaneoBody {
    @NotBlank

    private String nomePoi;
    @NotBlank

    private String descrizionePoi;

    @NotNull
    private Coordinate posizionePoi;
    @NotNull
    private long idAutoreCaricamento;


    @NotNull
    private PeriodoTempo periodoApertura;

    @NotNull
    public long getIdAutoreCaricamento() {
        return idAutoreCaricamento;
    }

    public void setIdAutoreCaricamento(@NotNull long idAutoreCaricamento) {
        this.idAutoreCaricamento = idAutoreCaricamento;
    }

    public String getNomePoi() {
        return nomePoi;
    }

    public void setNomePoi(String nomePoi) {
        this.nomePoi = nomePoi;
    }

    public String getDescrizionePoi() {
        return descrizionePoi;
    }

    public void setDescrizionePoi(String descrizionePoi) {
        this.descrizionePoi = descrizionePoi;
    }

    public Coordinate getPosizionePoi() {
        return posizionePoi;
    }

    public void setPosizionePoi(Coordinate posizionePoi) {
        this.posizionePoi = posizionePoi;
    }

    public @NotNull PeriodoTempo getPeriodoApertura() {
        return periodoApertura;
    }

    public void setPeriodoApertura(@NotBlank @NotNull PeriodoTempo periodoApertura) {
        this.periodoApertura = periodoApertura;
    }
}
