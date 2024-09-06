package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.factory;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaModificaPeriodoTempo;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RichiestaModificaPeriodoDiTempoFactoryImpl implements RichiestaModificaPeriodoDiTempoFactory {


    @Override
    public RichiestaModificaPeriodoTempo creaRichiesta(UtenteRegistrato autoreRichiesta, Comune comune, PoiTemporaneo poiTemporaneo, LocalDateTime dataApertura, LocalDateTime dataChiusura) {
        return new RichiestaModificaPeriodoTempo(autoreRichiesta, comune, poiTemporaneo, dataApertura, dataChiusura );
    }
}
