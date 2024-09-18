package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import it.unicam.cs.ids.GeoPlus.Model.Repository.ContestRepository;
import it.unicam.cs.ids.GeoPlus.Model.Util.PeriodoTempo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiziContest {


    @Autowired
    private ContestRepository contestRepository;

    public Contest creaContest(String nomeContest, String descrizione, UtenteStandard autoreContest, String regole, boolean pubblico, PeriodoTempo periodoTempo) {
        Contest contest = new Contest(nomeContest, descrizione, autoreContest, regole, pubblico, periodoTempo);
        return contestRepository.save(contest);
    }

    public Contest getContestById(Long id) {
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

    public void aggiungiContenutoContest(Contest contest, Contenuto contenuto) {
        contest.aggiungiContenutoContest(contenuto);
        contestRepository.save(contest);
    }


    public void rimuoviContenutoContest(Contest contest, Contenuto contenuto) {
        contest.rimuoviContenutoContest(contenuto);
        contestRepository.save(contest);
    }


}
