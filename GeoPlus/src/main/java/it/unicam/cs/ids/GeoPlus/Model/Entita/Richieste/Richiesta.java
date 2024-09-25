package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Richiesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RichiestaId;
    @ManyToOne
    private UtenteStandard autoreRichiesta;
    @ManyToOne
    private Comune comune;


    public Richiesta() {
    }


    public Richiesta(UtenteStandard autoreRichiesta, Comune comune) {
        this.autoreRichiesta = autoreRichiesta;
        this.comune = comune;
    }



    public UtenteStandard getAutoreRichiesta() {
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
