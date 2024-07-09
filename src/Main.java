import util.DatabaseInitializer;
import view.RegisterPage;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();

        RegisterPage registerPage = new RegisterPage();
        registerPage.setVisible(true);
    }
}
