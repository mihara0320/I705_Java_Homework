package Homeworks.Homework_1;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by kenzi on 05/04/2017.
 */
class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word!");
        String word = scanner.next();

        boolean canMoveON = false;

        while(canMoveON == false){

            if(word.length() > 8 ){
                System.out.println("The input is too long, Enter again!");
                word = scanner.next();
            } else {
                canMoveON = true;
            }
        }

        AsciiConverter converter = new AsciiConverter();
        converter.convert(word.toUpperCase());
    }
}
