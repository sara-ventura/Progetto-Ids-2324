package it.unicam.cs.ids.GeoPlus.Model.Contenuto;

import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.Objects;

public class ContenutoMultimediale extends Contenuto {

    private String didascalia;
    private String file;

    public ContenutoMultimediale(UtenteRegistrato autoreContenuto, Poi poi, String file, String didascalia) {
        super(autoreContenuto, poi);
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

//    @Override
//    public boolean equals(Object o) {
//
//    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), didascalia, file);
    }

}

