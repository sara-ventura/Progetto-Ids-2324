package it.unicam.cs.ids.GeoPlus.Model.Entita.Pois;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Util.Orari;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Poi extends EntitaRichiesta {

    private String nomePoi;
    private String descrizionePoi;
    @Embedded
    private Coordinate posizionePoi;
    @Embedded
    private Orari orariPoi;
    @OneToMany
    private List<Contenuto> contenutiAssociati;


    public Poi(String nomePoi, String descrizionePoi, Coordinate posizionePoi) {
        this.nomePoi = nomePoi;
        this.descrizionePoi = descrizionePoi;
        this.posizionePoi = posizionePoi;
        this.orariPoi = new Orari();
        this.contenutiAssociati = new ArrayList<>();
    }


    public Poi() {
        this.orariPoi = new Orari();
        this.contenutiAssociati = new ArrayList<>();
    }


    public void setNomePoi(String nomePoi) {
        this.nomePoi = nomePoi;
    }


    public String getNomePoi() {
        return this.nomePoi;
    }


    public void setDescrizionePoi(String descrizionePoi) {
        this.descrizionePoi = descrizionePoi;
    }


    public String getDescrizionePoi() {
        return this.descrizionePoi;
    }


    public Coordinate getPosizionePoi() {
        return this.posizionePoi;
    }


    public void setOrarioPoi(int giorno, LocalTime orarioApertura, LocalTime orarioChiusura) {
        this.orariPoi.setOrario(giorno, orarioApertura, orarioChiusura);
    }


    public List<Contenuto> getListaConetnutiAssociati() {
        return this.contenutiAssociati;
    }


    public void aggiungiContenuto(Contenuto contenuto) {
        this.contenutiAssociati.add(contenuto);
    }


    public void rimuoviContenuto(Contenuto contenuto) {
        this.contenutiAssociati.remove(contenuto);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass()) return false;
        Poi poi = (Poi) o;
        return
                Objects.equals(nomePoi, poi.nomePoi) &&
                        Objects.equals(descrizionePoi, poi.descrizionePoi) &&
                        Objects.equals(posizionePoi, poi.posizionePoi) &&
                        Objects.equals(orariPoi, poi.orariPoi) &&
                        Objects.equals(contenutiAssociati, poi.contenutiAssociati);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomePoi, descrizionePoi, posizionePoi, orariPoi, contenutiAssociati);
    }
}
