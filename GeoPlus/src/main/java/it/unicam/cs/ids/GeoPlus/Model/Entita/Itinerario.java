package it.unicam.cs.ids.GeoPlus.Model.Entita;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Itinerario extends EntitaRichiesta {

    private String nomeItinerario;
    private String descrizioneItinerario;
    @OneToMany
    private List<Poi> listaPoi;
    @ManyToOne
    private Comune comune;


    public Itinerario(String nomeItinerario, String descrizioneItinerario, Comune comune) {
        this.nomeItinerario = nomeItinerario;
        this.descrizioneItinerario = descrizioneItinerario;
        this.comune = comune;

        this.listaPoi = new ArrayList<>();
    }

    public Itinerario() {
        this.listaPoi = new ArrayList<>();
    }

    public String getNomeItinerario() {
        return nomeItinerario;
    }


    public void setNomeItinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
    }


    public String getDescrizioneItinerario() {
        return descrizioneItinerario;
    }


    private void setDescrizioneItinerario(String descrizioneItinerario) {
        this.descrizioneItinerario = descrizioneItinerario;
    }


    public List<Poi> getListaPoi() {
        return listaPoi;
    }


    public void aggiungiPoi(Poi poi) {
        if (!listaPoi.contains(poi)) {
            listaPoi.add(poi);
        }
    }


    public void rimuoviPoi(Poi poi) {
        listaPoi.remove(poi);
    }


    public boolean contienePoi(Poi poi) {
        return listaPoi.contains(poi);
    }


    public Comune getComune() {
        return comune;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass()) return false;
        Itinerario that = (Itinerario) o;
        return
                Objects.equals(nomeItinerario, that.nomeItinerario) &&
                        Objects.equals(descrizioneItinerario, that.descrizioneItinerario) &&
                        Objects.equals(comune, that.comune) &&
                        Objects.equals(listaPoi, that.listaPoi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeItinerario, descrizioneItinerario, comune, listaPoi);
    }


}