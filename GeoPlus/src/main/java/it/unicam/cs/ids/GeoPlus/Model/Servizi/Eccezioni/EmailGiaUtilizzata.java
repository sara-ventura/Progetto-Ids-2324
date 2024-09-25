package it.unicam.cs.ids.GeoPlus.Model.Servizi.Eccezioni;

public class EmailGiaUtilizzata extends RuntimeException{

    public EmailGiaUtilizzata() {
        super("Questa email è già associata ad un profilo sulla piattaforma");
    }


}
