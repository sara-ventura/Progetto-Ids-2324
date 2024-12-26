package it.unicam.cs.ids.GeoPlus.Model.Entita;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Segnalazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSegnalazione;
    @ManyToOne
    private Account autoreSegnalazione;
    @ManyToOne
    private Contenuto contenutoSegnalato;
    @ManyToOne
    private Comune comuneSegnalazione;

    private String motivoSegnalazione;

    public Segnalazione(Comune comune, Account autoreSegnalazione, Contenuto contenutoSegnalato, String motivoSegnalazione) {
        this.autoreSegnalazione = autoreSegnalazione;
        this.contenutoSegnalato = contenutoSegnalato;
        this.comuneSegnalazione = comune;
        this.motivoSegnalazione = motivoSegnalazione;
    }

    public Segnalazione() {

    }


    public long getIdSegnalazione() {
        return idSegnalazione;
    }


    public Account getAutoreSegnalazione() {
        return autoreSegnalazione;
    }

    public Contenuto getContenutoSegnalato() {
        return contenutoSegnalato;
    }

    public Comune getComuneSegnalazione() {
        return comuneSegnalazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segnalazione that = (Segnalazione) o;
        return Objects.equals(idSegnalazione, that.idSegnalazione) &&
                Objects.equals(autoreSegnalazione, that.autoreSegnalazione) &&
                Objects.equals(contenutoSegnalato, that.contenutoSegnalato) &&
                Objects.equals(comuneSegnalazione, that.comuneSegnalazione) &&
                Objects.equals(motivoSegnalazione, that.motivoSegnalazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSegnalazione, autoreSegnalazione, contenutoSegnalato, comuneSegnalazione, motivoSegnalazione);
    }
}