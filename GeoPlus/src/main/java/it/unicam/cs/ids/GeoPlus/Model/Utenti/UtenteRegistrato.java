package it.unicam.cs.ids.GeoPlus.Model.Utenti;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Contest.InvitoContest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Rappresenta un utente registrato nel sistema GeoPlus.
 * Un utente registrato ha un ruolo, un comune di appartenenza, e può salvare o condividere contenuti,
 * nonché ricevere inviti a contest.
 */
public class UtenteRegistrato {


    private Long idUtente;


    private String email;


    private String password;


    private Ruoli ruoloUtente;


    private Comune comuneApparteneza;


    private List<Contenuto> listaContenutiSalvati;


    private List<Contenuto> listaContenutiCondivisi;


    private List<InvitoContest> listaInvitoContest;

    /**
     * Crea un nuovo utente registrato con i parametri specificati.
     *
     * @param ruoloUtente        Il ruolo dell'utente.
     * @param comuneApparteneza  Il comune di appartenenza dell'utente.
     * @param email              L'email dell'utente.
     * @param password           La password dell'utente.
     */
    public UtenteRegistrato(Ruoli ruoloUtente, Comune comuneApparteneza, String email, String password) {
        this.email = email;
        this.password = password;
        this.ruoloUtente = ruoloUtente;
        this.listaContenutiSalvati = new ArrayList<>();
        this.listaContenutiCondivisi = new ArrayList<>();
        this.listaInvitoContest = new ArrayList<>();
    }

    /**
     * Crea un nuovo utente registrato con valori di default.
     */
    public UtenteRegistrato() {
        this.listaContenutiSalvati = new ArrayList<>();
        this.listaContenutiCondivisi = new ArrayList<>();
        this.listaInvitoContest = new ArrayList<>();
    }

    /**
     * Restituisce l'email dell'utente.
     *
     * @return L'email dell'utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Restituisce la password dell'utente.
     *
     * @return La password dell'utente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Restituisce il ruolo dell'utente.
     *
     * @return Il ruolo dell'utente.
     */
    public Ruoli getRuoloUtente() {
        return ruoloUtente;
    }

    /**
     * Restituisce il comune di appartenenza dell'utente.
     *
     * @return Il comune di appartenenza dell'utente.
     */
    public Comune getComuneApparteneza() {
        return comuneApparteneza;
    }

    /**
     * Imposta il ruolo dell'utente.
     *
     * @param ruoloUtente Il nuovo ruolo dell'utente.
     */
    public void setRuoloUtente(Ruoli ruoloUtente) {
        this.ruoloUtente = ruoloUtente;
    }

    /**
     * Imposta il comune di appartenenza dell'utente.
     *
     * @param comuneApparteneza Il nuovo comune di appartenenza.
     */
    public void setComuneApparteneza(Comune comuneApparteneza) {
        this.comuneApparteneza = comuneApparteneza;
    }

    /**
     * Restituisce la lista dei contenuti salvati dall'utente.
     *
     * @return La lista dei contenuti salvati.
     */
    public List<Contenuto> getListaContenutiSalvati() {
        return listaContenutiSalvati;
    }

    /**
     * Aggiunge un contenuto alla lista dei contenuti salvati dall'utente.
     *
     * @param contenuto Il contenuto da aggiungere.
     * @return true se il contenuto è stato aggiunto correttamente, false se il contenuto era già presente.
     */
    public boolean aggiungiContenuto(Contenuto contenuto) {
        if (!listaContenutiSalvati.contains(contenuto)) {
            return listaContenutiSalvati.add(contenuto);
        }
        return false;
    }

    /**
     * Rimuove un contenuto dalla lista dei contenuti salvati.
     *
     * @param contenuto Il contenuto da rimuovere.
     * @return true se il contenuto è stato rimosso correttamente, false altrimenti.
     */
    public boolean rimuoviContenuto(Contenuto contenuto) {
        return listaContenutiSalvati.remove(contenuto);
    }

    /**
     * Restituisce la lista dei contenuti condivisi dall'utente.
     *
     * @return La lista dei contenuti condivisi.
     */
    public List<Contenuto> getListaContenutiCondivisi() {
        return listaContenutiCondivisi;
    }

    /**
     * Aggiunge un contenuto alla lista dei contenuti condivisi dall'utente.
     * Il contenuto può essere condiviso solo se l'utente è l'autore del contenuto.
     *
     * @param contenuto Il contenuto da aggiungere.
     * @return true se il contenuto è stato aggiunto correttamente, false altrimenti.
     */
    public boolean aggiungiContenutoCondivisi(Contenuto contenuto) {
        if (!listaContenutiCondivisi.contains(contenuto) && contenuto.getAutoreContenuto().equals(this)) {
            return listaContenutiCondivisi.add(contenuto);
        } else return false;
    }

    /**
     * Rimuove un contenuto dalla lista dei contenuti condivisi.
     *
     * @param contenuto Il contenuto da rimuovere.
     * @return true se il contenuto è stato rimosso correttamente, false altrimenti.
     */
    public boolean rimuoviContenutoCondivisi(Contenuto contenuto) {
        return listaContenutiCondivisi.remove(contenuto);
    }

    /**
     * Restituisce la lista degli inviti a contest ricevuti dall'utente.
     *
     * @return La lista degli inviti a contest.
     */
    public List<InvitoContest> getListaInvitoContest() {
        return listaInvitoContest;
    }

    /**
     * Aggiunge un invito a contest alla lista degli inviti ricevuti dall'utente.
     * L'invito può essere aggiunto solo se l'utente è il destinatario dell'invito.
     *
     * @param invitoContest L'invito a contest da aggiungere.
     * @return true se l'invito è stato aggiunto correttamente, false altrimenti.
     */
    public boolean aggiungiInvitoContest(InvitoContest invitoContest) {
        if (!listaInvitoContest.contains(invitoContest) && invitoContest.getUtenteInvitato().equals(this)) {
            return listaInvitoContest.add(invitoContest);
        } else return false;
    }

    /**
     * Rimuove un invito a contest dalla lista degli inviti ricevuti.
     *
     * @param invitoContest L'invito da rimuovere.
     * @return true se l'invito è stato rimosso correttamente, false altrimenti.
     */
    public boolean rimuoviInvitoContest(InvitoContest invitoContest) {
        return listaInvitoContest.remove(invitoContest);
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}