package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.TipoModificaTesto;

public class RichiestaModificaTestoBody {

    private Long entitaId;
    private String modificaTesto;
    private TipoModificaTesto tipoModifica;

    public Long getEntitaId() {
        return entitaId;
    }

    public String getModificaTesto() {
        return modificaTesto;
    }

    public TipoModificaTesto getTipoModifica() {
        return tipoModifica;
    }

}

