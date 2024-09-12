package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class RichiestaCaricamentoPoi extends Richiesta {
    @OneToOne
    private Poi poi;

    public RichiestaCaricamentoPoi(UtenteRegistrato autoreRichiesta, Comune comune, Poi poi) {
        super(autoreRichiesta, comune);
        this.poi = poi;

    }

    public RichiestaCaricamentoPoi() {

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
        RichiestaCaricamentoPoi richiestaCaricamentoPoi = (RichiestaCaricamentoPoi) o;
        return Objects.equals(poi, richiestaCaricamentoPoi.poi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), poi);
    }
}
