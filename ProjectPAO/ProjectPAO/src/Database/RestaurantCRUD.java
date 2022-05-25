package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantCRUD {
    private final static Connection connection = MySqlConn.getInstance().getConnection();
    private RestaurantCRUD(){}
    public static List<List<String>> getRestaurante(){
        String sql = "SELECT * FROM restaurant";
        try{
            Statement statement = connection.createStatement();
            ResultSet restaurante = statement.executeQuery(sql);
            List<List<String>> result = new ArrayList<>();
            while (restaurante.next()){
                List <String> restaurant = new ArrayList<>();
                String nume = restaurante.getString(2);
                String adresa = restaurante.getString(3);
                restaurant.add(nume);
                restaurant.add(adresa);
                result.add(restaurant);
            }
            return result;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void adaugaRestaurant(String nume, String adresa){
        try {
            String sql = "INSERT INTO restaurant (nume, adresa) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            statement.setString(2, adresa);
            int rows = statement.executeUpdate();
            if(rows > 0){
                System.out.println("Restaurantul a fost adaugat");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void stergeRestaurant(String nume){
        try {
            String sql = "DELETE FROM restaurant WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Restaurantul a fost sters");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
