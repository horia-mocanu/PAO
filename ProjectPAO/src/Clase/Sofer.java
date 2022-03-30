package Clase;

public class Sofer {
    private String numeSofer;
    private String dateSofer;
    private double salariu;

    private Masina masina;
    private int experienta;

    public Sofer(String numeSofer, String dateSofer, double salariu, Masina masina, int experienta) {
        this.numeSofer = numeSofer;
        this.dateSofer = dateSofer;
        this.salariu = salariu;
        this.masina = masina;
        this.experienta = experienta;
    }

    public String getNumeSofer() {
        return numeSofer;
    }

    public double getSalariu() {
        return salariu;
    }

    public int getExperienta() {
        return experienta;
    }

    public Masina getMasina() {
        return masina;
    }

    public double MaresteSalariu () {
        if(experienta > 3) {
            salariu += (experienta - 3) * 200;
        }
        return salariu;
    }

    public void AfiseazaSofer(){
        System.out.println("Soferul " + numeSofer + "are salariul " + salariu + " si " + experienta + " ani de experienta");
    }

}
