package it.unicam.cs.ids.GeoPlus.Model.Entita.Contest;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;
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
    private UtenteStandard autoreContest;
    @ManyToMany
    private List<UtenteStandard> listaPartecipanti;
    @OneToMany
    private List<Contenuto> listaContenuti;
    @Embedded
    private PeriodoTempo periodoTempo;
    @OneToOne
    private UtenteStandard vincitoreContest;
    @ManyToMany
    private List<Poi> riferimenti;

    public Contest(String nomeContest, String descrizione, UtenteStandard autoreContest, String regole, boolean pubblico, PeriodoTempo periodoTempo, List<Poi> riferimenti) {
        this.nomeContest = nomeContest;
        this.descrizione = descrizione;
        this.autoreContest = autoreContest;
        this.regole = regole;
        this.pubblico = pubblico;
        this.periodoTempo = periodoTempo;
        this.riferimenti = riferimenti;
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


    public UtenteStandard getAutoreContest() {
        return this.autoreContest;
    }


    public boolean isPubblico() {
        return this.pubblico;
    }


    public void setPubblico(boolean pubblico) {
        this.pubblico = pubblico;
    }


    public void aggiungiContenutoContest(Contenuto contenuto) {
        if (!listaContenuti.contains(contenuto)) {
            this.listaContenuti.add(contenuto);
        }
    }


    public void rimuoviContenutoContest(Contenuto contenuto) {
        this.listaContenuti.remove(contenuto);
    }


    public void aggiungiPartecipanteContest(UtenteStandard partecipante) {
        if (!listaPartecipanti.contains(partecipante)) {
            this.listaPartecipanti.add(partecipante);
        }
    }


    public void rimuoviPartecipanteContest(UtenteStandard partecipante) {
        this.listaPartecipanti.remove(partecipante);
    }


    public PeriodoTempo getPeriodoTempo() {
        return periodoTempo;
    }

    public void setPeriodoTempo(PeriodoTempo periodoTempo) {
        this.periodoTempo = periodoTempo;
    }

    public List<UtenteStandard> getListaPartecipanti() {
        return listaPartecipanti;
    }

    public List<Contenuto> getListaContenuti() {
        return listaContenuti;
    }

    public UtenteStandard getVincitoreContest() {
        return vincitoreContest;
    }

    public void setVincitoreContest(UtenteStandard vincitoreContest) {
        this.vincitoreContest = vincitoreContest;
    }

    public boolean isUtentePartecipante(UtenteStandard partecipante) {
        return listaPartecipanti.contains(partecipante);
    }
    public List<Poi> getRiferimenti() {
        return riferimenti;
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