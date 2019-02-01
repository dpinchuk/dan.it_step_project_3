import utils.Loader;
import view.ViewConsole;

/**
 * Main class for run app
 */
public class Main {

    /**
     * psvm -> entry point
     * @param args String[]
     */
    public static void main(String[] args) {
        new Loader().generateDB();
        new ViewConsole().run();
    }

}