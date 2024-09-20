package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import java.time.LocalDateTime;

public class RichiestaModificaPeriodoTempoBody {
    private Long PoiId;
    private Long autoreRichiestaId;
    private LocalDateTime dataApertura;
    private LocalDateTime dataChiusura;


    public Long getPoiId() {
        return PoiId;
    }

    public void setPoiId(Long poiId) {
        this.PoiId = poiId;
    }

    public Long getAutoreRichiestaId() {
        return autoreRichiestaId;
    }

    public void setAutoreRichiestaId(Long autoreRichiestaId) {
        this.autoreRichiestaId = autoreRichiestaId;
    }

    public LocalDateTime getDataApertura() {
        return dataApertura;
    }

    public void setDataApertura(LocalDateTime dataApertura) {
        this.dataApertura = dataApertura;
    }

    public LocalDateTime getDataChiusura() {
        return dataChiusura;
    }

    public void setDataChiusura(LocalDateTime dataChiusura) {
        this.dataChiusura = dataChiusura;
    }
}

