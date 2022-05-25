package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BauturaCRUD{
    private final static Connection connection = MySqlConn.getInstance().getConnection();
    private BauturaCRUD(){}

    public static List<List<String>> getBautura(){
        String sql = "SELECT * FROM bautura";
        try{
            Statement statement = connection.createStatement();
            ResultSet bauturi = statement.executeQuery(sql);
            List<List<String>> result = new ArrayList<>();
            while (bauturi.next()){
                List <String> bautura = new ArrayList<>();
                String nume = bauturi.getString(2);
                String pret = bauturi.getString(3);
                String marime = bauturi.getString(4);

                bautura.add(nume);
                bautura.add(pret);
                bautura.add(marime);

                result.add(bautura);
            }
            return result;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void adaugaBautura(String nume, int pret, int marime){
        try {
            String sql = "INSERT INTO bautura (nume, pret, marime) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            statement.setInt(2, pret);
            statement.setInt(3, marime);
            int rows = statement.executeUpdate();
            if(rows > 0){
                System.out.println("Bautura adaugata cu succes.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void modificaPretBautura(String nume) {
        try {
            String sql = "UPDATE bautura SET pret = pret * 1.15 WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Pretul bauturii a fost modificat");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void stergeBautura(String type, String nume){
        if (!(type.equals("bautura"))){
            System.out.println("Tip incorect - ar trebui sa fie bautura");
            return;
        }
        try {
            String sql = "DELETE FROM " + type +" WHERE " + type +"_nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Bautura a fost stearsa");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}