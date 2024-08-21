package it.unicam.cs.ids.GeoPlus.Model.Contenuto;

import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;


import java.util.Objects;

public class ContenutoMultimediale extends Contenuto {

    private String didascalia;
    private String percorsoImmagine;

    public ContenutoMultimediale(UtenteRegistrato autoreContenuto, Poi poi, String immagine, String didascalia) {
        super(autoreContenuto, poi);
        this.didascalia=didascalia;
        this.percorsoImmagine =immagine;
    }

    public ContenutoMultimediale() {

    }

    public String getDidascalia() {
        return didascalia;
    }

    public String getPercorsoImmagine() {
        return percorsoImmagine;
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
        return Objects.hash(super.hashCode(), didascalia, percorsoImmagine);
    }

}

