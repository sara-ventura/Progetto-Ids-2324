package it.unicam.cs.ids.GeoPlus.Model.Util;

import jakarta.persistence.Embeddable;

import java.time.LocalTime;


/**
 * La classe Orari rappresenta un insieme di orari di apertura e chiusura
 * per ogni giorno della settimana.
 */
@Embeddable
public class Orari {

    private LocalTime[] orariApertura;
    private LocalTime[] orariChiusura;


    private static final int GIORNI_SETTIMANA = 7;

    public Orari() {
        orariApertura = new LocalTime[GIORNI_SETTIMANA];
        orariChiusura = new LocalTime[GIORNI_SETTIMANA];
    }


    public void setOrario(int giorno, LocalTime apertura, LocalTime chiusura) {
        validaGiorno(giorno);
        validaInput(apertura, chiusura);
        orariApertura[giorno] = apertura;
        orariChiusura[giorno] = chiusura;
    }

    private void validaGiorno(int giorno) {
        if (giorno < 0 || giorno >= GIORNI_SETTIMANA) {
            throw new IllegalArgumentException("Indice del giorno non valido. Deve essere compreso tra 0 e 6.");
        }
    }


    private void validaInput(LocalTime apertura, LocalTime chiusura) {
        if (apertura != null && chiusura != null && apertura.isAfter(chiusura)) {
            throw new IllegalArgumentException("L'orario di apertura non può essere successivo all'orario di chiusura.");
        }
    }

    public LocalTime getApertura(int giorno) {
        validaGiorno(giorno);
        return orariApertura[giorno];
    }

    public LocalTime getChiusura(int giorno) {
        validaGiorno(giorno);
        return orariChiusura[giorno];
    }


    public String getOrariCompleti() {
        StringBuilder orariCompleti = new StringBuilder();
        String[] giorni = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica"};

        for (int i = 0; i < GIORNI_SETTIMANA; i++) {
            String apertura = orariApertura[i] != null ? orariApertura[i].toString() : "Chiuso";
            String chiusura = orariChiusura[i] != null ? orariChiusura[i].toString() : "Chiuso";
            orariCompleti.append(giorni[i])
                    .append(": Apertura: ")
                    .append(apertura)
                    .append(", Chiusura: ")
                    .append(chiusura)
                    .append("\n");
        }

        return orariCompleti.toString();
    }


}