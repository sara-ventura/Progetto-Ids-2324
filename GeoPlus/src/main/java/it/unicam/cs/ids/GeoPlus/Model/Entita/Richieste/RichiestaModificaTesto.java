package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

import java.util.Objects;


@Entity
public abstract class RichiestaModificaTesto extends Richiesta {

    private String modificaTesto;
    @Enumerated
    private TipoModificaTesto tipoModifica;



    public RichiestaModificaTesto(UtenteStandard autoreRichiesta, Comune comune, String modificaTesto, TipoModificaTesto tipoModifica) {
        super(autoreRichiesta, comune);
        this.modificaTesto = modificaTesto;
        this.tipoModifica = tipoModifica;
    }

    public RichiestaModificaTesto() {
    }





    public String getModificaTesto() {
        return modificaTesto;
    }

    public TipoModificaTesto getTipoModifica() {
        return this.tipoModifica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RichiestaModificaTesto richiestaModificaTesto = (RichiestaModificaTesto) o;
        return Objects.equals(modificaTesto, richiestaModificaTesto.modificaTesto) && Objects.equals(tipoModifica, richiestaModificaTesto.tipoModifica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), modificaTesto, tipoModifica);
    }
}
