package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaModificaTestoItinerario;
import org.springframework.stereotype.Component;

@Component
public class RichiestaModificaTestoItinerarioBuilder extends RIchiestaModificaTestoBaseBuilder {

    private Itinerario itinerario;

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    @Override
    public RichiestaModificaTestoItinerario build() {
        return new RichiestaModificaTestoItinerario(this.getAutore(),this.getComune(), itinerario, this.getModificaTesto(), this.getTipoModificaTesto());
    }
}
