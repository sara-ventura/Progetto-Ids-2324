package it.unicam.cs.ids.GeoPlus.Model.Util;

import jakarta.persistence.Embeddable;


@Embeddable
public class Coordinate {

    private double latitudine;
    private double longitudine;

    public Coordinate(double latitudine, double longitudine) {
        validaLatitudine(latitudine);
        validaLongitudine(longitudine);
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public Coordinate() {

    }

    public double getLatitudine() {
        return this.latitudine;
    }

    public double getLongitudine() {
        return this.longitudine;
    }

    private void validaLatitudine(double latitudine) {
        if (latitudine < -90.0 || latitudine > 90.0) {
            throw new IllegalArgumentException("La latitudine deve essere compresa tra -90 e 90 gradi.");
        }
    }

    private void validaLongitudine(double longitudine) {
        if (longitudine < -180.0 || longitudine > 180.0) {
            throw new IllegalArgumentException("La longitudine deve essere compresa tra -180 e 180 gradi.");
        }
    }

}