package Service;

import Clase.*;
import Data.Reader;
import Data.Writer;
import Database.*;
import com.sun.source.util.SourcePositions;

import java.util.*;

public class ServiceClass {
    private static ServiceClass instance;
    private List<Restaurant> restaurante;
    private List<Comanda> comenzi;
    private List<Sofer> soferi;
    private List<Mancare> mancaruri;
    private List<Bautura> bauturi;
    private List<Client> clienti;
    final private Scanner scanner;
    private Reader reader;
    private Writer writer;
    private AuditService auditService;

    private ServiceClass() {
        restaurante = new ArrayList<>();
        comenzi = new ArrayList<>();
        soferi = new ArrayList<>();
        scanner = new Scanner(System.in);
        reader = Reader.getInstance();
        writer = Writer.getInstance();
        auditService = AuditService.getInstance();
        mancaruri = new ArrayList<>();
        bauturi = new ArrayList<>();
        clienti = new ArrayList<>();
    }

    public static ServiceClass getInstance(){
        if (instance == null)
            instance = new ServiceClass();
        return instance;
    }

    public void adaugaMancareInitial(Restaurant restaurant, List<List <String>> mancaruri){
        int i = 1;
        while (i < mancaruri.size()){
            List <String> line = mancaruri.get(i);
            String nume = line.get(0);
            int pret = Integer.parseInt(line.get(1));
            int cantitate = Integer.parseInt(line.get(2));
            List <String> ingrediente = Arrays.asList(line.get(3).split(";"));
            restaurant.AdaugaMancare(nume, pret, cantitate, ingrediente);
            i += 1;
        }
    }



    public void adaugaBauturiInitial(Restaurant restaurant, List<List <String>> bauturi){
        int i = 1;
        while (i < bauturi.size()){
            List <String> line = bauturi.get(i);
            String nume = line.get(0);
            int pret = Integer.parseInt(line.get(1));
            int marime = Integer.parseInt(line.get(2));
            restaurant.AdaugaBautura(nume, pret, marime);
            i += 1;
        }
    }


    public void adaugaClientiInitial(Restaurant restaurant, List<List <String>> clienti){
        int i = 1;
        while (i < clienti.size()){
            List <String> line = clienti.get(i);
            String nume = line.get(0);
            String username = line.get(1);
            String parola = line.get(2);
            int numarComenzi = Integer.parseInt(line.get(3));
            restaurant.AdaugaClient(nume, username, parola, numarComenzi);
            i += 1;
        }
    }


//    private void adaugaRestauranteInitial(List<List<String>> restauranteParam) {
////        int i = 1;
////        List<List<String>> mancare = reader.read("Mancare");
////        List<List<String>> bautura = reader.read("Bautura");
////        List<List<String>> client = reader.read("Client");
////        while (i < restauranteParam.size()){
////            List <String> line = restauranteParam.get(i);
////            String nume = line.get(0);
////            String adresa = line.get(1);
////            Restaurant r = new Restaurant(nume, adresa, 100, null, null);
////            adaugaMancaruri(r, mancare);
////            adaugaBauturi(r, bautura);
////            adaugaClienti(r, client);
////            restaurante.add(r);
////            i += 1;
////        }
////    }

    private void adaugaRestaurante(List<List<String>> restauranteParam) {
        List<List<String>> mancaruri = MancareCRUD.getMancare();
        List<List<String>> bauturi = BauturaCRUD.getBautura();
        List<List<String>> clienti = ClientCRUD.getClienti();

        int i = 0;
        while(i < restauranteParam.size()) {
            List <String> line = restauranteParam.get(i);
            String nume = line.get(0);
            String adresa = line.get(1);
            Restaurant r = new Restaurant(nume, adresa, 100, null, null);
            adaugaMancareInitial(r, mancaruri);
            adaugaBauturiInitial(r, bauturi);
            adaugaClientiInitial(r, clienti);
            restaurante.add(r);
            i += 1;
        }

    }


