package Clase;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private static int nr = 0;
    private int IDcomanda;
    private int IDrestaurant;
    private String username;
    private Sofer sofer;
    private List<Produs> produse;

    public Comanda(int IDcomanda, int IDrestaurant, String username, Sofer sofer, List<Produs> produse) {
        this.IDcomanda = IDcomanda;
        this.IDrestaurant = IDrestaurant;
        this.username = username;
        this.sofer = sofer;
        this.produse = produse;
    }

    public int TotalComanda() {
        int total = 0;
        for(Produs p:produse) {
            total += p.getPret();
        }
        return total;
    }
}
