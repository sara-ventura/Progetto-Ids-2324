package it.unicam.cs.ids.GeoPlus.Model.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Utenti.UtenteRegistrato;

import java.time.LocalTime;

public class RichiestaModificaOrario extends Richiesta<Poi> {

    private String giorno;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;

    public RichiestaModificaOrario(UtenteRegistrato autoreRichiesta, Comune comune, Poi poi, String giorno, LocalTime orarioApertura, LocalTime orarioChiusura) {
        super(autoreRichiesta, comune, poi);
        this.giorno = giorno;
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
    }

    public RichiestaModificaOrario() {

    }

    public String getGiorno() {
        return giorno;
    }

    public LocalTime getOrarioApertura() {
        return orarioApertura;
    }

    public LocalTime getOrarioChiusura() {
        return orarioChiusura;
    }

}