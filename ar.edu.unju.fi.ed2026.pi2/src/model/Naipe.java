package model;

/** Representa una carta con palo, valor y estado de disponibilidad. */
public class Naipe {
    private final String palo;
    private final int valor;
    private boolean isDisponible;

    /**
     * Crea una carta disponible con el palo y valor indicados.
     *
     * @param palo  palo de la carta
     * @param valor valor de la carta
     */
    public Naipe(String palo, int valor) {
        this.palo = palo;
        this.valor = valor;
        this.isDisponible = true;
    }

    /**
     * Obtiene el palo de la carta.
     *
     * @return palo de la carta
     */
    public String getPalo() {
        return palo;
    }

    /**
     * Obtiene el valor de la carta.
     *
     * @return valor de la carta
     */
    public int getValor() {
        return valor;
    }

    /**
     * Indica si la carta está disponible para ser utilizada.
     *
     * @return {@code true} si la carta está disponible; en caso contrario,
     *         {@code false}
     */
    public boolean isDisponible() {
        return isDisponible;
    }

    /**
     * Modifica la disponibilidad de la carta.
     *
     * @param isDisponible {@code true} para marcar la carta como disponible
     */
    public void setDisponible(boolean isDisponible) {
        this.isDisponible = isDisponible;
    }

    /**
     * Devuelve una representación textual de la carta.
     *
     * @return representación textual de la carta
     */
    @Override
    public String toString() {
        return "Naipe [palo=" + palo + ", valor=" + valor + ", isDisponible=" + isDisponible + "]";
    }
}
