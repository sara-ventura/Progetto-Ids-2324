package it.unicam.cs.ids.GeoPlus.Model;

/**
 * La classe Coordinate rappresenta una coppia di coordinate geografiche,
 * specificando la latitudine e la longitudine.
 */
public class Coordinate {

    private final double latitudine;
    private final double longitudine;

    /**
     * Costruttore per creare un oggetto Coordinate.
     *
     * @param latitudine  La latitudine del punto.
     * @param longitudine La longitudine del punto.
     * @throws IllegalArgumentException Se la latitudine o la longitudine non sono valide.
     */
    public Coordinate(double latitudine, double longitudine) {
        validaLatitudine(latitudine);
        validaLongitudine(longitudine);
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    /**
     * Restituisce la latitudine del punto.
     *
     * @return La latitudine del punto.
     */
    public double getLatitudine() {
        return this.latitudine;
    }

    /**
     * Restituisce la longitudine del punto.
     *
     * @return La longitudine del punto.
     */
    public double getLongitudine() {
        return this.longitudine;
    }

    /**
     * Verifica se la latitudine fornita è valida.
     *
     * @param latitudine La latitudine da verificare.
     * @throws IllegalArgumentException Se la latitudine non è compresa tra -90 e 90 gradi.
     */
    private void validaLatitudine(double latitudine) {
        if (latitudine < -90.0 || latitudine > 90.0) {
            throw new IllegalArgumentException("La latitudine deve essere compresa tra -90 e 90 gradi.");
        }
    }

    /**
     * Verifica se la longitudine fornita è valida.
     *
     * @param longitudine La longitudine da verificare.
     * @throws IllegalArgumentException Se la longitudine non è compresa tra -180 e 180 gradi.
     */
    private void validaLongitudine(double longitudine) {
        if (longitudine < -180.0 || longitudine > 180.0) {
            throw new IllegalArgumentException("La longitudine deve essere compresa tra -180 e 180 gradi.");
        }
    }

}