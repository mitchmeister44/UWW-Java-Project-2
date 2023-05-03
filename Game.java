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
        p2.playerTurn(input);
        p2.displayPets();
        p1.attack(p1.petDeck.get(0),p2.petDeck.get(0));
    }

    public static void rounds2P(Scanner input, Player p1, Player p2){
        int round =0;
        String optionChose;
        for (int i=0;i<5;i++){
            round++;
            for (int j=0;j<2;j++){
                System.out.printf("Round %d start: Player %d choose your action",round, j);
                while(true){
                    if(j==0){
                        try{
                            optionChose= input.nextLine();
                            if(optionChose.equalsIgnoreCase("u")){
                                p1.upgradePets(input);
                                break;
                            } else if(optionChose.equalsIgnoreCase("b")){
                                p1.buyPets();
                                break;
                            }else if(optionChose.equalsIgnoreCase("s")){
                                p1.shop(input);
                                break;
                            }
                        } catch (InputMismatchException e){
                            System.out.println("invalid input, value accepted are 'u', 'b', or 's'");
                        }
                    } else  if(j==1){
                        try{
                            optionChose= input.nextLine();
                            if(optionChose.equalsIgnoreCase("u")){
                                p2.upgradePets(input);
                                break;
                            } else if(optionChose.equalsIgnoreCase("b")){
                                p2.buyPets();
                                break;
                            }else if(optionChose.equalsIgnoreCase("s")){
                                p2.shop(input);
                                break;
                            }
                        } catch (InputMismatchException e){
                            System.out.println("invalid input, value accepted are 'u', 'b', or 's'");
                        }
                    }
                }
            }
        }
    }

    public static void rounds4P(Scanner input, Player p1, Player p2, Player p3, Player p4){
        int round =0;
        String optionChose;
        for (int i=0;i<5;i++){
            round++;
            for (int j=0;j<4;j++){
                System.out.printf("Round %d start: Player %d choose your action%n",round, j);
                while(true){
                    if(j==0){
                        try{
                            optionChose= input.nextLine();
                            if(optionChose.equalsIgnoreCase("u")){
                                p1.upgradePets(input);
                                break;
                            } else if(optionChose.equalsIgnoreCase("b")){
                                p1.buyPets();
                                break;
                            }else if(optionChose.equalsIgnoreCase("s")){
                                p1.shop(input);
                                break;
                            }
                        } catch (InputMismatchException e){
                            System.out.println("invalid input, value accepted are 'u', 'b', or 's'");
                        }
                    } else  if(j==1){
                        try{
                            optionChose= input.nextLine();
                            if(optionChose.equalsIgnoreCase("u")){
                                p2.upgradePets(input);
                                break;
                            } else if(optionChose.equalsIgnoreCase("b")){
                                p2.buyPets();
                                break;
                            }else if(optionChose.equalsIgnoreCase("s")){
                                p2.shop(input);
                                break;
                            }
                        } catch (InputMismatchException e){
                            System.out.println("invalid input, value accepted are 'u', 'b', or 's'");
                        }
                    } else  if(j==2){
                        try{
                            optionChose= input.nextLine();
                            if(optionChose.equalsIgnoreCase("u")){
                                p3.upgradePets(input);
                                break;
                            } else if(optionChose.equalsIgnoreCase("b")){
                                p3.buyPets();
                                break;
                            }else if(optionChose.equalsIgnoreCase("s")){
                                p3.shop(input);
                                break;
                            }
                        } catch (InputMismatchException e){
                            System.out.println("invalid input, value accepted are 'u', 'b', or 's'");
                        }
                    } else  if(j==3){
                        try{
                            optionChose= input.nextLine();
                            if(optionChose.equalsIgnoreCase("u")){
                                p4.upgradePets(input);
                                break;
                            } else if(optionChose.equalsIgnoreCase("b")){
                                p4.buyPets();
                                break;
                            }else if(optionChose.equalsIgnoreCase("s")){
                                p4.shop(input);
                                break;
                            }
                        } catch (InputMismatchException e){
                            System.out.println("invalid input, value accepted are 'u', 'b', or 's'");
                        }
                    }
                }
            }
        }
    }

}
