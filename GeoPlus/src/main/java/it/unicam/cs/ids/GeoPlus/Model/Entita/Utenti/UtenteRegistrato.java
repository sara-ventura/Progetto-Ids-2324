package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Rappresenta un utente registrato nel sistema GeoPlus.
 * Un utente registrato ha un ruolo, un comune di appartenenza, e può salvare o condividere contenuti,
 * nonché ricevere inviti a contest.
 */
@Entity
public class UtenteRegistrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtente;


    private Long idCredenziali;


    @Embedded
    private Ruoli ruoloUtente;


    @ManyToOne
    private Comune comuneAppartenenza;

    /**
     * Costruttore della classe UtenteRegistrato.
     *
     * @param ruoloUtente Il ruolo assegnato all'utente.
     * @param credenziali Le credenziali associate all'utente.
     */
    public UtenteRegistrato(Ruoli ruoloUtente, long credenziali) {
        this.ruoloUtente = ruoloUtente;
        this.idCredenziali = credenziali;
    }

    public UtenteRegistrato() {

    }

    /**
     * Restituisce l'identificatore univoco dell'utente.
     *
     * @return L'ID dell'utente.
     */
    public Long getIdUtente() {
        return idUtente;
    }

    /**
     * Restituisce l'identificatore delle credenziali dell'utente.
     *
     * @return L'ID delle credenziali.
     */
    public Long getIdCredenziali() {
        return idCredenziali;
    }

    /**
     * Restituisce il ruolo associato all'utente.
     *
     * @return Il ruolo dell'utente.
     */
    public Ruoli getRuoloUtente() {
        return ruoloUtente;
    }

    /**
     * Restituisce il comune di appartenenza dell'utente.
     *
     * @return Il comune di appartenenza.
     */
    public Comune getComuneAppartenenza() {
        return comuneAppartenenza;
    }

    /**
     * Imposta il ruolo dell'utente.
     *
     * @param ruoloUtente Il ruolo da assegnare all'utente.
     */
    public void setRuoloUtente(Ruoli ruoloUtente) {
        this.ruoloUtente = ruoloUtente;
    }

    /**
     * Imposta il comune di appartenenza dell'utente.
     *
     * @param comuneAppartenenza Il comune da assegnare all'utente.
     */
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