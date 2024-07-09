package util;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {
    public static void initialize() {
        String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "first_name TEXT NOT NULL, " +
                "last_name TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "phone TEXT NOT NULL, " +
                "username TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL, " +
                "is_admin BOOLEAN NOT NULL, " +
                "org_id INTEGER, " +
                "FOREIGN KEY (org_id) REFERENCES organizations (org_id))";

        String taskTable = "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "description TEXT, " +
                "status TEXT, " +
                "owner TEXT)";

        String orgTable = "CREATE TABLE IF NOT EXISTS organizations (" +
                "org_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "org_name TEXT NOT NULL, " +
                "domain TEXT NOT NULL, " +
                "head_office_address TEXT NOT NULL, " +
                "size INTEGER NOT NULL, " +
                "admin_id INTEGER NOT NULL)";

        String insertAdmin = "INSERT INTO users (first_name, last_name, email, phone, username, password, is_admin, org_id) " +
                "VALUES ('Admin', 'Admin', 'admin@example.com', '1234567890', 'admin', 'admin123', true, null)";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(taskTable);
            stmt.execute(orgTable);
            stmt.execute(insertAdmin); // Insert default admin user

            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
