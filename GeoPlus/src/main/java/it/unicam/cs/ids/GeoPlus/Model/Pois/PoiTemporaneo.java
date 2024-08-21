package it.unicam.cs.ids.GeoPlus.Model.Pois;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.PeriodoTempo;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;


import java.time.LocalDateTime;

/**
 * La classe PoiTemporaneo rappresenta un punto di interesse temporaneo,
 * che è attivo solo per un determinato periodo di apertura.
 */
public class PoiTemporaneo extends Poi {

    private PeriodoTempo periodoApertura;

    /**
     * Costruttore per creare un oggetto PoiTemporaneo.
     *
     * @param nomePoi          Il nome del punto di interesse.
     * @param descrizionePoi   La descrizione del punto di interesse.
     * @param posizionePoi     Le coordinate del punto di interesse.
     * @param comunePoi        Il comune a cui appartiene il punto di interesse.
     * @param autorePoi        L'utente registrato che ha creato il punto di interesse.
     * @param periodoApertura  Il periodo di apertura del punto di interesse temporaneo.
     */
    public PoiTemporaneo(String nomePoi, String descrizionePoi, Coordinate posizionePoi, Comune comunePoi, UtenteRegistrato autorePoi, PeriodoTempo periodoApertura) {
        super(nomePoi, descrizionePoi, posizionePoi, comunePoi, autorePoi);
        this.periodoApertura = periodoApertura;
    }


    public PoiTemporaneo() {}

    /**
     * Restituisce la data di chiusura del punto di interesse temporaneo.
     *
     * @return La data di chiusura.
     */
    public LocalDateTime getDataChiusura() {
        return periodoApertura.getDataFine();
    }

    /**
     * Imposta la data di chiusura del punto di interesse temporaneo.
     *
     * @param dataChiusura La nuova data di chiusura.
     */
    public void setDataChiusura(LocalDateTime dataChiusura) {
        this.periodoApertura.setDataFine(dataChiusura);
    }

    /**
     * Restituisce la data di apertura del punto di interesse temporaneo.
     *
     * @return La data di apertura.
     */
    public LocalDateTime getDataApertura() {
        return periodoApertura.getDataInizio();
    }

    /**
     * Imposta la data di apertura del punto di interesse temporaneo.
     *
     * @param dataApertura La nuova data di apertura.
     */
    public void setDataApertura(LocalDateTime dataApertura) {
        this.periodoApertura.setDataInizio(dataApertura);
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
