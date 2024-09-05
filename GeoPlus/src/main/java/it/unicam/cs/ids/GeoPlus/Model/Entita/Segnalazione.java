package it.unicam.cs.ids.GeoPlus.Model.Entita;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * La classe Segnalazione rappresenta una segnalazione effettuata da un utente registrato
 * riguardo a un contenuto specifico. Include informazioni sull'autore della segnalazione
 * e sul contenuto segnalato.
 */
@Entity
public class Segnalazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSegnalazione;
    @ManyToOne
    private UtenteRegistrato autoreSegnalazione;
    @ManyToOne
    private Contenuto contenutoSegnalato;
    @ManyToOne
    private Comune comuneSegnalazione;


    public Segnalazione(Comune comune, UtenteRegistrato autoreSegnalazione, Contenuto contenutoSegnalato) {
        this.autoreSegnalazione = autoreSegnalazione;
        this.contenutoSegnalato = contenutoSegnalato;
        this.comuneSegnalazione = comune;
    }

    public Segnalazione() {

    }


    public long getIdSegnalazione() {
        return idSegnalazione;
    }


    public UtenteRegistrato getAutoreSegnalazione() {
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
                Objects.equals(comuneSegnalazione, that.comuneSegnalazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSegnalazione, autoreSegnalazione, contenutoSegnalato, comuneSegnalazione);
    }
}