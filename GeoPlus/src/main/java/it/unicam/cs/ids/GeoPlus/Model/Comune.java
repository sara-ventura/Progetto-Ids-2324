package it.unicam.cs.ids.GeoPlus.Model;

import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * La classe Comune rappresenta un comune con un nome, una descrizione,
 * una lista di punti di interesse (POI) e una superficie definita da un poligono.
 */
public class Comune {

    private String nomeComune;
    private String descrizione;
    private List<Poi> listaPoi;
    private Coordinate[] superficie;

    /**
     * Costruttore per creare un oggetto Comune.
     *
     * @param nomeComune  Nome del comune.
     * @param descrizione Descrizione del comune.
     * @param superficie  Array di coordinate che definiscono la superficie del comune.
     * @throws IllegalArgumentException Se la superficie fornita non è valida.
     */
    public Comune(String nomeComune, String descrizione, Coordinate[] superficie) {
        if (validaSuperficie(superficie)) {
            this.nomeComune = nomeComune;
            this.descrizione = descrizione;
            this.listaPoi = new ArrayList<>();
            this.superficie = superficie;
        } else {
            throw new IllegalArgumentException("superficie non valida");
        }
    }

    /**
     * Costruttore vuoto per la classe Comune.
     */
    public Comune() {
        // Costruttore vuoto
    }

    /**
     * Verifica se la superficie fornita è valida.
     *
     * @param superficie Array di coordinate che definiscono la superficie.
     * @return true se la superficie è valida, false altrimenti.
     */
    private boolean validaSuperficie(Coordinate[] superficie) {
        if (superficie.length < 3) {
            return false;
        }
        Coordinate primoPunto = superficie[0];
        Coordinate ultimoPunto = superficie[superficie.length - 1];
        return primoPunto.getLatitudine() == ultimoPunto.getLatitudine() &&
                primoPunto.getLongitudine() == ultimoPunto.getLongitudine();
    }

    /**
     * Restituisce il nome del comune.
     *
     * @return Nome del comune.
     */
    public String getNomeComune() {
        return this.nomeComune;
    }

    /**
     * Restituisce la descrizione del comune.
     *
     * @return Descrizione del comune.
     */
    public String getDescrizione() {
        return this.descrizione;
    }

    /**
     * Restituisce l'array di coordinate che definiscono la superficie del comune.
     *
     * @return Array di coordinate della superficie.
     */
    public Coordinate[] getSuperficie() {
        return superficie;
    }

    /**
     * Restituisce la lista dei punti di interesse associati al comune.
     *
     * @return Lista dei punti di interesse.
     */
    public List<Poi> getPoiAssociati() {
        return this.listaPoi;
    }

    /**
     * Verifica se una coordinata è contenuta all'interno della superficie del comune.
     *
     * @param coordinata La coordinata da verificare.
     * @return true se la coordinata è all'interno della superficie, false altrimenti.
     */
    public boolean contieneCoordinata(Coordinate coordinata) {
        int n = superficie.length;
        boolean inside = false;
        for (int i = 0, j = n - 1; i < n; j = i++) {
            double xi = superficie[i].getLatitudine(), yi = superficie[i].getLongitudine();
            double xj = superficie[j].getLatitudine(), yj = superficie[j].getLongitudine();
            boolean intersect = ((yi > coordinata.getLongitudine()) != (yj > coordinata.getLongitudine())) &&
                    (coordinata.getLatitudine() < (xj - xi) * (coordinata.getLongitudine() - yi) / (yj - yi) + xi);
            if (intersect) {
                inside = !inside;
            }
        }
        return inside;
    }

    /**
     * Aggiunge un punto di interesse (POI) alla lista dei POI associati al comune.
     *
     * @param poi Il punto di interesse da aggiungere.
     * @return true se il POI è stato aggiunto con successo, false altrimenti.
     */
    public boolean aggiungiPoi(Poi poi) {
        if (poi.getComune().equals(this) && !listaPoi.contains(poi)) {
            listaPoi.add(poi);
            return true;
        }
        return false;
    }

    /**
     * Rimuove un punto di interesse (POI) dalla lista dei POI associati al comune.
     *
     * @param poi Il punto di interesse da rimuovere.
     * @return true se il POI è stato rimosso con successo, false altrimenti.
     */
    public boolean rimuoviPoi(Poi poi) {
        return listaPoi.remove(poi);
    }


    /*
    @Override
    public boolean equals(Object o) {
        // Implementazione da aggiungere se necessario
    }

    @Override
    public int hashCode() {
        // Implementazione da aggiungere se necessario
    }
    */
}