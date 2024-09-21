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

    @NotNull
    private long idAutoreCaricamento;

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

    public long getIdAutoreCaricamento() {
        return idAutoreCaricamento;
    }

    public void setIdAutoreCaricamento(Long autoreCaricamento) {
        this.idAutoreCaricamento = autoreCaricamento;
    }
}
