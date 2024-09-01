package it.unicam.cs.ids.GeoPlus.Model.Contenuto;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;


/**
 * Classe astratta che rappresenta un contenuto associato a un punto di interesse (Poi)
 * e a un autore registrato.
 */
public abstract class Contenuto extends EntitaRichiesta {

    private Poi poi;
    private UtenteRegistrato autoreContenuto;

    /**
     * Costruttore per creare un oggetto Contenuto.
     *
     * @param autoreContenuto L'autore del contenuto.
     * @param poi             Il punto di interesse associato al contenuto.
     */
    public Contenuto(UtenteRegistrato autoreContenuto, Poi poi) {
        this.poi = poi;
        this.autoreContenuto = autoreContenuto;
    }

    /**
     * Costruttore di default per creare un oggetto Contenuto.
     */
    public Contenuto() {
    }

    /**
     * Restituisce il punto di interesse associato al contenuto.
     *
     * @return Il punto di interesse (Poi) associato.
     */
    public Poi getPoi() {
        return this.poi;
    }

    /**
     * Restituisce il comune associato al punto di interesse.
     *
     * @return Il comune associato al contenuto.
     */
    @Override
    public Comune getComune() {
        return this.poi.getComune();
    }

    /**
     * Restituisce l'autore del contenuto.
     *
     * @return l'autore del contenuto.
     */
    public UtenteRegistrato getAutoreContenuto() {
        return autoreContenuto;
    }

    public abstract void setTesto(String testo);

    // @Override
    // public boolean equals(Object o) {
    //     // Implementazione dell'uguaglianza
    // }

    // @Override
    // public int hashCode() {
    //     // Implementazione del calcolo dell'hash
    // }
}