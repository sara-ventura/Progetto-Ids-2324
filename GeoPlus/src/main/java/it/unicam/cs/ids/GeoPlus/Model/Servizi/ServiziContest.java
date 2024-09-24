package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.InvitoContest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Repository.ContestRepository;

import it.unicam.cs.ids.GeoPlus.Model.Repository.InvitoConetstRepository;
import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ServiziContest {


    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private InvitoConetstRepository invitoContestRepository;
    @Autowired
    private ServiziComune serviziComune;
    @Autowired
    private ServiziUtenteRegistrato serviziUtenteRegistrato;

    public void creaContest(String nomeContest, String descrizione, UtenteStandard autoreContest, String regole, boolean pubblico, PeriodoTempo periodoTempo, List<Poi> riferimenti) {
        Comune comuneBase = serviziComune.getComune(riferimenti.getFirst().getPosizionePoi());
        if (!Objects.equals(comuneBase.getIdComune(), autoreContest.getComuneAppartenenza().getIdComune())) {
            throw new IllegalArgumentException("Un animatore non può creare contest con riferimenti fuori dal comune");
        }
        for (Poi poi : riferimenti) {
            Comune comuneCorrente = serviziComune.getComune(poi.getPosizionePoi());
            if (!Objects.equals(comuneBase.getIdComune(), comuneCorrente.getIdComune())) {
                throw new IllegalArgumentException("Tutti i POI devono appartenere allo stesso comune.");
            }
        }
        Contest contest = new Contest(nomeContest, descrizione, autoreContest, regole, pubblico, periodoTempo, riferimenti);
        contest.setPeriodoTempo(periodoTempo);
        contestRepository.save(contest);
    }

    public void salvaContest(Contest contest) {
        contestRepository.save(contest);
    }

    public Contest getContest(Long id) {
        return contestRepository.findById(id).orElse(null);
    }

    public List<Contest> getContests(String nomeContest) {
        return contestRepository.findAllByNomeContest(nomeContest);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void rimuoviContestScaduti() {
        List<Contest> contestList = contestRepository.findAll();
        LocalDateTime oraCorrente = LocalDateTime.now();
        for (Contest contest : contestList) {
            if (contest.getPeriodoTempo().verificaScadenza(oraCorrente)) {
                contestRepository.delete(contest);
            }
        }
    }

    public void iscriviUtente(UtenteStandard utente, Contest contest) {
        contest.aggiungiPartecipanteContest(utente);
        contestRepository.save(contest);
    }


    public void rimuoviUtente(UtenteStandard utente, Contest contest) {
        contest.rimuoviPartecipanteContest(utente);
        contestRepository.save(contest);
    }

    public void aggiungiContenutoContest(Contest contest, Contenuto contenuto, UtenteStandard partecipante) {
        if (contest.isUtentePartecipante(partecipante)) {
            contest.aggiungiContenutoContest(contenuto);
            contestRepository.save(contest);
        }
    }


    public void rimuoviContenutoContest(Contest contest, Contenuto contenuto, UtenteStandard partecipante) {
        if (contest.isUtentePartecipante(partecipante)) {
            contest.rimuoviContenutoContest(contenuto);
            contestRepository.save(contest);
        }
    }

    public void creaInvito(UtenteStandard utente, Contest contest) {
        InvitoContest invito = new InvitoContest(contest, utente);
        invitoContestRepository.save(invito);
        aggiungiInvito(invito);
    }

    public void eliminaInvito(InvitoContest invito) {
        UtenteStandard utenteInvitato = invito.getUtenteInvitato();
        utenteInvitato.rimuoviInvitoContest(invito);
        serviziUtenteRegistrato.salvaUtente(utenteInvitato);
    }


    public void aggiungiInvito(InvitoContest invito) {
        UtenteStandard utenteInvitato = invito.getUtenteInvitato();
        utenteInvitato.aggiungiInvitoContest(invito);
        serviziUtenteRegistrato.salvaUtente(utenteInvitato);
    }

    public InvitoContest getInvito(long invito) {
        return invitoContestRepository.findById(invito).orElse(null);
    }

}
