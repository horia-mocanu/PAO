package Clase;

import java.util.List;
import java.util.ArrayList;

public class Mancare extends Produs{
    private int cantitate;
    private List<String> ingrediente;

    public Mancare(String nume, int pret, Restaurant restaurant, int cantitate, List<String> ingrediente) {
        super(nume, pret, restaurant);
        this.cantitate = cantitate;
        this.ingrediente = new ArrayList<>(ingrediente);
    }

    @Override
    public void Print() {
        super.Print();
        System.out.println("Mancarea cu gramajul " + cantitate + " coontine: ");
        for(String ingredient:ingrediente) {
            System.out.println(ingredient);
            System.out.printf(" ");
        }
        System.out.printf("\n");
    }
}
