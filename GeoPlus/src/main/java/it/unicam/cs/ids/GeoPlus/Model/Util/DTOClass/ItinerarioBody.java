package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ItinerarioBody {
    @NotBlank
    private String nomeItinerario;
    @NotBlank
    private String descrizioneItinerario;

    @NotNull
    private long idComune;

    @NotNull
    @NotEmpty
    private List<Long> listaPoi;

    public @NotBlank String getNomeItinerario() {
        return nomeItinerario;
    }

    public @NotBlank String getDescrizioneItinerario() {
        return descrizioneItinerario;
    }

    @NotNull
    public long getIdComune() {
        return idComune;
    }

    public @NotNull @NotEmpty List<Long> getListaPoi() {
        return listaPoi;
    }

}