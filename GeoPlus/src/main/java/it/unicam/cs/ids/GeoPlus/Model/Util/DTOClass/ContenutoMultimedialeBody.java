package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContenutoMultimedialeBody {

    @NotBlank
    private String testo;

    @NotBlank
    private String file;

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



    public @NotBlank String getFile() {
        return file;
    }

    public void setFile(@NotBlank String file) {
        this.file = file;
    }

    @NotNull
    public long getIdAutoreCaricamento() {
        return idAutoreCaricamento;
    }

    public void setIdAutoreCaricamento(@NotNull long idAutoreCaricamento) {
        this.idAutoreCaricamento = idAutoreCaricamento;
    }

    public  Long getIdPoi() {
        return idPoi;
    }

    public void setIdPoi(@NotNull Long idPoi) {
        this.idPoi = idPoi;
    }
}

