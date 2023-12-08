
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class smartSteal {

    public static void main(String[] args) {
        startAnimation();
        executeCommand("cmd.exe", "/c", "ipconfig");
        executeCommand();
    }

    public static void startAnimation() {
        int duration = 10; // Duration of the animation in seconds

        long startTime = System.currentTimeMillis();

        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = (currentTime - startTime) / 1000;

            if (elapsedTime >= duration) {
                break;
            }

            // Clear console for a smoother animation
            clearConsole();

            // Display the animated symbols and numbers
            displayAnimation(elapsedTime);

            // Sleep for a short duration to control the speed of the animation
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearConsole() {
        // Clear console for different operating systems
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayAnimation(long elapsedTime) {
        // Example: Display animated symbols and numbers
        int symbolIndex = (int) (elapsedTime % 3); // Change this for different symbols
        System.out.println("IP configuration array: " + getSymbol(symbolIndex));

        int number = (int) (elapsedTime % 10); // Change this for different numbers
        System.out.println("Connection MS: " + number);
    }

    public static char getSymbol(int index) {
        char[] symbols = {'-', '|', '/'};
        return symbols[index];
    }


    public static void executeCommand(String... command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the command to complete
            int exitCode = process.waitFor();
            System.out.println("Command exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
