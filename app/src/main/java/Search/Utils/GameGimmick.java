package Search.Utils;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class GameGimmick implements GimmickAction {
    @SuppressWarnings("resource")
    @Override
    public String gimmick(String key) {
        // Implement the gimmick for "random" node
        Random random = new java.util.Random();

        if (key.toString().equals("dice")) {
            System.out.println("Rolling the dice...");
            int diceRoll = random.nextInt(6) + 1; // Generates a number between 1 and 6
            System.out.println("You rolled a: " + diceRoll);
        }else if (key.toString().equals("kalkulator")) {
            Scanner scanner = new Scanner(System.in);
        
            System.out.println("Welcome to the Calculator!");
            System.out.println("Select an operation: +, -, *, /");
            char operation = scanner.next().charAt(0);
            
            System.out.println("Enter the first number: ");
            double num1 = scanner.nextDouble();
            
            System.out.println("Enter the second number: ");
            double num2 = scanner.nextDouble();
            
            double result;
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    System.out.println("Result: " + result);
                    break;
                case '-':
                    result = num1 - num2;
                    System.out.println("Result: " + result);
                    break;
                case '*':
                    result = num1 * num2;
                    System.out.println("Result: " + result);
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                        System.out.println("Result: " + result);
                    } else {
                        System.out.println("Error: Division by zero is not allowed.");
                    }
                    break;
                default:
                    System.out.println("Invalid operation selected.");
            }
        }else if (key.toString().equals("coin")) {
            System.out.println("Flipping the coin...");
            boolean flipResult = random.nextBoolean(); // Randomly returns true or false
            
            if (flipResult) {
                System.out.println("The coin landed on: Heads");
            } else {
                System.out.println("The coin landed on: Tails");
            }
        }else if (key.toString().equals("close")) {
            String command;
            String osName = System.getProperty("os.name").toLowerCase();

            if (osName.contains("windows")) {
                command = "taskkill /IM Code.exe /F";
            } else if (osName.contains("mac")) {
                command = "osascript -e 'quit app \"Visual Studio Code\"'";
            } else { // Linux
                command = "pkill code";
            }

            try {
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "gimmick";
    }
}
