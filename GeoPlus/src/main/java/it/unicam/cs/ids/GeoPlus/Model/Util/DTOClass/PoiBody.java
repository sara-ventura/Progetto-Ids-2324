package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//classe DTO
public class PoiBody {
    @NotBlank

    private String nomePoi;
    @NotBlank

    private String descrizionePoi;

    @NotNull
    private Coordinate posizionePoi;


    public String getNomePoi() {
        return nomePoi;
    }


    public String getDescrizionePoi() {
        return descrizionePoi;
    }


    public Coordinate getPosizionePoi() {
        return posizionePoi;
    }


}
