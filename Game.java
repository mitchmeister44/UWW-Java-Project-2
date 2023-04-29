//https://docs.google.com/document/d/10V8uHS4LzVFcNgaSif8vy0swEWv-N0wdmW5KmS45pOo/edit?usp=sharing
import java.util.*;
import java.lang.*;
import java.io.*;
public class Game {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        String userResponse;
        System.out.println("Welcome to our interpretation of the popular autobattler Super Auto Pets! We hope you enjoy!");
        while(true) {
            System.out.println("Please start by selecting the way in which you'll play. Enter 't' to play live with two players or 'c' to match up against the computer.");
            try {
                userResponse = input.nextLine();
                if(userResponse.equalsIgnoreCase("c")) {
                    //run one player game method (PVC)
                    PVC(input);
                    break;
                }
                else if(userResponse.equalsIgnoreCase("t")) {
                    //run two player game method (PVP)
                    PVP(input);
                    break;
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid input, computer will only accept 'c' or 't'");
            }
        }
    }
    public static void PVP(Scanner input) {
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        p1.playerTurn(input);
        p1.displayPets();
    }
    public static void PVC(Scanner input) {
        Player p1 = new Player(1);
        Player p2 = new Player(2);
    }
}
