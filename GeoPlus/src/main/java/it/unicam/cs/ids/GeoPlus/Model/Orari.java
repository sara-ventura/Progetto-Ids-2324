package it.unicam.cs.ids.GeoPlus.Model;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * La classe Orari rappresenta un insieme di orari di apertura e chiusura
 * per ogni giorno della settimana.
 */
public class Orari {

    private final Map<String, LocalTime[]> orari;


    private static final Set<String> GIORNI_SETTIMANA = Set.of(
            "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica"
    );

    /**
     * Costruttore per creare un oggetto Orari.
     * Inizializza la mappa con i giorni della settimana, impostando gli orari a null.
     */
    public Orari() {
        orari = new HashMap<>();
        for (String giorno : GIORNI_SETTIMANA) {
            orari.put(giorno, null);
        }
    }

    /**
     * Imposta l'orario di apertura e chiusura per un giorno specifico.
     *
     * @param giorno   Il giorno della settimana (es. "Lunedì").
     * @param apertura  L'orario di apertura del giorno.
     * @param chiusura  L'orario di chiusura del giorno.
     * @throws IllegalArgumentException Se l'orario di apertura è successivo all'orario di chiusura
     *                                  o se il giorno fornito non è valido.
     */
    public void setOrario(String giorno, LocalTime apertura, LocalTime chiusura) {
        validaInput(giorno, apertura, chiusura); // Valida gli input
        if (!orari.containsKey(giorno)) {
            throw new IllegalArgumentException("Il giorno " + giorno + " non è un giorno valido della settimana.");
        }
        orari.put(giorno, new LocalTime[]{apertura, chiusura});
    }

    /**
     * Valida gli input per l'orario di apertura e chiusura.
     *
     * @param giorno   Il giorno da validare.
     * @param apertura  L'orario di apertura da validare.
     * @param chiusura  L'orario di chiusura da validare.
     * @throws IllegalArgumentException Se l'orario di apertura è successivo all'orario di chiusura.
     */
    private void validaInput(String giorno, LocalTime apertura, LocalTime chiusura) {
        if (apertura.isAfter(chiusura)) {
            throw new IllegalArgumentException("L'orario di apertura non può essere successivo all'orario di chiusura.");
        }
    }

    /**
     * Restituisce l'orario di apertura e chiusura per un giorno specifico.
     *
     * @param giorno Il giorno della settimana di cui si vogliono conoscere gli orari.
     * @return Un array di LocalTime contenente l'orario di apertura e chiusura,
     *         o null se non è stato impostato alcun orario.
     */
    public LocalTime[] getOrarioGiorno(String giorno) {
        return orari.getOrDefault(giorno, null);
    }

    /**
     * Restituisce la mappa degli orari per tutti i giorni della settimana.
     *
     * @return La mappa contenente gli orari di apertura e chiusura per ogni giorno.
     */
    public Map<String, LocalTime[]> getOrari() {
        return this.orari;
    }
}