    private void AdaugaDate() {
        //List<List<String>> restaurante = reader.read("Restaurant");
        List<List<String>> restaurante = RestaurantCRUD.getRestaurante();
        adaugaRestaurante(restaurante);

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
        System.out.println("11 Adauga restaurant");
        System.out.println("12 Sterge restaurant");
        System.out.println("exit pentru a iesi\n");

        String option = scanner.nextLine();
        switch(option){
            case("exit"):{
                return;
            }
            case("1"):{
                afiseazaRestaurante();
                CRUD.writeAction("s-au afisat restaurantele");
                //auditService.writeAction("a vazut restaurantele");
                break;
            }
            case("2"):{
                System.out.println("Introdu numele restaurantului");
                String numeRest = scanner.nextLine();
                Restaurant restaurant = gasesteRestaurant(numeRest);
                if(restaurant != null){
                    restaurant.AfisareMeniu();
                }
                CRUD.writeAction("s-a afisat meniul");
                //auditService.writeAction("a vazut meniul");
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
                String ingredientsStr = String.join(";", ing);
                MancareCRUD.adaugaMancare(prodNume, pret, cant, ingredientsStr);
                CRUD.writeAction("mancarea a fost adaugata");
//                String linie = prodNume + ',' + String.valueOf(pret) + ',' + String.valueOf(cant) + ',' + String.join("; ", ing);
//                writer.writeLine("Mancare", linie);
//                auditService.writeAction("a fost adaugat un fel de mancare");
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
                BauturaCRUD.adaugaBautura(prodNume, pret, cant);
                CRUD.writeAction("bautura a fost adaugata");
//                String linie = prodNume + ',' + String.valueOf(pret) + ',' + String.valueOf(cant);
//                writer.writeLine("Bautura", linie);
//                auditService.writeAction("a fost adaugata o bautura");

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
                CRUD.writeAction("a fost cautat un produs");
                //auditService.writeAction("a fost cautat un produs");
                break;
            }
            case("6"):{
                System.out.println("Introdu numele restaurantului");
                String numeRest = scanner.nextLine();
                Restaurant restaurant = gasesteRestaurant(numeRest);
                restaurant.AfiseazaClienti();
                CRUD.writeAction("au fost vizionati clientii");
                //auditService.writeAction("au fost vizionati clientii");
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
                String linie = nume + ',' + username + ',' + parola + ",0";
                ClientCRUD.adaugaClient(nume, username, parola, 0);
                CRUD.writeAction("clientul a fost adaugata");
//                writer.writeLine("Client", linie);
//                auditService.writeAction("a fost adaugat un client");
                break;
            }
            case("8"):{
                for (Sofer sofer: soferi){
                    sofer.AfiseazaSofer();
                }
                CRUD.writeAction("au fost afisati soferii");
                //auditService.writeAction("au fost afisati soferii");
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
                CRUD.writeAction("a fost marit un salariu");
                //auditService.writeAction("a fost marit un salariu");
                break;
            }
            case("10"):{
                for (Sofer sofer: soferi){
                    Masina masina = sofer.getMasina();
                    masina.AfiseazaMasina();
                }
                CRUD.writeAction("au fost afisate masinile");
                //auditService.writeAction("au fost afisate masinile");
                break;
            }
            case("11"): {
                System.out.println("Introdu numele restaurantului");
                String numeRest = scanner.nextLine();
                System.out.println("Introdu adresa restaurantului");
                String adresa = scanner.nextLine();

                Restaurant restaurant = new Restaurant(numeRest, adresa, 100, null, null);
                restaurante.add(restaurant);
                RestaurantCRUD.adaugaRestaurant(numeRest, adresa);
                CRUD.writeAction("Restaurantul a fost adaugat");

                break;
            }
            case("12"): {
                System.out.println("Introdu numele restaurantului care sa fie sters");
                String numeRest = scanner.nextLine();
                Restaurant restaurant = gasesteRestaurant(numeRest);
                if(restaurant != null) {
                    RestaurantCRUD.stergeRestaurant(numeRest);
                    CRUD.writeAction("restaurantul a fost sters");
                    restaurante.remove(restaurant);
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
