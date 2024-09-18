package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaModificaTestoIt;
import org.springframework.stereotype.Component;

@Component
public class RichiestaModificaTestoItinerarioBuilder extends RIchiestaModificaTestoBaseBuilder {

    private Itinerario itinerario;

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    @Override
    public RichiestaModificaTestoIt build() {
        return new RichiestaModificaTestoIt(this.getAutore(),this.getComune(), itinerario, this.getModificaTesto(), this.getTipoModificaTesto());
    }
}
