package model;

public class Naipe {
    private String palo;
    private int valor;
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
    public boolean getIsDisponible() {
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
     * Modifica el palo de la carta.
     *
     * @param palo nuevo palo de la carta
     */
    public void setPalo(String palo) {
        this.palo = palo;
    }

    /**
     * Modifica el valor de la carta.
     *
     * @param valor nuevo valor de la carta
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    /**
     * Devuelve una representación textual de la carta.
     *
     * @return representación textual de la carta
     */
    public String toString() {
        return "Naipe [palo=" + palo + ", valor=" + valor + ", isDisponible=" + isDisponible + "]";
    }
}
