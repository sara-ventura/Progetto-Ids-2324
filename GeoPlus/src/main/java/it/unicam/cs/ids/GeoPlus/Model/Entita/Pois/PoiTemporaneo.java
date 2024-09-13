package it.unicam.cs.ids.GeoPlus.Model.Entita.Pois;

import it.unicam.cs.ids.GeoPlus.Model.Util.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class PoiTemporaneo extends Poi {
    @Embedded
    private PeriodoTempo periodoApertura;

    public PoiTemporaneo(String nomePoi, String descrizionePoi, Coordinate posizionePoi, PeriodoTempo periodoApertura) {
        super(nomePoi, descrizionePoi, posizionePoi);
        this.periodoApertura = periodoApertura;
    }


    public PoiTemporaneo() {
    }

    public PeriodoTempo getPeriodoApertura() {
        return periodoApertura;
    }


    public void setDataChiusura(LocalDateTime dataChiusura) {
        this.periodoApertura.setDataFine(dataChiusura);
    }


    public void setDataApertura(LocalDateTime dataApertura) {
        this.periodoApertura.setDataInizio(dataApertura);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        PoiTemporaneo that = (PoiTemporaneo) o;
        return Objects.equals(periodoApertura, that.periodoApertura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), periodoApertura);
    }
}
