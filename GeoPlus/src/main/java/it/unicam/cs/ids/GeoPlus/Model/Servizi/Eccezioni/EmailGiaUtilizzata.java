package it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni;
/**
 * Eccezione personalizzata lanciata quando si tenta di registrare un'email già utilizzata
 * da un altro profilo sulla piattaforma.
 * Estende {@link RuntimeException}.
 *
 * Questa eccezione viene tipicamente utilizzata per indicare che l'email fornita è già associata
 * a un profilo esistente e non può essere riutilizzata per una nuova registrazione.
 */
public class EmailGiaUtilizzata extends RuntimeException{

    /**
     * Costruttore predefinito che crea una nuova eccezione con il messaggio di errore
     * "Questa email è già associata ad un profilo sulla piattaforma".
     */
    public EmailGiaUtilizzata() {
        super("Questa email è già associata ad un profilo sulla piattaforma");
    }


}
