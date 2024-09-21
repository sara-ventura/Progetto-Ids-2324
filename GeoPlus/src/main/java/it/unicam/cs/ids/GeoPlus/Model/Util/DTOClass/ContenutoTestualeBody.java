package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContenutoTestualeBody {

    @NotBlank
    private String testo;

    @NotBlank
    private Long idPoi;

    @NotNull
    private long idAutoreCaricamento;

    public @NotBlank String getTesto() {
        return testo;
    }

    public void setTesto(@NotBlank String testo) {
        this.testo = testo;
    }


    @NotNull
    public long getIdAutoreCaricamento() {
        return idAutoreCaricamento;
    }

    public void setIdAutoreCaricamento(@NotNull long idAutoreCaricamento) {
        this.idAutoreCaricamento = idAutoreCaricamento;
    }

    public @NotBlank Long getIdPoi() {
        return idPoi;
    }

    public void setIdPoi(@NotBlank Long idPoi) {
        this.idPoi = idPoi;
    }
}
