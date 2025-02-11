package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalTime;
import java.util.Objects;

@Entity
public class RichiestaModificaOrario extends Richiesta {

    @ManyToOne
    private Poi poi;
    private int giorno;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;

    public RichiestaModificaOrario(Account autoreRichiesta, Comune comune, Poi poi, int giorno, LocalTime orarioApertura, LocalTime orarioChiusura) {
        super(autoreRichiesta, comune);
        this.poi = poi;
        this.giorno = giorno;
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
    }

    public RichiestaModificaOrario() {

    }

    @Override
    public Poi getEntitaRichiesta() {
        return poi;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RichiestaModificaOrario richiestaModificaOrario = (RichiestaModificaOrario) o;
        return Objects.equals(poi, richiestaModificaOrario.poi) && Objects.equals(giorno, richiestaModificaOrario.giorno) && Objects.equals(orarioApertura, richiestaModificaOrario.orarioApertura) && Objects.equals(orarioChiusura, richiestaModificaOrario.orarioChiusura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), poi, giorno, orarioApertura, orarioChiusura);
    }
}
