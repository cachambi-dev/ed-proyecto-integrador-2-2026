package model;

public class Naipe {
    private String palo;
    private int valor;
    private boolean isDisponible;

    public Naipe(String palo, int valor) {
        this.palo = palo;
        this.valor = valor;
        this.isDisponible = true;
    }

    public String getPalo() {
        return palo;
    }
    
    public int getValor() {
        return valor;
    }

    public boolean getIsDisponible() {
        return isDisponible;
    }

    public void setDisponible(boolean isDisponible) {
        this.isDisponible = isDisponible;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Naipe [palo=" + palo + ", valor=" + valor + ", isDisponible=" + isDisponible + "]";
    }
}
