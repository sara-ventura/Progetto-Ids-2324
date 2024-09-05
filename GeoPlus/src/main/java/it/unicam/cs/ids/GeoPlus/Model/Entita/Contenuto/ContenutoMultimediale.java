package it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class ContenutoMultimediale extends Contenuto {

    private String didascalia;
    private String file;

    public ContenutoMultimediale(Poi poi, String file, String didascalia) {
        super(poi);
        this.didascalia = didascalia;
        this.file = file;
    }

    public ContenutoMultimediale() {

    }

    @Override
    public void setTesto(String testo) {
        this.didascalia = testo;
    }

    public String getDidascalia() {
        return didascalia;
    }

    public String getFile() {
        return file;
    }

    public void setDidascalia(String didascalia) {
        this.didascalia = didascalia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        ContenutoMultimediale that = (ContenutoMultimediale) o; // Casting
        return Objects.equals(didascalia, that.didascalia) &&
                Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), didascalia, file);
    }

}

