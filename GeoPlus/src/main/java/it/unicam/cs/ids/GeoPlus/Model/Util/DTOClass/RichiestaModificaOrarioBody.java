package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import java.time.LocalTime;

public class RichiestaModificaOrarioBody {

    private Long PoiId;
    private String modificaTesto;

    private int giorno;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;

    public Long getPoiId() {
        return PoiId;
    }

    public String getModificaTesto() {
        return modificaTesto;
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

