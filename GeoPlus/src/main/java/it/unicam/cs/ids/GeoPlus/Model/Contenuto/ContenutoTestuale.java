package it.unicam.cs.ids.GeoPlus.Model.Contenuto;

import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.Objects;


/**
 * La classe ContenutoTestuale rappresenta un contenuto testuale associato
 * a un autore registrato e a un punto di interesse (Poi).
 */
public class ContenutoTestuale extends Contenuto {

    private String testo;

    /**
     * Costruttore per creare un oggetto ContenutoTestuale.
     *
     * @param autoreContenuto L'autore del contenuto testuale.
     * @param poi             Il punto di interesse associato al contenuto testuale.
     * @param testo           Il testo del contenuto.
     */
    public ContenutoTestuale(UtenteRegistrato autoreContenuto, Poi poi, String testo) {
        super(autoreContenuto, poi);
        this.testo = testo;
    }

    /**
     * Costruttore di default per creare un oggetto ContenutoTestuale.
     */
    public ContenutoTestuale() {
        // Costruttore di default
    }

    /**
     * Restituisce il testo del contenuto testuale.
     *
     * @return Il testo del contenuto.
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Imposta il testo del contenuto testuale.
     *
     * @param testo Il nuovo testo del contenuto.
     */
    public void setTesto(String testo) {
        this.testo = testo; // Aggiorna il testo del contenuto
    }


    // @Override
    // public boolean equals(Object o) {
    //     // Implementazione dell'uguaglianza
    // }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), testo);
    }
}