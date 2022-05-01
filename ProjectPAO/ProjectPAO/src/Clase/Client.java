package Clase;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String numeClient;
    private String username;
    private String parola;
    private int numarComenzi;

    public Client(String numeClient, String username, String parola, int numarComenzi) {
        this.numeClient = numeClient;
        this.username = username;
        this.parola = parola;
        this.numarComenzi = numarComenzi;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public int getNumarComenzi() {
        return numarComenzi;
    }

    public void setNumarComenzi(int numarComenzi) {
        this.numarComenzi = numarComenzi;
    }

    public void AfiseazaClient(){
        System.out.println("Clientul " + numeClient + " cu username-ul " + username + "si "+ numarComenzi + " comenzi");
    }
}
