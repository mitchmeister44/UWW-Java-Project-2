import java.util.*;
import java.lang.*;
import java.io.*;
public class Game {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        String userResponse;
        System.out.println("Welcome to our interpretation of the popular autobattler Super Auto Pets! We hope you enjoy!");
        while(true) {
            System.out.println("Please start by selecting the way in which you'll play. Enter 't' to play live with two players or 'f' to play with 4");
            try {
                userResponse = input.nextLine();
                if(userResponse.equalsIgnoreCase("f")) {
                    //run four player game method (PV4)
                    PV4(input);
                    break;
                }
                else if(userResponse.equalsIgnoreCase("t")) {
                    //run two player game method (PV2)
                    PV2(input);
                    break;
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid input, computer will only accept 'c' or 't'");
            }
        }
    }

    public static void PV4(Scanner input) {
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        Player p3 = new Player(3);
        Player p4 = new Player(4);
        System.out.println("All players will start with the 5 default pets, 2 additional pets will be available for purchase from the shop as the game progresses.");
        p1.playerTurn(input);
        p1.displayPets();
    }

    public static void PV2(Scanner input) {
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        System.out.println("All players will start with the 5 default pets, 2 additional pets will be available for purchase from the shop as the game progresses.");
        p1.playerTurn(input);
        p1.displayPets();
    }
}
