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
    private PeriodoTempo periodoApertura;

    public String getNomePoi() {
        return nomePoi;
    }

    public String getDescrizionePoi() {
        return descrizionePoi;
    }

    public Coordinate getPosizionePoi() {
        return posizionePoi;
    }

    public @NotNull PeriodoTempo getPeriodoApertura() {
        return periodoApertura;
    }

}
