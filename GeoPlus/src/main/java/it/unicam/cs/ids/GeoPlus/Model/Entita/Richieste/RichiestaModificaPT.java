package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Pois.PoiTemporaneo;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * La classe RichiestaModificaPT rappresenta una richiesta
 * per modificare il periodo di apertura e chiusura associato a un oggetto
 * di tipo PoiTemporaneo nel sistema GeoPlus.
 * Estende la classe generica Richiesta.
 * Questa classe è utilizzata per gestire modifiche temporali di eventi o punti
 * di interesse che hanno un periodo di validità definito.
 */

@Entity
public class RichiestaModificaPT extends Richiesta {

    @ManyToOne
    private PoiTemporaneo poiTemporaneo;
    private LocalDateTime dataApertura;
    private LocalDateTime dataChiusura;

    /**
     * Costruttore che inizializza una nuova richiesta di modifica del periodo
     * di tempo per un PoiTemporaneo con i dettagli specificati.
     *
     * @param autoreRichiesta l'utente registrato che ha effettuato la richiesta
     * @param comune          il comune associato alla richiesta
     * @param poiTemporaneo   il punto di interesse temporaneo al quale si riferisce la modifica del periodo di tempo
     * @param dataApertura    la data e l'ora di apertura proposte
     * @param dataChiusura    la data e l'ora di chiusura proposte
     */

    public RichiestaModificaPT(UtenteStandard autoreRichiesta, Comune comune, PoiTemporaneo poiTemporaneo, LocalDateTime dataApertura, LocalDateTime dataChiusura) {
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

    /**
     * Restituisce la data e l'ora di chiusura proposte per il periodo di validità.
     *
     * @return la data e l'ora di chiusura proposte
     */


    public LocalDateTime getDataChiusura() {
        return dataChiusura;
    }

    /**
     * Restituisce la data e l'ora di apertura proposte per il periodo di validità.
     *
     * @return la data e l'ora di apertura proposte
     */

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
