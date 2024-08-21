package it.unicam.cs.ids.GeoPlus.Model.Contest;


import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

/**
 * La classe InvitoContest rappresenta un invito a partecipare a un contest.
 * Include informazioni sul contest a cui si è invitati e sull'utente invitato.
 */
public class InvitoContest {

    private final Contest contest; // Contest a cui si è invitati
    private final UtenteRegistrato utenteInvitato;

    /**
     * Costruttore per creare un oggetto InvitoContest.
     *
     * @param contest         Il contest a cui si è invitati.
     * @param utenteInvitato  L'utente registrato invitato al contest.
     */
    public InvitoContest(Contest contest, UtenteRegistrato utenteInvitato) {
        this.contest = contest; // Inizializza il contest
        this.utenteInvitato = utenteInvitato;
    }

    /**
     * Restituisce il contest associato all'invito.
     *
     * @return Il contest a cui si è invitati.
     */
    public Contest getContest() {
        return this.contest;
    }

    /**
     * Restituisce l'utente invitato al contest.
     *
     * @return L'utente registrato invitato.
     */
    public UtenteRegistrato getUtenteInvitato() {
        return this.utenteInvitato;
    }
}