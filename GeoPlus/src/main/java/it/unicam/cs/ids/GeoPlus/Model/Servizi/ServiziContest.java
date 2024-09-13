package it.unicam.cs.ids.GeoPlus.Model.Servizi;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.Contest;
import it.unicam.cs.ids.GeoPlus.Model.Repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiziContest {
    @Autowired
    private ServiziContest serviziContest;

    @Autowired
    private ContestRepository contestRepository;

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

}
