package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import java.time.LocalTime;

public class RichiestaModificaOrarioBody {
    private Long autoreRichiestaId;
    private Long PoiId;
    private String modificaTesto;

    private int giorno;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;

    public Long getAutoreRichiestaId() {
        return autoreRichiestaId;
    }

    public void setAutoreRichiestaId(Long autoreRichiestaId) {
        this.autoreRichiestaId = autoreRichiestaId;
    }

    public Long getPoiId() {
        return PoiId;
    }

    public void setPoiId(Long poiId) {
        PoiId = poiId;
    }

    public String getModificaTesto() {
        return modificaTesto;
    }

    public void setModificaTesto(String modificaTesto) {
        this.modificaTesto = modificaTesto;
    }

    public int getGiorno() {
        return giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public LocalTime getOrarioApertura() {
        return orarioApertura;
    }

    public void setOrarioApertura(LocalTime orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public LocalTime getOrarioChiusura() {
        return orarioChiusura;
    }

    public void setOrarioChiusura(LocalTime orarioChiusura) {
        this.orarioChiusura = orarioChiusura;
    }
}

