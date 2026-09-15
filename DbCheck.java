import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbCheck {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/snehband_snehbandhan?useSSL=false&serverTimezone=UTC",
                "root",
                "Pavinash@123"
            );
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT user_name, password FROM USER LIMIT 5");
            while (rs.next()) {
                System.out.println("USER: " + rs.getString(1) + " | PASS: " + rs.getString(2));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
