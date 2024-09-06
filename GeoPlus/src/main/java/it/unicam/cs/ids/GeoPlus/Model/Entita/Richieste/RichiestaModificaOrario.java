package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalTime;

@Entity
public class RichiestaModificaOrario extends Richiesta {

    @ManyToOne
    private Poi poi;
    private int giorno;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;

    public RichiestaModificaOrario(UtenteRegistrato autoreRichiesta, Comune comune, Poi poi, int giorno, LocalTime orarioApertura, LocalTime orarioChiusura) {
        super(autoreRichiesta, comune);
        this.giorno = giorno;
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
    }

    public RichiestaModificaOrario() {

    }

    @Override
    public Poi getEntitaRichiesta() {
        return poi;
    }

    public int getGiorno() {
        return giorno;
    }

    public LocalTime getOrarioApertura() {
        return orarioApertura;
    }

    public LocalTime getOrarioChiusura() {
        return orarioChiusura;
    }

}
