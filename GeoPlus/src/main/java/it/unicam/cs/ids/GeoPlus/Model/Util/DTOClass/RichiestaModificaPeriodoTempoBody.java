package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import java.time.LocalDateTime;

public class RichiestaModificaPeriodoTempoBody {
    private Long PoiId;

    private LocalDateTime dataApertura;
    private LocalDateTime dataChiusura;


    public Long getPoiId() {
        return PoiId;
    }


    public LocalDateTime getDataApertura() {
        return dataApertura;
    }


    public LocalDateTime getDataChiusura() {
        return dataChiusura;
    }
}

