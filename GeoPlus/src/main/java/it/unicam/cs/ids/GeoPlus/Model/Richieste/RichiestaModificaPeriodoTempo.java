package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.time.LocalDateTime;

/**
 * La classe RichiestaModificaPeriodoTempo rappresenta una richiesta di modifica 
 * del periodo di apertura e chiusura di un punto di interesse temporaneo (POI temporaneo) 
 * effettuata da un utente registrato all'interno di un determinato comune.
 * Estende la classe astratta Richiesta.
 * Questa classe è utilizzata per gestire le richieste che includono informazioni 
 * specifiche sulle date e sugli orari di apertura e chiusura di un POI temporaneo,
 * l'autore della richiesta, il comune associato e il POI temporaneo coinvolto.
 */

public class RichiestaModificaPeriodoTempo extends Richiesta {


   private LocalDateTime dataApertura;
   private LocalDateTime dataChiusura;

/**
     * Costruttore che inizializza una nuova richiesta di modifica del periodo di tempo con i dettagli specificati.
     *
     * @param autoreRichiesta  l'utente registrato che ha effettuato la richiesta
     * @param comune           il comune associato alla richiesta
     * @param poiTemporaneo    il punto di interesse temporaneo di cui si desidera modificare il periodo di tempo
     * @param dataApertura     la data e l'ora di apertura proposta
     * @param dataChiusura     la data e l'ora di chiusura proposta
     */

    public RichiestaModificaPeriodoTempo(UtenteRegistrato autoreRichiesta, Comune comune, PoiTemporaneo poiTemporaneo, LocalDateTime dataApertura, LocalDateTime dataChiusura) {
        super(autoreRichiesta, comune, poiTemporaneo);
        this.dataApertura = dataApertura;
        this.dataChiusura = dataChiusura;
    }

    public RichiestaModificaPeriodoTempo() {

    }

 /**
     * Restituisce la data e l'ora di chiusura proposta per il POI temporaneo.
     *
     * @return la data e l'ora di chiusura proposta
     */

    public LocalDateTime getDataChiusura() {
        return dataChiusura;
    }

/**
     * Restituisce la data e l'ora di apertura proposta per il POI temporaneo.
     *
     * @return la data e l'ora di apertura proposta
     */

    public LocalDateTime getDataApertura() {
        return dataApertura;
    }
}
