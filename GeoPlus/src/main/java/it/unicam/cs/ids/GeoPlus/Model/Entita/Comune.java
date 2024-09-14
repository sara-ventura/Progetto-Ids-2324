package it.unicam.cs.ids.GeoPlus.Model.Entita;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComune;
    private String nomeComune;
    private String descrizione;
    @OneToMany
    private List<Poi> listaPoi;
    @OneToMany
    private List<Itinerario> listaItinerari;
    @Embedded
    private Coordinate coordinateCentrali;


    public Comune(String nomeComune, String descrizione, Coordinate coordinateCentrali) {
        this.nomeComune = nomeComune;
        this.descrizione = descrizione;
        this.coordinateCentrali = coordinateCentrali;
        this.listaPoi = new ArrayList<>();
        listaItinerari = new ArrayList<>();

    }

    public Comune() {
        this.listaPoi = new ArrayList<>();
        this.listaItinerari = new ArrayList<>();
    }

    public Long getIdComune() {
        return idComune;
    }

    public String getNomeComune() {
        return this.nomeComune;
    }


    public String getDescrizione() {
        return this.descrizione;
    }


    public List<Poi> getPoiAssociati() {
        return this.listaPoi;
    }


    public List<Itinerario> getItinerariAssociati() {
        return this.listaItinerari;
    }


    public void aggiungiPoi(Poi poi) {
        if (!listaPoi.contains(poi)) {
            listaPoi.add(poi);
        }
    }


    public boolean rimuoviPoi(Poi poi) {
        return listaPoi.remove(poi);
    }


    public boolean aggiungiItinerario(Itinerario itinerario) {
        if (!listaItinerari.contains(itinerario)) {
            listaItinerari.add(itinerario);
            return true;
        }
        return false;
    }


    public boolean rimuoviItinerario(Itinerario itinerario) {
        return listaItinerari.remove(itinerario);
    }

    public Coordinate getCoordinateCentrali() {
        return this.coordinateCentrali;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comune comune = (Comune) o;
        return Objects.equals(idComune, comune.idComune) &&
                Objects.equals(nomeComune, comune.nomeComune) &&
                Objects.equals(descrizione, comune.descrizione) &&
                Objects.equals(coordinateCentrali, comune.coordinateCentrali);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComune, nomeComune, descrizione, coordinateCentrali); }


}