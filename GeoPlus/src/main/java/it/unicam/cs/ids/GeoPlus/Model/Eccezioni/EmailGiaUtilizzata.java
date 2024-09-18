package it.unicam.cs.ids.GeoPlus.Model.Eccezioni;

public class EmailGiaUtilizzata extends RuntimeException {
  public EmailGiaUtilizzata(String message) {
    super("Questa email è già associata ad un profilo sulla piattaforma");
  }
}
