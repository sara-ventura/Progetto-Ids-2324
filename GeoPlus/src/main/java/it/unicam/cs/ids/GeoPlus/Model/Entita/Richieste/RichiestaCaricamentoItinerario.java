package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class RichiestaCaricamentoItinerario extends Richiesta {
    @OneToOne
    private Itinerario itinerario;

    public RichiestaCaricamentoItinerario(UtenteRegistrato autoreRichiesta, Comune comune, Itinerario Itinerario) {
        super(autoreRichiesta, comune);
        this.itinerario = Itinerario;

    }

    public RichiestaCaricamentoItinerario() {

    }

    @Override
    public Itinerario getEntitaRichiesta() {
        return itinerario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RichiestaCaricamentoItinerario richiestaCaricamentoItinerario = (RichiestaCaricamentoItinerario) o;
        return Objects.equals(itinerario, richiestaCaricamentoItinerario.itinerario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itinerario);
    }
}
