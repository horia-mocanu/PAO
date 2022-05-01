package Clase;

public class Bautura extends Produs{
    int marime;

    public Bautura(String nume, int pret, Restaurant restaurant, int marime) {
        super(nume, pret, restaurant);
        this.marime = marime;
    }

    @Override
    public void Print() {
        super.Print();
        System.out.println("Bautura are " + marime + " L");
    }

}
