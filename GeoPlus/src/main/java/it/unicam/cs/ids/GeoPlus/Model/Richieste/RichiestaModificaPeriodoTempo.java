package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.time.LocalDateTime;

/**
 * La classe RichiestaModificaPeriodoTempo rappresenta una richiesta
 * per modificare il periodo di apertura e chiusura associato a un oggetto
 * di tipo PoiTemporaneo nel sistema GeoPlus.
 * Estende la classe generica Richiesta<PoiTemporaneo> specificando
 * che questa richiesta è associata a entità di tipo PoiTemporaneo.
 * Questa classe è utilizzata per gestire modifiche temporali di eventi o punti
 * di interesse che hanno un periodo di validità definito.
 */

public class RichiestaModificaPeriodoTempo extends Richiesta<PoiTemporaneo> {


   private LocalDateTime dataApertura;
   private LocalDateTime dataChiusura;

 /**
     * Costruttore che inizializza una nuova richiesta di modifica del periodo
     * di tempo per un PoiTemporaneo con i dettagli specificati.
     *
     * @param autoreRichiesta l'utente registrato che ha effettuato la richiesta
     * @param comune          il comune associato alla richiesta
     * @param poiTemporaneo   il punto di interesse temporaneo al quale si riferisce la modifica del periodo di tempo
     * @param dataApertura    la data e l'ora di apertura proposte
     * @param dataChiusura    la data e l'ora di chiusura proposte
     */

    public RichiestaModificaPeriodoTempo(UtenteRegistrato autoreRichiesta, Comune comune, PoiTemporaneo poiTemporaneo, LocalDateTime dataApertura, LocalDateTime dataChiusura) {
        super(autoreRichiesta, comune, poiTemporaneo);
        this.dataApertura = dataApertura;
        this.dataChiusura = dataChiusura;
    }

    public RichiestaModificaPeriodoTempo() {

    }

 /**
     * Restituisce la data e l'ora di chiusura proposte per il periodo di validità.
     *
     * @return la data e l'ora di chiusura proposte
     */


    public LocalDateTime getDataChiusura() {
        return dataChiusura;
    }

  /**
     * Restituisce la data e l'ora di apertura proposte per il periodo di validità.
     *
     * @return la data e l'ora di apertura proposte
     */

    public LocalDateTime getDataApertura() {
        return dataApertura;
    }
}