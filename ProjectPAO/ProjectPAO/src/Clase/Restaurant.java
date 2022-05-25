package Clase;

import java.util.List;
import java.util.ArrayList;

public class Restaurant {
    private static int id = 0;
    private String nume;
    private String adresa;
    private int capacitate;
    private List<Produs> meniu;
    private List<Client> clienti;
    private double rating;
    private int IDrestaurant;


    public Restaurant(String nume, String adresa, int capacitate, List<Produs> meniu, List<Client> clienti) {
        this.nume = nume;
        this.adresa = adresa;
        this.capacitate = capacitate;
        this.meniu = new ArrayList<>();
        this.clienti = new ArrayList<>();
        this.rating = 5.0;
        this.IDrestaurant = id++;
    }

    public String getNume() {
        return nume;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public double getRating() {
        return rating;
    }

    public int getIDrestaurant() {
        return IDrestaurant;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public double ModificareRating() {
        if(capacitate > 500) {
            rating =- (capacitate - 500)/100 * 0.2;
        }
        return rating;
    }

    public void AdaugaMancare(String nume, int pret, int cantitate, List<String> ingrediente) {
        Produs produs;
        produs = new Mancare(nume, pret, this, cantitate, ingrediente);
        meniu.add(produs);
    }

    public void AdaugaBautura(String nume, int pret, int marime) {
        Produs produs;
        produs = new Bautura(nume, pret, this, marime);
        meniu.add(produs);
    }

    public void AfisareMeniu() {
        System.out.println("Meniul contine: ");
        for (Produs produs:meniu) {
            produs.Print();
        }
    }

    public Produs CumparaProdus(String nume) {
        for(Produs produs:meniu) {
            if(produs.getNumeProdus().equals(nume)) {
                return produs;
            }
        }
        return null;
    }

    public void AdaugaClient(String numeClient, String username, String parola, int numarComenzi) {
        Client client;
        client = new Client(numeClient, username, parola, numarComenzi);
        clienti.add(client);
    }

    public void AfiseazaClienti(){
        for (Client client : clienti){
            client.AfiseazaClient();
        }
    }

    public void AfisareRestaurant() {
        System.out.println("Restaurantul " + nume + " are adresa " + adresa);
    }
}
