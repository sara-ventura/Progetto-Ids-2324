package it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import jakarta.persistence.Entity;

import java.util.Objects;



@Entity
public class ContenutoTestuale extends Contenuto {

    private String testo;

    public ContenutoTestuale(Poi poi, String testo) {
        super(poi);
        this.testo = testo;
    }

    public ContenutoTestuale() {
    }


    public String getTesto() {
        return testo;
    }


    @Override
    public void setTesto(String testo) {
        this.testo = testo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        ContenutoTestuale that = (ContenutoTestuale) o;
        return Objects.equals(testo, that.testo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), testo);
    }


}