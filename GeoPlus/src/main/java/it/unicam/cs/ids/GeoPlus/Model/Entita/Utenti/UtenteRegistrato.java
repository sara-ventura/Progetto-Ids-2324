package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class UtenteRegistrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtente;


    private Long idCredenziali;


    @Enumerated(EnumType.STRING)
    private Ruoli ruoloUtente;


    @ManyToOne
    private Comune comuneAppartenenza;

    public UtenteRegistrato(Ruoli ruoloUtente, long credenziali) {
        this.ruoloUtente = ruoloUtente;
        this.idCredenziali = credenziali;
    }

    public UtenteRegistrato() {

    }

    public Long getIdUtente() {
        return idUtente;
    }

    public Long getIdCredenziali() {
        return idCredenziali;
    }

    public Ruoli getRuoloUtente() {
        return ruoloUtente;
    }

    public Comune getComuneAppartenenza() {
        return comuneAppartenenza;
    }

    public void setRuoloUtente(Ruoli ruoloUtente) {
        this.ruoloUtente = ruoloUtente;
    }

    public void setComuneAppartenenza(Comune comuneAppartenenza) {
        this.comuneAppartenenza = comuneAppartenenza;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtenteRegistrato that = (UtenteRegistrato) o;
        return Objects.equals(idCredenziali, that.idCredenziali) && Objects.equals(ruoloUtente, that.ruoloUtente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCredenziali, ruoloUtente);
    }
}