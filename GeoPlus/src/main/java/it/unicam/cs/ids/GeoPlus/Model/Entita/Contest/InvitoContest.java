package it.unicam.cs.ids.GeoPlus.Model.Entita.Contest;


import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class InvitoContest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvitoContest;
    @ManyToOne
    private Contest contest;
    @ManyToOne
    private UtenteRegistrato utenteInvitato;


    public InvitoContest(Contest contest, UtenteRegistrato utenteInvitato) {
        this.contest = contest; // Inizializza il contest
        this.utenteInvitato = utenteInvitato;
    }

    public InvitoContest() {

    }

    public Long getIdInvitoContest() {
        return idInvitoContest;
    }

    public Contest getContest() {
        return this.contest;
    }

    public UtenteRegistrato getUtenteInvitato() {
        return this.utenteInvitato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvitoContest that = (InvitoContest) o;
        return Objects.equals(idInvitoContest, that.idInvitoContest) &&
                Objects.equals(contest, that.contest) &&
                Objects.equals(utenteInvitato, that.utenteInvitato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInvitoContest, contest, utenteInvitato);
    }
}