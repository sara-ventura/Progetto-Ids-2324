package it.unicam.cs.ids.GeoPlus.Model.Contenuto;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.SoggettoRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;


/**
 * Classe astratta che rappresenta un contenuto associato a un punto di interesse (Poi)
 * e a un autore registrato.
 */
public abstract class Contenuto implements SoggettoRichiesta {

    private UtenteRegistrato autoreContenuto;
    private Poi poi;

    /**
     * Costruttore per creare un oggetto Contenuto.
     *
     * @param autoreContenuto L'autore del contenuto.
     * @param poi             Il punto di interesse associato al contenuto.
     */
    public Contenuto(UtenteRegistrato autoreContenuto, Poi poi) {
        this.autoreContenuto = autoreContenuto;
        this.poi = poi;
    }

    /**
     * Costruttore di default per creare un oggetto Contenuto.
     */
    public Contenuto() {
        // Costruttore di default
    }

    /**
     * Restituisce l'autore del contenuto.
     *
     * @return L'autore registrato del contenuto.
     */
    public UtenteRegistrato getAutoreContenuto() {
        return this.autoreContenuto;
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

    // @Override
    // public boolean equals(Object o) {
    //     // Implementazione dell'uguaglianza
    // }

    // @Override
    // public int hashCode() {
    //     // Implementazione del calcolo dell'hash
    // }
}