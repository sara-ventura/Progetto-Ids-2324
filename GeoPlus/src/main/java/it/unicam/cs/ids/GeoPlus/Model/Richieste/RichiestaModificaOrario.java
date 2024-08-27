package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.time.LocalTime;

/**
 * La classe RichiestaModificaOrario rappresenta una richiesta di modifica degli orari di apertura e chiusura 
 * di un punto di interesse (POI) effettuata da un utente registrato all'interno di un determinato comune.
 * Estende la classe astratta Richiesta.
 * Questa classe viene utilizzata per gestire le richieste che includono informazioni specifiche 
 * sugli orari di apertura e chiusura di un POI, il giorno della settimana a cui si riferisce 
 * la modifica, l'autore della richiesta, il comune associato e il POI coinvolto.
 */

public class RichiestaModificaOrario extends Richiesta {

    private String giorno;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;

 /**
     * Costruttore che inizializza una nuova richiesta di modifica degli orari con i dettagli specificati.
     *
     * @param autoreRichiesta l'utente registrato che ha effettuato la richiesta
     * @param comune          il comune associato alla richiesta
     * @param poi             il punto di interesse (POI) di cui si desidera modificare gli orari
     * @param giorno          il giorno della settimana a cui si riferisce la modifica
     * @param orarioApertura  l'orario di apertura proposto
     * @param orarioChiusura  l'orario di chiusura proposto
     */

    public RichiestaModificaOrario(UtenteRegistrato autoreRichiesta, Comune comune, Poi poi, String giorno, LocalTime orarioApertura, LocalTime orarioChiusura) {
        super(autoreRichiesta, comune, poi);
        this.giorno = giorno;
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
    }

    public RichiestaModificaOrario() {

    }

  /**
     * Restituisce il giorno della settimana a cui si riferisce la modifica degli orari.
     *
     * @return il giorno della settimana della modifica degli orari
     */

    public String getGiorno() {
        return giorno;
    }

/**
     * Restituisce l'orario di apertura proposto per il giorno specificato.
     *
     * @return l'orario di apertura proposto
     */

    public LocalTime getOrarioApertura() {
        return orarioApertura;
    }

 /**
     * Restituisce l'orario di chiusura proposto per il giorno specificato.
     *
     * @return l'orario di chiusura proposto
     */

    public LocalTime getOrarioChiusura() {
        return orarioChiusura;
    }

}
