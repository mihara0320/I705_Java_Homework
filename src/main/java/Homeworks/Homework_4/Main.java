package Homeworks.Homework_4;

import java.util.Scanner;

/**
 * Created by masaki on 4/29/2017.
 */
public class Main {

    public static void main(String[] args) {
        HeadlineCollector headlineCollector = new HeadlineCollector();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Options\n");
            System.out.println("(1) - run program");
            System.out.println("(2) - list log");
            System.out.println("(3) - search");

            System.out.print("Please enter your selection:\t");
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1: headlineCollector.run(); break;
                case 2: headlineCollector.listLogs(); break;
                case 3: headlineCollector.search(); break;
                default: System.exit(0);
            }
        }


    }
}

