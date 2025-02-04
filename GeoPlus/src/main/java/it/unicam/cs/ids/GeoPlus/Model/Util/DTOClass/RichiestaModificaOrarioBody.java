package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import java.time.LocalTime;

public class RichiestaModificaOrarioBody {

    private Long poiId;

    private int giorno;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;

    public Long getPoiId() {
        return poiId;
    }


    public int getGiorno() {
        return giorno;
    }

    public LocalTime getOrarioApertura() {
        return orarioApertura;
    }

    public LocalTime getOrarioChiusura() {
        return orarioChiusura;
    }

}

