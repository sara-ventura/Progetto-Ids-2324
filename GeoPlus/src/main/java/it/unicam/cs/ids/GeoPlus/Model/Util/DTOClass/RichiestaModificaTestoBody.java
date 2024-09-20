package it.unicam.cs.ids.GeoPlus.Model.Util.DTOClass;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.TipoModificaTesto;

public class RichiestaModificaTestoBody {
    private Long autoreRichiestaId;
    private Long entitaId;
    private String modificaTesto;
    private TipoModificaTesto tipoModifica;

    public Long getAutoreRichiestaId() {
        return autoreRichiestaId;
    }

    public void setAutoreRichiestaId(Long autoreRichiestaId) {
        this.autoreRichiestaId = autoreRichiestaId;
    }


    public Long getEntitaId() {
        return entitaId;
    }

    public void setEntitaId(Long entitaId) {
        this.entitaId = entitaId;
    }

    public String getModificaTesto() {
        return modificaTesto;
    }

    public void setModificaTesto(String modificaTesto) {
        this.modificaTesto = modificaTesto;
    }

    public TipoModificaTesto getTipoModifica() {
        return tipoModifica;
    }

    public void setTipoModifica(TipoModificaTesto tipoModifica) {
        this.tipoModifica = tipoModifica;
    }
}

