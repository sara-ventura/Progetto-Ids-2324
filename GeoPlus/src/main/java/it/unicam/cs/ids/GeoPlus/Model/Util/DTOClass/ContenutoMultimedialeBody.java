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



    public @NotBlank String getTesto() {
        return testo;
    }

    public @NotBlank String getFile() {
        return file;
    }

    public  Long getIdPoi() {
        return idPoi;
    }

}

