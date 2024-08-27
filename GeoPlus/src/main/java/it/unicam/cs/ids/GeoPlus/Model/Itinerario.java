package it.unicam.cs.ids.GeoPlus.Model;

import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;


import java.util.ArrayList;
import java.util.List;

/**
 * La classe Itinerario rappresenta un itinerario che contiene un elenco di punti di interesse (Poi)
 * associati a un comune e a un autore registrato.
 */
public class Itinerario extends EntitàRichiesta {

    // private Long IdItinerario;
    private String nomeItinerario;
    private String descrizioneItinerario;
    private final List<Poi> listaPoi;
    private final Comune comune;
    private final UtenteRegistrato autoreItinerario;

    /**
     * Costruttore per creare un oggetto Itinerario.
     *
     * @param nomeItinerario          Il nome dell'itinerario.
     * @param descrizioneItinerario   La descrizione dell'itinerario.
     * @param comune                  Il comune associato all'itinerario.
     * @param autoreItinerario        L'utente registrato che ha creato l'itinerario.
     */
    public Itinerario(String nomeItinerario, String descrizioneItinerario, Comune comune, UtenteRegistrato autoreItinerario) {
        this.nomeItinerario = nomeItinerario;
        this.descrizioneItinerario = descrizioneItinerario;
        this.comune = comune;
        this.autoreItinerario = autoreItinerario;
        this.listaPoi = new ArrayList<>();
    }

    // public Long getIdItinerario() {
    //     return IdItinerario;
    // }

    /**
     * Restituisce il nome dell'itinerario.
     *
     * @return Il nome dell'itinerario.
     */
    public String getNomeItinerario() {
        return nomeItinerario;
    }

    /**
     * Imposta un nuovo nome per l'itinerario.
     *
     * @param nomeItinerario Il nuovo nome dell'itinerario.
     */
    public void setNomeItinerario(String nomeItinerario) {
        this.nomeItinerario = nomeItinerario;
    }

    /**
     * Restituisce la descrizione dell'itinerario.
     *
     * @return La descrizione dell'itinerario.
     */
    public String getDescrizioneItinerario() {
        return descrizioneItinerario;
    }

    /**
     * Imposta una nuova descrizione per l'itinerario.
     *
     * @param descrizioneItinerario La nuova descrizione dell'itinerario.
     */
    private void setDescrizioneItinerario(String descrizioneItinerario) {
        this.descrizioneItinerario = descrizioneItinerario;
    }

    /**
     * Restituisce l'elenco dei punti di interesse associati all'itinerario.
     *
     * @return La lista dei punti di interesse.
     */
    public List<Poi> getListaPoi() {
        return listaPoi;
    }

    /**
     * Restituisce l'autore dell'itinerario.
     *
     * @return L'utente registrato che ha creato l'itinerario.
     */
    public UtenteRegistrato getAutoreItinerario() {
        return autoreItinerario;
    }

    /**
     * Aggiunge un punto di interesse all'itinerario se appartiene al comune dell'itinerario
     * e non è già presente nella lista.
     *
     * @param poi Il punto di interesse da aggiungere.
     */
    public void aggiungiPoi(Poi poi) {
        if (poi.getComune().equals(comune) && !listaPoi.contains(poi)) {
            listaPoi.add(poi);
        }
    }

    /**
     * Rimuove un punto di interesse dall'itinerario.
     *
     * @param poi Il punto di interesse da rimuovere.
     */
    public void rimuoviPoi(Poi poi) {
        listaPoi.remove(poi);
    }

    /**
     * Verifica se un punto di interesse è già presente nell'itinerario.
     *
     * @param poi Il punto di interesse da verificare.
     * @return true se il punto di interesse è presente, false altrimenti.
     */
    public boolean contienePoi(Poi poi) {
        return listaPoi.contains(poi);
    }

    /**
     * Restituisce il comune associato all'itinerario.
     *
     * @return Il comune dell'itinerario.
     */
    public Comune getComune() {
        return comune;
    }

    // @Override
    // public boolean equals(Object o) {
    //     // Implementazione del metodo equals
    // }

    // @Override
    // public int hashCode() {
    //     // Implementazione del metodo hashCode
    // }
}