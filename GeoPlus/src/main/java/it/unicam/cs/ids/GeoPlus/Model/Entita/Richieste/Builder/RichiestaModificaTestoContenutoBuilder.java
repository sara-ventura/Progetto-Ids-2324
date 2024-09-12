package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaModificaTestoContenuto;
import org.springframework.stereotype.Component;

@Component
public class RichiestaModificaTestoContenutoBuilder extends RIchiestaModificaTestoBaseBuilder{

    private Contenuto contenuto;

    public void setContenuto(Contenuto contenuto) {
        this.contenuto = contenuto;
    }

    @Override
    public RichiestaModificaTestoContenuto build() {
        return new RichiestaModificaTestoContenuto(this.getAutore(), this.getComune(), contenuto, this.getModificaTesto(), this.getTipoModificaTesto());
    }
}
