package it.unicam.cs.ids.GeoPlus.Model.Gestori;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.InvitoContest;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziContest;
import it.unicam.cs.ids.GeoPlus.Model.Servizi.ServiziUtenteRegistrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GestoreInvitiContest {
    @Autowired
    private ServiziUtenteRegistrato serviziUtenteRegistrato;
    @Autowired
    private ServiziContest serviziContest;

    public void accettaInvitoContest(InvitoContest invitoContest) {
        invitoContest.getUtenteInvitato().rimuoviInvitoContest(invitoContest);
        invitoContest.getContest().aggiungiPartecipanteContest(invitoContest.getUtenteInvitato());
        serviziUtenteRegistrato.salvaUtente(invitoContest.getUtenteInvitato());
        serviziContest.salvaContest(invitoContest.getContest());
        serviziContest.eliminaInvito(invitoContest);
    }


    public void rifiutaInvitoContest(InvitoContest invitoContest) {
        invitoContest.getUtenteInvitato().rimuoviInvitoContest(invitoContest);
        serviziUtenteRegistrato.salvaUtente(invitoContest.getUtenteInvitato());
        serviziContest.eliminaInvito(invitoContest);
    }
}
