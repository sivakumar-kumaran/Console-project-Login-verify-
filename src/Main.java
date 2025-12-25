import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("enter name :");
        String name=in.nextLine();
        System.out.println("enter password :");
        String pass=in.nextLine();
        String url = "jdbc:mysql://localhost:3306/login_schema"; //?useSSL=false&serverTimezone=UTC
        String username = "root";
        String password = "Siva@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Database connected successfully!");

            Statement statement = connection.createStatement();

            // INSERT static values
            String insertSql =
                    "INSERT IGNORE INTO users (username, password) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(insertSql);

            ps.setString(1, name);
            ps.setString(2, pass);

            ps.executeUpdate();

            // SELECT
            String selectSql = "SELECT * FROM users where password='Siva@12345'";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username")
                + "Password: " + resultSet.getString("password"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
