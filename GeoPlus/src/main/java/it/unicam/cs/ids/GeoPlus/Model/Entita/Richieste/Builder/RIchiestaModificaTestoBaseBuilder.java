package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.TipoModificaTesto;

public abstract class RIchiestaModificaTestoBaseBuilder extends RichiestaBaseBuilder {

    private TipoModificaTesto tipoModificaTesto;
    private String modificaTesto;

    public TipoModificaTesto getTipoModificaTesto() {
        return tipoModificaTesto;
    }
    public String getModificaTesto() {
        return modificaTesto;
    }
    public void setTipoModificaTesto(TipoModificaTesto modificaTesto) {
        this.tipoModificaTesto = modificaTesto;
    }
    public void setModificaTesto(String modificaTesto) {
        this.modificaTesto = modificaTesto;
    }

}
