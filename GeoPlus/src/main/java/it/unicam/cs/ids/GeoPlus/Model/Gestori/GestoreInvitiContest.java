package it.unicam.cs.ids.GeoPlus.Model.Gestori;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.InvitoContest;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziAccount;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziContest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GestoreInvitiContest {
    @Autowired
    private ServiziAccount serviziAccount;
    @Autowired
    private ServiziContest serviziContest;

    public void accettaInvitoContest(InvitoContest invitoContest) {
        invitoContest.getUtenteInvitato().rimuoviInvitoContest(invitoContest);
        invitoContest.getContest().aggiungiPartecipanteContest(invitoContest.getUtenteInvitato());
        serviziAccount.salvaUtente(invitoContest.getUtenteInvitato());
        serviziContest.salvaContest(invitoContest.getContest());
        serviziContest.eliminaInvito(invitoContest);
    }


    public void rifiutaInvitoContest(InvitoContest invitoContest) {
        invitoContest.getUtenteInvitato().rimuoviInvitoContest(invitoContest);
        serviziAccount.salvaUtente(invitoContest.getUtenteInvitato());
        serviziContest.eliminaInvito(invitoContest);
    }
}
