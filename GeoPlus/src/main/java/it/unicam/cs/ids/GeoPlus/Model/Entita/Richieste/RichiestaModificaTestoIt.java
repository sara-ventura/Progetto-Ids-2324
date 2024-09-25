package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.Objects;


@Entity
public class RichiestaModificaTestoIt extends RichiestaModificaTesto {

    @ManyToOne
    private Itinerario itinerario;


    public RichiestaModificaTestoIt(UtenteStandard autoreRichiesta, Comune comune, Itinerario itinerario, String modificaTesto, TipoModificaTesto tipoModifica) {
        super(autoreRichiesta, comune, modificaTesto, tipoModifica);
        this.itinerario = itinerario;
    }

    public RichiestaModificaTestoIt() {

    }

    @Override
    public Itinerario getEntitaRichiesta() {
        return itinerario;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RichiestaModificaTestoIt richiestaModificaTestoIt = (RichiestaModificaTestoIt) o;
        return Objects.equals(itinerario, richiestaModificaTestoIt.itinerario);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itinerario);
    }
}
