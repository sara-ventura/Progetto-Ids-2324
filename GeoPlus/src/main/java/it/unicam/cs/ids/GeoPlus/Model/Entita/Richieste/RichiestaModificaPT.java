package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class RichiestaModificaPT extends Richiesta {

    @ManyToOne
    private PoiTemporaneo poiTemporaneo;
    private LocalDateTime dataApertura;
    private LocalDateTime dataChiusura;


    public RichiestaModificaPT(Account autoreRichiesta, Comune comune, PoiTemporaneo poiTemporaneo, LocalDateTime dataApertura, LocalDateTime dataChiusura) {
        super(autoreRichiesta, comune);
        this.poiTemporaneo = poiTemporaneo;
        this.dataApertura = dataApertura;
        this.dataChiusura = dataChiusura;
    }

    public RichiestaModificaPT() {

    }

    @Override
    public PoiTemporaneo getEntitaRichiesta() {
        return poiTemporaneo;
    }


    public LocalDateTime getDataChiusura() {
        return dataChiusura;
    }


    public LocalDateTime getDataApertura() {
        return dataApertura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RichiestaModificaPT richiestaModificaPT = (RichiestaModificaPT) o;
        return Objects.equals(poiTemporaneo, richiestaModificaPT.poiTemporaneo) && Objects.equals(dataApertura, richiestaModificaPT.dataApertura) && Objects.equals(dataChiusura, richiestaModificaPT.dataChiusura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), poiTemporaneo, dataApertura, dataChiusura);
    }
}
