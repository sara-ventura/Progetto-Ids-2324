package it.unicam.cs.ids.GeoPlus.Model.Util;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class PeriodoTempo {

    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    public PeriodoTempo(LocalDateTime dataInizio, LocalDateTime dataFine) {
        if (this.validaDate(dataInizio, dataFine)) {
            this.dataInizio = dataInizio;
            this.dataFine = dataFine;
        } else {
            throw new IllegalArgumentException("La data di inizio deve essere antecedente alla data di fine.");
        }
    }

    public PeriodoTempo() {

    }

    public LocalDateTime getDataInizio() {
        return this.dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return this.dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public boolean contieneData(LocalDateTime data) {
        return !data.isBefore(dataInizio) && !data.isAfter(dataFine);
    }

    public boolean verificaScadenza(LocalDateTime dataOdierna) {
        return dataOdierna.isAfter(dataFine);
    }

    private boolean validaDate(LocalDateTime dataInizio, LocalDateTime dataFine) {
        return !dataInizio.isAfter(dataFine);
    }
}