package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientCRUD {
    private final static Connection connection = MySqlConn.getInstance().getConnection();
    private ClientCRUD(){}

    public static List<List<String>> getClienti(){
        String sql = "SELECT * FROM client";
        try{
            Statement statement = connection.createStatement();
            ResultSet clienti = statement.executeQuery(sql);
            List<List<String>> result = new ArrayList<>();
            while (clienti.next()){
                List <String> client = new ArrayList<>();
                String nume = clienti.getString(2);
                String username = clienti.getString(3);
                String parola = clienti.getString(4);
                String numarComenzi = clienti.getString(5);

                client.add(nume);
                client.add(username);
                client.add(parola);
                client.add(numarComenzi);

                result.add(client);
            }
            return result;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean adaugaClient(String nume, String username, String parola, int numarComenzi){
        try {
            String sql = "INSERT INTO user (nume, username, parola, numarComenzi) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            statement.setString(2, username);
            statement.setString(3, parola);
            statement.setInt(4, numarComenzi);
            int rows = statement.executeUpdate();
            if(rows > 0){
                System.out.println("Clientul a fost inregistrat cu succes");
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static void schimbaParola(String username, String parolaNoua){
        try {
            String sql = "UPDATE client SET parola = ? WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, parolaNoua);
            statement.setString(2, username);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Parola a fost schimbata.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
