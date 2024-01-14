// Liliana Klavebäck Martinez likl4662@SU.SE

import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStream;

public class InputReader {
    private static final String END_OF_TEXT = "?> ";

    private static ArrayList<InputStream> inputStreamList = new ArrayList<>();
    private Scanner scanner;

    public InputReader() {
        this(System.in);
    }

    public InputReader(InputStream inputStream) {
        if (inputStreamList.contains(inputStream))
            throw new IllegalStateException("Error: Stream already in use!");
        inputStreamList.add(inputStream);
        this.scanner = new Scanner(inputStream);
    }

    public String handleText(String userInput) {
        System.out.print(userInput + END_OF_TEXT);
        String input = scanner.nextLine().trim();
        return input;
    }

    public int handleInteger(String userInput) {
        System.out.print(userInput + END_OF_TEXT);
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public double handleDouble(String userInput) {
        System.out.print(userInput + END_OF_TEXT);
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    
}

