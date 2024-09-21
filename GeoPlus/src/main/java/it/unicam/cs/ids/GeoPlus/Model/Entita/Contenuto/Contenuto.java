package it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.Objects;



@Entity
public abstract class Contenuto extends EntitaRichiesta {

    @JsonBackReference
    @ManyToOne
    private Poi poi;


    public Contenuto(Poi poi) {
        this.poi = poi;

    }

    public Contenuto() {
    }

    public Poi getPoi() {
        return this.poi;
    }


    public abstract void setTesto(String testo);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass()) return false;
        Contenuto contenuto = (Contenuto) o;
        return Objects.equals(poi, contenuto.poi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(poi, this.getId());
    }
}