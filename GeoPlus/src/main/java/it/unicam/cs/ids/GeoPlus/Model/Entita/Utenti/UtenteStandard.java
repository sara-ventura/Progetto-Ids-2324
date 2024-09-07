package it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contest.InvitoContest;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Ruoli.Ruoli;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Rappresenta un utente standard nel sistema GeoPlus.
 * Un utente standard ha un ruolo, un comune di appartenenza, e può salvare o condividere contenuti,
 * nonché ricevere inviti a contest.
 */
@Entity
public class UtenteStandard  extends UtenteRegistrato{

    /**
     * Lista di inviti ai contest ricevuti dall'utente.
     */
    @OneToMany
    private List<InvitoContest> listaInvitiContest;


    /**
     * Lista dei contenuti salvati dall'utente.
     */
    @OneToMany
    private List<Contenuto> listaContenutiSalvati;

    /**
     * Costruttore della classe UtenteStandard.
     *
     * @param ruoloUtente Il ruolo assegnato all'utente.
     * @param credenziali Le credenziali associate all'utente.
     */
    public UtenteStandard(Ruoli ruoloUtente, Long credenziali) {
        super(ruoloUtente, credenziali);
        this.listaInvitiContest = new ArrayList<>();
        this.listaContenutiSalvati = new ArrayList<>();
    }

    public UtenteStandard() {

    }

    /**
     * Restituisce la lista degli inviti ai contest ricevuti dall'utente.
     *
     * @return La lista di {@link InvitoContest}.
     */
    public List<InvitoContest> getListaInvitiContest() {
        return listaInvitiContest;
    }

    /**
     * Restituisce la lista dei contenuti salvati dall'utente.
     *
     * @return La lista di {@link Contenuto}.
     */
    public List<InvitoContest> getListaInvitoContest() {
        return listaInvitiContest;
    }

    /**
     * Aggiunge un invito contest alla lista degli inviti, se non già presente.
     *
     * @param invitoContest L'invito contest da aggiungere.
     * @return {@code true} se l'invito è stato aggiunto correttamente,
     *         {@code false} se l'invito era già presente.
     */
    public boolean aggiungiInvitoContest(InvitoContest invitoContest) {
        if (!listaInvitiContest.contains(invitoContest)) {
            return listaInvitiContest.add(invitoContest);
        } else return false;
    }

    /**
     * Rimuove un invito contest dalla lista degli inviti.
     *
     * @param invitoContest L'invito contest da rimuovere.
     * @return {@code true} se l'invito è stato rimosso correttamente,
     *         {@code false} se l'invito non era presente.
     */
    public boolean rimuoviInvitoContest(InvitoContest invitoContest) {
        return listaInvitiContest.remove(invitoContest);
    }

    /**
     * Aggiunge un contenuto alla lista dei contenuti salvati, se non già presente.
     *
     * @param contenuto Il contenuto da aggiungere.
     * @return {@code true} se il contenuto è stato aggiunto correttamente,
     *         {@code false} se il contenuto era già presente.
     */
    public boolean aggiungiContenutoSalvato(Contenuto contenuto) {
        if (!listaContenutiSalvati.contains(contenuto)) {
            return listaContenutiSalvati.add(contenuto);
        } else return false;
    }

    /**
     * Rimuove un contenuto dalla lista dei contenuti salvati.
     *
     * @param contenuto Il contenuto da rimuovere.
     * @return {@code true} se il contenuto è stato rimosso correttamente,
     *         {@code false} se il contenuto non era presente.
     */
    public boolean rimuoviContenutoSalvato(Contenuto contenuto) {
        return listaContenutiSalvati.remove(contenuto);
    }


    @Override
    public int hashCode() {

        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
