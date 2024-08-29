package it.unicam.cs.ids.GeoPlus.Model.Pois;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Coordinate;
import it.unicam.cs.ids.GeoPlus.Model.EntitaRichiesta;
import it.unicam.cs.ids.GeoPlus.Model.Orari;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * La classe Poi rappresenta un punto di interesse (POI) in un comune.
 * Contiene informazioni sul nome, descrizione, posizione, orari, contenuti associati e l'autore.
 */
public class Poi extends EntitaRichiesta {

    //   private Long idPoi;
    private String nomePoi;
    private String descrizionePoi;
    private Coordinate posizionePoi;
    private Orari orariPoi;
    private Comune comunePoi;
    private List<Contenuto> contenutiAssociati;


    /**
     * Costruttore per creare un oggetto Poi.
     *
     * @param nomePoi        Il nome del punto di interesse.
     * @param descrizionePoi La descrizione del punto di interesse.
     * @param posizionePoi   Le coordinate del punto di interesse.
     * @param comunePoi      Il comune a cui appartiene il punto di interesse.
     * @param autorePoi      L'utente registrato che ha creato il punto di interesse.
     * @throws IllegalArgumentException se la posizione non è valida nel comune.
     */
    public Poi(String nomePoi, String descrizionePoi, Coordinate posizionePoi, Comune comunePoi, UtenteRegistrato autorePoi) {
        if (comunePoi.contieneCoordinata(posizionePoi)) {
            this.nomePoi = nomePoi;
            this.descrizionePoi = descrizionePoi;
            this.posizionePoi = posizionePoi;
            this.comunePoi = comunePoi;
            this.orariPoi = new Orari();
            this.contenutiAssociati = new ArrayList<>();
        } else {
            throw new IllegalArgumentException("Posizione non valida");
        }
    }


    public Poi() {
    }

    /*
     * Restituisce l'ID del punto di interesse.
     *
     * @return L'ID del punto di interesse.
     */
//    public Long getIdContenuto() {
//        return idPoi;
//    }

    /**
     * Imposta il nome del punto di interesse.
     *
     * @param nomePoi Il nuovo nome del punto di interesse.
     */
    public void setNomePoi(String nomePoi) {
        this.nomePoi = nomePoi;
    }

    /**
     * Restituisce il nome del punto di interesse.
     *
     * @return Il nome del punto di interesse.
     */
    public String getNomePoi() {
        return this.nomePoi;
    }

    /**
     * Imposta la descrizione del punto di interesse.
     *
     * @param descrizionePoi La nuova descrizione del punto di interesse.
     */
    public void setDescrizionePoi(String descrizionePoi) {
        this.descrizionePoi = descrizionePoi;
    }

    /**
     * Restituisce la descrizione del punto di interesse.
     *
     * @return La descrizione del punto di interesse.
     */
    public String getDescrizionePoi() {
        return this.descrizionePoi;
    }

    /**
     * Restituisce la posizione del punto di interesse.
     *
     * @return Le coordinate del punto di interesse.
     */
    public Coordinate getPosizionePoi() {
        return this.posizionePoi;
    }

    @Override
    public Comune getComune() {
        return this.comunePoi; // Ritorna il comune a cui appartiene il punto di interesse
    }


    /**
     * Imposta gli orari di apertura per un giorno specifico.
     *
     * @param giorno         Il giorno per cui impostare gli orari.
     * @param orarioApertura L'orario di apertura.
     * @param orarioChiusura L'orario di chiusura.
     */
    public void setOrarioPoi(String giorno, LocalTime orarioApertura, LocalTime orarioChiusura) {
        this.orariPoi.setOrario(giorno, orarioApertura, orarioChiusura);
    }

    /**
     * Restituisce gli orari di apertura per un giorno specifico.
     *
     * @param giorno Il giorno di cui si vogliono ottenere gli orari.
     * @return Un array di LocalTime contenente l'orario di apertura e di chiusura.
     */
    public LocalTime[] getOrarioGiorno(String giorno) {
        return this.orariPoi.getOrarioGiorno(giorno);
    }

    /**
     * Restituisce tutti gli orari di apertura del punto di interesse.
     *
     * @return Una mappa contenente gli orari di apertura per ogni giorno.
     */
    public Map<String, LocalTime[]> getOrariPoi() {
        return this.orariPoi.getOrari();
    }

    /**
     * Restituisce la lista dei contenuti associati al punto di interesse.
     *
     * @return La lista dei contenuti associati.
     */
    public List<Contenuto> getListaConetnutiAssociati() {
        return this.contenutiAssociati;
    }

    /**
     * Aggiunge un contenuto alla lista dei contenuti associati al punto di interesse.
     *
     * @param contenuto Il contenuto da aggiungere.
     * @return true se il contenuto è stato aggiunto, false altrimenti.
     */
    public boolean aggiungiContenuto(Contenuto contenuto) {
        return this.contenutiAssociati.add(contenuto);
    }

    /**
     * Rimuove un contenuto dalla lista dei contenuti associati al punto di interesse.
     *
     * @param contenuto Il contenuto da rimuovere.
     * @return true se il contenuto è stato rimosso, false altrimenti.
     */
    public boolean rimuoviContenuto(Contenuto contenuto) {
        return this.contenutiAssociati.remove(contenuto);
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
