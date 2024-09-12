package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;


import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaModificaPeriodoTempo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RichiestaModificaPeriodoTempoBuilder extends RichiestaBaseBuilder {

    private PoiTemporaneo poiTemporaneo;
    private LocalDateTime dataApertura;
    private LocalDateTime dataChiusura;

    public void setPoiTemporaneo(PoiTemporaneo poiTemporaneo) {
        this.poiTemporaneo = poiTemporaneo;
    }
    public void setDataApertura(LocalDateTime dataApertura) {
        this.dataApertura = dataApertura;
    }
    public void setDataChiusura(LocalDateTime dataChiusura) {
        this.dataChiusura = dataChiusura;
    }

    @Override
    public RichiestaModificaPeriodoTempo build() {
        return new RichiestaModificaPeriodoTempo(this.getAutore(), this.getComune(), poiTemporaneo, dataApertura, dataChiusura );
    }
}
