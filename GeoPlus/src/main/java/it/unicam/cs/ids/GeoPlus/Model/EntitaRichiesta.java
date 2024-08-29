package it.unicam.cs.ids.GeoPlus.Model;


public abstract class EntitaRichiesta implements SoggettoRichiesta {
    private Long Id;

    public Long getId() {
        return Id;
    }

    public abstract Comune getComune();

}
