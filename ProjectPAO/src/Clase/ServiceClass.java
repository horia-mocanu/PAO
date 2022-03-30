package Clase;

import java.util.*;

public class ServiceClass {
    private static ServiceClass instance;
    private List<Restaurant> restaurante;
    private List<Comanda> comenzi;
    private List<Sofer> soferi;
    final private Scanner scanner;
    private ServiceClass() {
        restaurante = new ArrayList<>();
        comenzi = new ArrayList<>();
        soferi = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static ServiceClass getInstance(){
        if (instance == null)
            instance = new ServiceClass();
        return instance;
    }

    private void AdaugaDate() {
        Restaurant restaurant1 = new Restaurant("Dristor", "Bv Dristor nr1", 20, new ArrayList<>(),new ArrayList<>());
        restaurant1.AdaugaMancare("shaorma", 25, 400,  Arrays.asList("cartofi", "carne", "lipie"));
        restaurante.add(restaurant1);

        Masina masina1 = new Masina("Audi", "gri", "B22JMK");
        Sofer sofer1 = new Sofer("Horia", "21 de ani", 4000, masina1, 2);
        soferi.add(sofer1);
    }

    private void afiseazaRestaurante(){
        for (Restaurant restaurant : restaurante){
            restaurant.AfisareRestaurant();
        }
    }

    private Restaurant gasesteRestaurant(String nume){
        for (Restaurant restaurant : restaurante){
            if (restaurant.getNume().equals(nume)){
                return restaurant;
            }
        }
        return null;
    }

    private Sofer gasesteSofer(String nume){
        for (Sofer sofer : soferi){
            if (sofer.getNumeSofer().equals(nume)){
                return sofer;
            }
        }
        return null;
    }


    public void Run() {
        AdaugaDate();
        //5AfisareRestaurant()
        //3AfisareMeniu() la un restaurant
        //2AdaugaBautura() la un restaurant
        //1AdaugaMancare() la un restaurant
        //9 cauta produs in meniu

        //4AdaugaClient() la un restaurant
        //8AfisareClient

        //6afisare soferi
        //7MaresteSalariu() sofer
        //10 afisare masini
        while(true){
        System.out.println("Alegeti optiunea dorita:");
        System.out.println("1 pentru a vedea toate restaurantele;");
        System.out.println("2 pentru a vedea meniul unui restaurant;");
        System.out.println("3 pentru a adauga un fel de mancare in meniul unui restaurant;;");
        System.out.println("4 pentru a adauga o bautura in meniul unui restaurant;");
        System.out.println("5 pentru a cauta un produs in meniu");
        System.out.println("6 pentru a afisa toti clientii unui restaurant");
        System.out.println("7 pentru a adauga un client la un restaurant");
        System.out.println("8 pentru a afisa toti soferii");
        System.out.println("9 pentru a mari salariul unui sofer");
        System.out.println("10 pentru a afisa toate masinile.");
        System.out.println("exit pentru a iesi\n");

        String option = scanner.nextLine();
        switch(option){
            case("exit"):{
                return;
            }
            case("1"):{
                afiseazaRestaurante();
                break;
            }
            case("2"):{
                System.out.println("Introdu numele restaurantului");
                String numeRest = scanner.nextLine();
                Restaurant restaurant = gasesteRestaurant(numeRest);
                if(restaurant != null){
                    restaurant.AfisareMeniu();
                }
                break;
            }
            case("3"):{
                System.out.println("Introdu numele restaurantului");
                String numeRest = scanner.nextLine();
                Restaurant restaurant = gasesteRestaurant(numeRest);

                System.out.println("Introduceti numele produsului");
                String prodNume = scanner.nextLine();
                System.out.println("Introduceti pretul produsului");
                int pret = scanner.nextInt();
                System.out.println("Introduceti cantitatea de produs");
                int cant = scanner.nextInt();

                scanner.nextLine();

                System.out.println("Introduceti numarul ingredientelor");
                int nrIngrediente = scanner.nextInt();
                scanner.nextLine();

                ArrayList<String> ing = new ArrayList<>();
                System.out.println("Introduceti numele ingredientelor pe cate o linie");
                while (nrIngrediente > 0){
                    String ingredient = scanner.nextLine();
                    ing.add(ingredient);
                    nrIngrediente -= 1;
                }
                restaurant.AdaugaMancare(prodNume, pret, cant, ing);
                break;
            }
            case("4"):{
                System.out.println("Introdu numele restaurantului");
                String numeRest = scanner.nextLine();
                Restaurant restaurant = gasesteRestaurant(numeRest);

                System.out.println("Introduceti numele bauturii");
                String prodNume = scanner.nextLine();
                System.out.println("Introduceti pretul bauturii");
                int pret = scanner.nextInt();
                System.out.println("Introduceti volumul de bautura");
                int cant = scanner.nextInt();
                restaurant.AdaugaBautura(prodNume, pret, cant);

                break;
            }
            case("5"):{
                System.out.println("Introdu numele restaurantului");
                String numeRest = scanner.nextLine();
                Restaurant restaurant = gasesteRestaurant(numeRest);

                System.out.println("Introduceti numele felului de mancare sauu al bauturii");
                String prodNume = scanner.nextLine();
                Produs produs = restaurant.CumparaProdus(prodNume);
                if (produs != null){
                    System.out.println("Produsul se afla in meniu");
                }
                else{
                    System.out.println("Produsul nu se afla in meniu");
                }
                break;
            }
            case("6"):{
                System.out.println("Introdu numele restaurantului");
                String numeRest = scanner.nextLine();
                Restaurant restaurant = gasesteRestaurant(numeRest);
                restaurant.AfiseazaClienti();
                break;
            }
            case("7"):{
                System.out.println("Introdu numele restaurantului");
                String numeRest = scanner.nextLine();
                Restaurant restaurant = gasesteRestaurant(numeRest);

                System.out.println("Introduceti numele, username-ul si parola pe cate o linie");

                String nume, username, parola;
                nume = scanner.nextLine();
                username = scanner.nextLine();
                parola = scanner.nextLine();
                restaurant.AdaugaClient(nume, username, parola, 0);
                break;
            }
            case("8"):{
                for (Sofer sofer: soferi){
                    sofer.AfiseazaSofer();
                }
                break;
            }
            case("9"):{
                System.out.println("Introdu numele soferului");
                String nume = scanner.nextLine();
                Sofer sofer = gasesteSofer(nume);
                if (sofer != null)
                    sofer.MaresteSalariu();
                else{
                    System.out.println("Soferul nu exista.");
                }
                break;
            }
            case("10"):{
                for (Sofer sofer: soferi){
                    Masina masina = sofer.getMasina();
                    masina.AfiseazaMasina();
                }
                break;
            }
            default: {
                    System.out.println("Optiune invalida");
                }
            }
        }
    }
}
