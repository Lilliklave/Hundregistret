// 

import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStream;

public class HandleUserInput {
    private static final String END_OF_TEXT = "?> ";

    private static ArrayList<InputStream> inputStreamList = new ArrayList<>();
    private Scanner scanner;

    public HandleUserInput() {
        this(System.in);
    }

    public HandleUserInput(InputStream inputStream) {
        if (inputStreamList.contains(inputStream))
            throw new IllegalStateException("Error: Stream already in use!");
        inputStreamList.add(inputStream);
        this.scanner = new Scanner(inputStream);
    }

    public String readText(String userInput) {
        System.out.print(userInput + END_OF_TEXT);
        String input = formatString(scanner.nextLine().trim());
        return input;
    }

    public int readInteger(String userInput) {
        System.out.print(userInput + END_OF_TEXT);
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public double readDouble(String userInput) {
        System.out.print(userInput + END_OF_TEXT);
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    private String formatString(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}