package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.Objects;


@Entity
public class RichiestaModificaTestoPoi extends RichiestaModificaTesto {

    @ManyToOne
    private Poi poi;

    public RichiestaModificaTestoPoi(Account autoreRichiesta, Comune comune, Poi poi, String modificaTesto, TipoModificaTesto tipoModifica) {
        super(autoreRichiesta, comune, modificaTesto, tipoModifica);
        this.poi = poi;
    }

    public RichiestaModificaTestoPoi() {

    }

    @Override
    public Poi getEntitaRichiesta() {
        return poi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RichiestaModificaTestoPoi richiestaModificaTestoPoi = (RichiestaModificaTestoPoi) o;
        return Objects.equals(poi, richiestaModificaTestoPoi.poi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), poi);
    }
}
