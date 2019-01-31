import utils.Loader;
import view.ViewConsole;

public class Main {

    public static void main(String[] args) {
        new Loader().generateDB();
        new ViewConsole().run();
    }

}