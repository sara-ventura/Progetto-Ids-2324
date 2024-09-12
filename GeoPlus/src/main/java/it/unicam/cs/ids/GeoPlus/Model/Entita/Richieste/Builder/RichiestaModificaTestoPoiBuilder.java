package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaModificaTestoPoi;
import org.springframework.stereotype.Component;

@Component
public class RichiestaModificaTestoPoiBuilder extends RIchiestaModificaTestoBaseBuilder{

    private Poi poi;

    public void setPoi(Poi poi) {
        this.poi = poi;
    }

    @Override
    public RichiestaModificaTestoPoi build() {
        return new RichiestaModificaTestoPoi(this.getAutore(), this.getComune(), poi, this.getModificaTesto(), this.getTipoModificaTesto());
    }
}
