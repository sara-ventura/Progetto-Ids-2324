package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.Poi;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaCaricamentoPoi;
import org.springframework.stereotype.Component;

@Component
public class RichiestaCaricamentoPoiBuilder extends RichiestaBaseBuilder{

    private Poi poi;

    public void setPoi(Poi poi) {
        this.poi = poi;
    }

    @Override
    public RichiestaCaricamentoPoi build() {
        return new RichiestaCaricamentoPoi(this.getAutore(),this.getComune(), this.poi);
    }
}
