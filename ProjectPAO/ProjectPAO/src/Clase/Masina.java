package Clase;

public class Masina {
    private String marca;
    private String culoare;
    private String numarMasina;

    public Masina(String marca, String culaore, String numarMasina) {
        this.marca = marca;
        this.culoare = culaore;
        this.numarMasina = numarMasina;
    }

    public String getMarca() {
        return marca;
    }

    public String getNumarMasina() {
        return numarMasina;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNumarMasina(String numarMasina) {
        this.numarMasina = numarMasina;
    }
    public void AfiseazaMasina(){
        System.out.println("Masina " + marca + " " + culoare + " cu numarul " + numarMasina);
    }
}
