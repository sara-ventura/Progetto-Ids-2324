package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Richiesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RichiestaId;
    @ManyToOne
    private Account autoreRichiesta;
    @ManyToOne @JsonIgnore
    private Comune comune;


    public Richiesta() {
    }


    public Richiesta(Account autoreRichiesta, Comune comune) {
        this.autoreRichiesta = autoreRichiesta;
        this.comune = comune;
    }
    public Long getRichiestaId() {
        return RichiestaId;
    }


    public Account getAutoreRichiesta() {
        return autoreRichiesta;
    }

    public Comune getComune() {
        return this.comune;
    }


    public abstract EntitaRichiesta getEntitaRichiesta();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Richiesta richiesta = (Richiesta) o;
        return Objects.equals(autoreRichiesta, richiesta.autoreRichiesta) &&
                Objects.equals(comune, richiesta.comune);
    }


    @Override
    public int hashCode() {
        return Objects.hash(autoreRichiesta, comune);
    }



}
