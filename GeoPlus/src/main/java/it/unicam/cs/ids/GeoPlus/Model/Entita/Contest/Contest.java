package it.unicam.cs.ids.GeoPlus.Model.Entita.Contest;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContest;

    private String nomeContest;
    private String descrizione;
    private String regole;
    private boolean pubblico;
    @ManyToOne
    private UtenteRegistrato autoreContest;
    @OneToMany
    private List<UtenteRegistrato> listaPartecipanti;
    @OneToMany
    private List<Contenuto> listaContenuti;
    @Embedded
    private PeriodoTempo periodoTempo;
    @OneToOne
    private UtenteRegistrato vincitoreContest;


    public Contest(String nomeContest, String descrizione, UtenteRegistrato autoreContest, String regole, boolean pubblico, PeriodoTempo periodoTempo) {
        this.nomeContest = nomeContest;
        this.descrizione = descrizione;
        this.autoreContest = autoreContest;
        this.regole = regole;
        this.pubblico = pubblico;
        this.periodoTempo = periodoTempo;
        this.listaPartecipanti = new ArrayList<>();
        this.listaContenuti = new ArrayList<>();
    }

    public Contest() {
        this.listaPartecipanti = new ArrayList<>();
        this.listaContenuti = new ArrayList<>();
    }


    public String getRegole() {
        return this.regole;
    }

    public void setRegole(String regole) {
        this.regole = regole;
    }


    public String getNomeContest() {
        return this.nomeContest;
    }

    public void setNomeContest(String nomeContest) {
        this.nomeContest = nomeContest;
    }


    public String getDescrizioneContest() {
        return this.descrizione;
    }

    public void setDescrizioneContest(String descrizione) {
        this.descrizione = descrizione;
    }


    public UtenteRegistrato getAutoreContest() {
        return this.autoreContest;
    }


    public boolean isPubblico() {
        return this.pubblico;
    }


    public void setPubblico(boolean pubblico) {
        this.pubblico = pubblico;
    }


    public boolean aggiungiContenutoContest(Contenuto contenuto) {
        if (!listaContenuti.contains(contenuto)) {
            return this.listaContenuti.add(contenuto);
        } else return false;
    }


    public boolean rimuoviContenutoContest(Contenuto contenuto) {
        return this.listaContenuti.remove(contenuto);
    }


    public boolean aggiungiPartecipanteContest(UtenteRegistrato partecipante) {
        if (!listaPartecipanti.contains(partecipante)) {
            return this.listaPartecipanti.add(partecipante);
        }
        return false;
    }


    public boolean rimuoviPartecipanteContest(UtenteRegistrato partecipante) {
        return this.listaPartecipanti.remove(partecipante);
    }


    public PeriodoTempo getPeriodoTempo() {
        return periodoTempo;
    }

    public void setPeriodoTempo(PeriodoTempo periodoTempo) {
        this.periodoTempo = periodoTempo;
    }

    public List<UtenteRegistrato> getListaPartecipanti() {
        return listaPartecipanti;
    }

    public List<Contenuto> getListaContenuti() {
        return listaContenuti;
    }

    public UtenteRegistrato getVincitoreContest() {
        return vincitoreContest;
    }

    public void setVincitoreContest(UtenteRegistrato vincitoreContest) {
        this.vincitoreContest = vincitoreContest;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contest contest = (Contest) o;
        return pubblico == contest.pubblico &&
                Objects.equals(idContest, contest.idContest) &&
                Objects.equals(nomeContest, contest.nomeContest) &&
                Objects.equals(descrizione, contest.descrizione) &&
                Objects.equals(regole, contest.regole) &&
                Objects.equals(autoreContest, contest.autoreContest);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idContest, nomeContest, descrizione, regole, pubblico, autoreContest);
    }
}