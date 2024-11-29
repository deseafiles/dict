package Search;

import java.util.Scanner;
import Search.Models.SearchEngine;

public class App {
    public static void main(String[] args) {

        SearchEngine engine = new SearchEngine();

        engine.addData("dice","gimmick");
        engine.addData("kalkulator","gimmick");
        engine.addData("coin","gimmick");
        engine.addData("close","gimmick");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Mini Search Engine!");

        while (true) {
                System.out.println("\n1. Search\n2. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    engine.search(keyword);
                } else if (choice == 2) {
                    System.out.println("Exiting search engine. Goodbye!");
                    scanner.close();
                    break;
                } else {
                    System.out.println("Invalid choice, try again.");
                }
        }
    }
}
