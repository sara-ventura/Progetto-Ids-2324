package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import javax.validation.constraints.NotBlank;

public class ContenutoTestualeBody {

    @NotBlank
    private String testo;

    @NotBlank
    private Long idPoi;


    public @NotBlank String getTesto() {
        return testo;
    }

    public @NotBlank Long getIdPoi() {
        return idPoi;
    }
}
