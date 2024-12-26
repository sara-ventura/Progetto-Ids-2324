package it.unicam.cs.ids.GeoPlus.Model.Auth;

public class ExistingUserException extends Throwable {

    public ExistingUserException() {
        super("Ops, sembra che un account sia già associato alla tua mail!");
    }
}
