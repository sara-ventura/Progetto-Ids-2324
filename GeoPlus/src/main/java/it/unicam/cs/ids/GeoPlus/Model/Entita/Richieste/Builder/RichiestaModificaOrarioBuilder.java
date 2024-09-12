package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaModificaOrario;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class RichiestaModificaOrarioBuilder extends RichiestaBaseBuilder {

    private Poi poi;
    private int giorno;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;

    public void setPoi(Poi poi) {
        this.poi = poi;
    }
    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public void setOrarioApertura(LocalTime orarioApertura) {
        this.orarioApertura = orarioApertura;
    }
    public void setOrarioChiusura(LocalTime orarioChiusura) {
        this.orarioChiusura = orarioChiusura;
    }


    @Override
    public RichiestaModificaOrario build() {
        return new RichiestaModificaOrario(this.getAutore(), this.getComune(), poi, giorno, orarioApertura, orarioChiusura);
    }
}
