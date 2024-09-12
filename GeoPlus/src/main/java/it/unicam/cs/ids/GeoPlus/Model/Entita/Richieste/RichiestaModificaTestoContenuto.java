package it.unicam.cs.ids.GeoPlus.Model.Entita.Richieste;

import it.unicam.cs.ids.GeoPlus.Model.Entita.Comune;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Contenuto.Contenuto;
import it.unicam.cs.ids.GeoPlus.Model.Entita.Utenti.UtenteRegistrato;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.Objects;

/**
 * La classe RichiestaModificaTestoContenuto rappresenta una richiesta specifica
 * di modifica del testo associato a un oggetto di tipo Contenuto nel sistema GeoPlus.
 * Estende la classe astratta RichiestaModificaTesto
 * utilizzando il tipo specifico Contenuto per gestire le richieste di modifica del testo.
 * Questa classe è utilizzata per gestire le richieste che includono modifiche di testo 
 * su contenuti specifici, come descrizioni, articoli, o altre forme di testo che 
 * possono essere associate a un oggetto di tipo Contenuto.
 */

@Entity
public class RichiestaModificaTestoContenuto extends RichiestaModificaTesto {

  @OneToOne
  private Contenuto contenuto;


  /**
     * Costruttore che inizializza una nuova richiesta di modifica del testo per un contenuto con i dettagli specificati.
     *
     * @param autoreRichiesta l'utente registrato che ha effettuato la richiesta
     * @param comune          il comune associato alla richiesta
     * @param contenuto       il contenuto al quale si riferisce la modifica del testo
     * @param modificaTesto   il testo della modifica proposto
     */

    public RichiestaModificaTestoContenuto(UtenteRegistrato autoreRichiesta, Comune comune, Contenuto contenuto, String modificaTesto, TipoModificaTesto tipoModifica) {
        super(autoreRichiesta, comune, modificaTesto, tipoModifica);
      this.contenuto = contenuto;

    }

    public RichiestaModificaTestoContenuto() {

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

    RichiestaModificaTestoContenuto richiestaModificaTestoContenuto = (RichiestaModificaTestoContenuto) o;
    return Objects.equals(contenuto, richiestaModificaTestoContenuto.contenuto) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), contenuto);
  }
}
