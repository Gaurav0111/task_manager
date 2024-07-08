import util.DatabaseInitializer;
import view.LoginPage;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();

        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}
