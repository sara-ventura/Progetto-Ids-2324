package it.unicam.cs.ids.GeoPlus.Model.Contest;

import it.unicam.cs.ids.GeoPlus.Model.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Contest rappresenta un contest (concorso) creato da un utente registrato.
 * Contiene informazioni sul nome, descrizione, regole, visibilità e i partecipanti al contest.
 */
public class Contest {
    // private Long idContest; // ID del contest, commentato per attuale assenza.

    private String nomeContest;
    private String descrizione;
    private String regole;
    private boolean pubblico;
    private final UtenteRegistrato autoreContest;
    private final List<UtenteRegistrato> listaPartecipanti;
    private final List<Contenuto> listaContenuti;

    /**
     * Costruttore per creare un oggetto Contest.
     *
     * @param nomeContest   Il nome del contest.
     * @param descrizione   La descrizione del contest.
     * @param autoreContest L'utente registrato che crea il contest.
     * @param regole        Le regole del contest.
     * @param pubblico      Indica se il contest è pubblico.
     */
    public Contest(String nomeContest, String descrizione, UtenteRegistrato autoreContest, String regole, boolean pubblico) {
        this.nomeContest = nomeContest;
        this.descrizione = descrizione;
        this.autoreContest = autoreContest;
        this.regole = regole;
        this.pubblico = pubblico;
        this.listaPartecipanti = new ArrayList<>();
        this.listaContenuti = new ArrayList<>();
    }

    /**
     * Restituisce le regole del contest.
     *
     * @return Le regole del contest.
     */
    public String getRegole() {
        return this.regole;
    }

    /**
     * Imposta le regole del contest.
     *
     * @param regole Le nuove regole del contest.
     */
    public void setRegole(String regole) {
        this.regole = regole;
    }

    // Uncomment se l'ID del contest è necessario
    // public Long getIdContest() {
    //     return this.idContest;
    // }

    /**
     * Restituisce il nome del contest.
     *
     * @return Il nome del contest.
     */
    public String getNomeContest() {
        return this.nomeContest;
    }

    /**
     * Restituisce la descrizione del contest.
     *
     * @return La descrizione del contest.
     */
    public String getDescrizioneContest() {
        return this.descrizione;
    }

    /**
     * Restituisce l'autore del contest.
     *
     * @return L'utente registrato che ha creato il contest.
     */
    public UtenteRegistrato getAutoreContest() {
        return this.autoreContest;
    }

    /**
     * Verifica se il contest è pubblico.
     *
     * @return true se il contest è pubblico, false altrimenti.
     */
    public boolean isPubblico() {
        return this.pubblico;
    }

    /**
     * Imposta la visibilità del contest.
     *
     * @param pubblico Indica se il contest deve essere pubblico.
     */
    public void setPubblico(boolean pubblico) {
        this.pubblico = pubblico;
    }

    /**
     * Aggiunge un contenuto al contest.
     *
     * @param contenuto Il contenuto da aggiungere.
     * @return true se il contenuto è stato aggiunto, false se non è stato aggiunto.
     */
    public boolean aggiungiContenutoContest(Contenuto contenuto) {
        return this.listaContenuti.add(contenuto);
    }

    /**
     * Rimuove un contenuto dal contest.
     *
     * @param contenuto Il contenuto da rimuovere.
     * @return true se il contenuto è stato rimosso, false altrimenti.
     */
    public boolean rimuoviContenutoContest(Contenuto contenuto) {
        return this.listaContenuti.remove(contenuto);
    }

    /**
     * Aggiunge un partecipante al contest.
     *
     * @param partecipante L'utente registrato da aggiungere come partecipante.
     * @return true se il partecipante è stato aggiunto, false altrimenti.
     */
    public boolean aggiungiPartecipanteContest(UtenteRegistrato partecipante) {
        return this.listaPartecipanti.add(partecipante);
    }

    /**
     * Rimuove un partecipante dal contest.
     *
     * @param partecipante L'utente registrato da rimuovere come partecipante.
     * @return true se il partecipante è stato rimosso, false altrimenti.
     */
    public boolean rimuoviPartecipanteContest(UtenteRegistrato partecipante) {
        return this.listaPartecipanti.remove(partecipante);
    }


    // @Override
    // public boolean equals(Object o) {
    //     // Implementazione dell'uguaglianza
    // }

    // @Override
    // public int hashCode() {
    //     // Implementazione del calcolo dell'hash
    // }
}