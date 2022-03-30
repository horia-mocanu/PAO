package Clase;

public abstract class Produs {
    protected String numeProdus;
    protected int pret;
    protected Restaurant restaurant;

    public Produs(String nume, int pret, Restaurant restaurant) {
        this.numeProdus = nume;
        this.pret = pret;
        this.restaurant = restaurant;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public int getPret() {
        return pret;
    }

    public void Print() {
        System.out.println("Produsul " + numeProdus + " costa  " + pret);
    }
}
