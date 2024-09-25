package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteStandard;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.Objects;


@Entity
public class RichiestaModificaTestoC extends RichiestaModificaTesto {

  @OneToOne
  private Contenuto contenuto;


    public RichiestaModificaTestoC(UtenteStandard autoreRichiesta, Comune comune, Contenuto contenuto, String modificaTesto, TipoModificaTesto tipoModifica) {
        super(autoreRichiesta, comune, modificaTesto, tipoModifica);
      this.contenuto = contenuto;

    }

    public RichiestaModificaTestoC() {

    }

  @Override
  public Contenuto getEntitaRichiesta() {
    return contenuto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    RichiestaModificaTestoC richiestaModificaTestoC = (RichiestaModificaTestoC) o;
    return Objects.equals(contenuto, richiestaModificaTestoC.contenuto) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), contenuto);
  }
}
