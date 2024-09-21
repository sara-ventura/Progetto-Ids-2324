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

    @NotNull
    private long idAutoreCaricamento;

    public @NotBlank String getNomeItinerario() {
        return nomeItinerario;
    }

    public void setNomeItinerario(@NotBlank String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
    }

    public @NotBlank String getDescrizioneItinerario() {
        return descrizioneItinerario;
    }

    public void setDescrizioneItinerario(@NotBlank String descrizioneItinerario) {
        this.descrizioneItinerario = descrizioneItinerario;
    }


    @NotNull
    public long getIdAutoreCaricamento() {
        return idAutoreCaricamento;
    }

    public void setIdAutoreCaricamento(@NotNull long idAutoreCaricamento) {
        this.idAutoreCaricamento = idAutoreCaricamento;
    }

    @NotNull
    public long getIdComune() {
        return idComune;
    }

    public void setIdComune(@NotNull long idComune) {
        this.idComune = idComune;
    }

    public @NotNull @NotEmpty List<Long> getListaPoi() {
        return listaPoi;
    }

    public void setListaPoi(@NotNull @NotEmpty List<Long> listaPoi) {
        this.listaPoi = listaPoi;
    }
}