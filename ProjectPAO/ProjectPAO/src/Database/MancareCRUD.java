package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MancareCRUD{
    private final static Connection connection = MySqlConn.getInstance().getConnection();
    private MancareCRUD(){}

    public static List<List<String>> getMancare(){
        String sql = "SELECT * FROM mancare";
        try{
            Statement statement = connection.createStatement();
            ResultSet mancaruri = statement.executeQuery(sql);
            List<List<String>> result = new ArrayList<>();
            while (mancaruri.next()){
                List <String> mancare = new ArrayList<>();
                String nume = mancaruri.getString(2);
                String pret = mancaruri.getString(3);
                String cantitate = mancaruri.getString(4);
                String ingrediente = mancaruri.getString(5);

                mancare.add(nume);
                mancare.add(pret);
                mancare.add(cantitate);
                mancare.add(ingrediente);

                result.add(mancare);
            }
            return result;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void adaugaMancare(String nume, int pret, int cantitate, String ingrediente){
        try {
            String sql = "INSERT INTO mancare (nume, pret, cantitate, ingrediente) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            statement.setInt(2, pret);
            statement.setInt(3, cantitate);
            statement.setString(4, ingrediente);
            int rows = statement.executeUpdate();
            if(rows > 0){
                System.out.println("Mancare adaugata cu succes.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void modificaPret(String nume) {
        try {
            String sql = "UPDATE mancare SET pret = pret * 1.05 WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Pretul mancarii a fost modificat.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void stergeMancare(String type, String nume){
        if (!(type.equals("mancare"))){
            System.out.println("Tip incorect - ar trebui sa fie mancare");
            return;
        }
        try {
            String sql = "DELETE FROM " + type +" WHERE " + type +"_nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Mancarea a fost stearsa");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
