package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class RichiestaCaricamentoContenuto extends RichiestaCaricamento {
    @OneToOne
    private Contenuto contenuto;

    public RichiestaCaricamentoContenuto(Account autoreRichiesta, Comune comune, Contenuto Contenuto) {
        super(autoreRichiesta, comune);
        this.contenuto = Contenuto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RichiestaCaricamentoContenuto richiestaCaricamentoContenuto = (RichiestaCaricamentoContenuto) o;
        return Objects.equals(contenuto, richiestaCaricamentoContenuto.contenuto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contenuto);
    }


    public RichiestaCaricamentoContenuto() {
    }

    @Override
    public Contenuto getEntitaRichiesta() {
        return contenuto;
    }
}
