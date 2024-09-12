package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;


import it.unicam.cs.ids.GeoPlus.Model.Entita.Itinerario;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaCaricamentoItinerario;
import org.springframework.stereotype.Component;

@Component
public class RichiestaCaricamentoItinerarioBuilder extends RichiestaBaseBuilder {
    private Itinerario itinerario;

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }


    @Override
    public RichiestaCaricamentoItinerario build() {
        return new RichiestaCaricamentoItinerario(this.getAutore(), this.getComune(), this.itinerario);
    }
}
