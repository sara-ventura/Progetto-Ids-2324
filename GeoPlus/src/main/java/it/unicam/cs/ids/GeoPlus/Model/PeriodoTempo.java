package it.unicam.cs.ids.GeoPlus.Model;

import java.time.LocalDateTime;


/**
 * La classe PeriodoTempo rappresenta un intervallo di tempo definito da
 * una data di inizio e una data di fine.
 */
public class PeriodoTempo {

    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    /**
     * Costruttore per creare un oggetto PeriodoTempo.
     *
     * @param dataInizio La data di inizio del periodo.
     * @param dataFine   La data di fine del periodo.
     * @throws IllegalArgumentException Se la data di inizio non è antecedente alla data di fine.
     */
    public PeriodoTempo(LocalDateTime dataInizio, LocalDateTime dataFine) {
        if (this.validaDate(dataInizio, dataFine)) {
            this.dataInizio = dataInizio;
            this.dataFine = dataFine;
        } else {
            throw new IllegalArgumentException("La data di inizio deve essere antecedente alla data di fine.");
        }
    }

    /**
     * Restituisce la data di inizio del periodo.
     *
     * @return La data di inizio.
     */
    public LocalDateTime getDataInizio() {
        return this.dataInizio;
    }

    /**
     * Imposta la data di inizio del periodo.
     *
     * @param dataInizio La nuova data di inizio.
     */
    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    /**
     * Restituisce la data di fine del periodo.
     *
     * @return La data di fine.
     */
    public LocalDateTime getDataFine() {
        return this.dataFine;
    }

    /**
     * Imposta la data di fine del periodo.
     *
     * @param dataFine La nuova data di fine.
     */
    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    /**
     * Verifica se una data specificata è compresa all'interno del periodo.
     *
     * @param data La data da verificare.
     * @return true se la data è compresa tra la data di inizio e la data di fine, altrimenti false.
     */
    public boolean contieneData(LocalDateTime data) {
        return !data.isBefore(dataInizio) && !data.isAfter(dataFine);
    }

    /**
     * Verifica se una data specificata rappresenta una scadenza rispetto alla data di fine del periodo.
     *
     * @param dataOdierna La data da verificare.
     * @return true se la data odierna è successiva alla data di fine, altrimenti false.
     */
    public boolean verificaScadenza(LocalDateTime dataOdierna) {
        return dataOdierna.isAfter(dataFine);
    }

    /**
     * Valida che la data di inizio sia antecedente o uguale alla data di fine.
     *
     * @param dataInizio La data di inizio da validare.
     * @param dataFine   La data di fine da validare.
     * @return true se la data di inizio è valida, altrimenti false.
     */
    private boolean validaDate(LocalDateTime dataInizio, LocalDateTime dataFine) {
        return !dataInizio.isAfter(dataFine);
    }
}