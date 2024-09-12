package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.Builder;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste.RichiestaCaricamentoContenuto;
import org.springframework.stereotype.Component;

@Component
public class RichiestaCaricamentoContenutoBuilder extends RichiestaBaseBuilder{
    private Contenuto contenuto;

    @Override
    public RichiestaCaricamentoContenuto build() {
        return new RichiestaCaricamentoContenuto(this.getAutore(), this.getComune(), this.contenuto);
    }

    public void setContenuto(Contenuto contenuto) {
        this.contenuto = contenuto;
    }
}
