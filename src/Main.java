import view.ViewConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final String EXIT = "0";

    public static void main(String[] args) throws IOException {
        ViewConsole vc = new ViewConsole();
        vc.run();
    }
}
