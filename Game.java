import java.util.*;
import java.lang.*;
import java.io.*;
import java.text.*;
import java.time.*;
import java.util.concurrent.*;
public class Game {
    public static void main (String[] args) {
        try(Scanner reader = new Scanner(new File("statistics.txt"))){
            String line = reader.nextLine();
            System.out.printf("Last Login: %s%n",line);
        }

        catch(FileNotFoundException e) {
            System.out.println("Statistics file empty, this is your first login.");
        }
        catch(NoSuchElementException e) {
            System.out.println("Statistics file empty, this is your first login.");
        }
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
        File file = new File("statistics.txt");
        BufferedWriter writer = null;
        TimeUnit time = TimeUnit.SECONDS;
        int p1Wins = 0;
        int p2Wins = 0;
        int p3Wins = 0;
        int p4Wins = 0;
        String p1Name,p2Name,p3Name,p4Name;
        String formattedGameOutcome1 = " ";
        String formattedGameOutcome2 = " ";
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        Player p3 = new Player(3);
        Player p4 = new Player(4);
        System.out.println("All players will start with the 3 default pets, additional pets will be available for purchase from the shop as the game progresses.");
        p1.displayPets();
        p2.displayPets();
        p3.displayPets();
        p4.displayPets();
        rounds4P(input, p1, p2, p3, p4);
        if(p1.petDeck.size() > p2.petDeck.size()) {
            System.out.println("Player 2, please add a pet to even the pet quantities.");
            p2.upgradePets(input);
        }
        else if(p1.petDeck.size() < p2.petDeck.size()) {
            System.out.println("Player 1, please add a pet to even the pet quantities.");
            p1.upgradePets(input);
        }
        else if(p3.petDeck.size() > p4.petDeck.size()) {
            System.out.println("Player 4, please add a pet to even the pet quantities.");
            p4.upgradePets(input);
        }
        else if(p3.petDeck.size() < p4.petDeck.size()) {
            System.out.println("Player 3, please add a pet to even the pet quantities.");
            p3.upgradePets(input);
        }
        System.out.println("All pets now casting special abilities...");
        for(int i = 0; i < p1.petDeck.size()-1; i++) {
            p1.petDeck.get(i).useSpecial(p1.petDeck.get(i+1));
            if(i == 2) {
                p1.petDeck.get(i).useSpecial(p1.petDeck.get(0));
            }
        }
        for(int i = 0; i < p2.petDeck.size()-1; i++) {
            p2.petDeck.get(i).useSpecial(p2.petDeck.get(i+1));
            if(i == 2) {
                p2.petDeck.get(i).useSpecial(p2.petDeck.get(0));
            }
        }
        for(int i = 0; i < p3.petDeck.size()-1; i++) {
            p3.petDeck.get(i).useSpecial(p3.petDeck.get(i+1));
            if(i == 2) {
                p3.petDeck.get(i).useSpecial(p3.petDeck.get(0));
            }
        }
        for(int i = 0; i < p4.petDeck.size()-1; i++) {
            p4.petDeck.get(i).useSpecial(p4.petDeck.get(i+1));
            if(i == 2) {
                p4.petDeck.get(i).useSpecial(p4.petDeck.get(0));
            }
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
        Date date = new Date();
        try{
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(formatter.format(date));
            writer.write("\n");
            writer.write("Final pet decks:\n");
            writer.write("Player 1:\n");
            writer.write(p1.petDeck.toString());
            writer.write("\n");
            writer.write("Player 2:\n");
            writer.write(p2.petDeck.toString());
            writer.write("Player 3:\n");
            writer.write(p3.petDeck.toString());
            writer.write("Player 4:\n");
            writer.write(p4.petDeck.toString());
            writer.write("\n");
        }
        catch (IOException e) {
            System.out.println("Could not write to file");
        }
        System.out.println("Final pet decks before battle: ");
        p1.displayPets();
        p2.displayPets();
        p3.displayPets();
        p4.displayPets();
        try{
            time.sleep(10);
        }
        catch(InterruptedException e) {

        }
        System.out.println("Players 1 and 2 will now face off!");
        for(int i = 0; i < p1.petDeck.size(); i++) {
            if(p1.attack(p1Wins, p2Wins,p1.petDeck.get(i),p2.petDeck.get(i)) == 2) {
                p2Wins++;
            }
            else {
                p1Wins++;
            }
        }
        if(p1Wins > p2Wins) {
            System.out.printf("Player 1 wins %d to %d over player 2!%n",p1Wins,p2Wins);
            formattedGameOutcome1 = String.format("Player 1 wins %d to %d over player 2!%n",p1Wins,p2Wins);
        }
        else if(p2Wins > p1Wins) {
            System.out.printf("Player 2 wins %d to %d over player 1!%n",p2Wins,p1Wins);
            formattedGameOutcome1 = String.format("Player 2 wins %d to %d over player 2!%n",p2Wins,p1Wins);
        }
        System.out.println("Players 3 and 4 will now face off!");
        System.out.println("To clarify as a means of avoiding confusion, player 3 will be shown as '1' and 4 as '2'");
        for(int i = 0; i < p3.petDeck.size(); i++) {
            if(p3.attack(p3Wins, p4Wins,p3.petDeck.get(i),p4.petDeck.get(i)) == 2) {
                p4Wins++;
            }
            else {
                p3Wins++;
            }
        }
        if(p3Wins > p4Wins) {
            System.out.printf("Player 3 wins %d to %d over player 4!%n",p3Wins,p4Wins);
            formattedGameOutcome2 = String.format("Player 3 wins %d to %d over player 4!%n",p3Wins,p4Wins);
        }
        else if(p2Wins > p1Wins) {
            System.out.printf("Player 4 wins %d to %d over player 3!%n",p4Wins,p3Wins);
            formattedGameOutcome2 = String.format("Player 4 wins %d to %d over player 3!%n",p4Wins,p3Wins);
        }
        input.nextLine();
        System.out.println("All players, please enter your names.");
        System.out.println("Player 1:");
        p1Name = input.nextLine();
        System.out.println("Player 2:");
        p2Name = input.nextLine();
        System.out.println("Player 3:");
        p3Name = input.nextLine();
        System.out.println("Player 4:");
        p4Name = input.nextLine();
        String formattedPlayerNames = String.format("Player 1: %s%nPlayer 2: %s%nPlayer 3: %s%nPlayer 4: %s%n",p1Name,p2Name,p3Name,p4Name);
        System.out.println("Summary statistics for the most recent playthrough will be available via the program's text file. Thank you for playing!");
        try{
            writer.write("\n");
            writer.write("Game Type: 4 player\n");
            writer.write(formattedPlayerNames);
            writer.write("Game Outcomes:\n");
            writer.write("Game 1: ");
            writer.write(formattedGameOutcome1);
            writer.write("\n");
            writer.write("Game 2: ");
            writer.write(formattedGameOutcome2);
        }
        catch (IOException e) {
            System.out.println("Could not write to file");
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("I/O error occurred");
            }
        }
    }

    public static void PV2(Scanner input) {
        File file = new File("statistics.txt");
        BufferedWriter writer = null;
        TimeUnit time = TimeUnit.SECONDS;
        int p1Wins = 0;
        int p2Wins = 0;
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        String p1Name,p2Name;;
        String formattedGameOutcome = " ";
        System.out.println("All players will start with the 3 default pets, additional pets will be available for purchase from the shop as the game progresses.");
        p1.displayPets();
        p2.displayPets();
        rounds2P(input, p1, p2);
        if(p1.petDeck.size() > p2.petDeck.size()) {
            System.out.println("Player 2, please add a pet to even the pet quantities.");
            p2.upgradePets(input);
        }
        else if(p1.petDeck.size() < p2.petDeck.size()) {
            System.out.println("Player 1, please add a pet to even the pet quantities.");
            p1.upgradePets(input);
        }
        System.out.println("All pets now casting their special abilities...");
        for(int i = 0; i < p1.petDeck.size()-1; i++) {
            p1.petDeck.get(i).useSpecial(p1.petDeck.get(i+1));
            if(i == 2) {
                p1.petDeck.get(i).useSpecial(p1.petDeck.get(0));
            }
        }
        for(int i = 0; i < p2.petDeck.size()-1; i++) {
            p2.petDeck.get(i).useSpecial(p2.petDeck.get(i+1));
            if(i == 2) {
                p2.petDeck.get(i).useSpecial(p2.petDeck.get(0));
            }
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
        Date date = new Date();
        try{
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(formatter.format(date));
            writer.write("\n");
            writer.write("Final pet decks:\n");
            writer.write("Player 1:\n");
            writer.write(p1.petDeck.toString());
            writer.write("\n");
            writer.write("Player 2:\n");
            writer.write(p2.petDeck.toString());
            writer.write("\n");
        }
        catch (IOException e) {
            System.out.println("Could not write to file");
        }
        System.out.println("Final pet decks before battle: ");
        p1.displayPets();
        p2.displayPets();
        try{
            time.sleep(10);
        }
        catch(InterruptedException e) {

        }
        for(int i = 0; i < p1.petDeck.size(); i++) {
            if(p1.attack(p1Wins, p2Wins,p1.petDeck.get(i),p2.petDeck.get(i)) == 2) {
                p2Wins++;
            }
            else {
                p1Wins++;
            }
        }

        if(p1Wins > p2Wins) {
            System.out.printf("Player 1 wins %d to %d over player 2!%n",p1Wins,p2Wins);
            formattedGameOutcome = String.format("Player 1 wins %d to %d over player 2!%n",p1Wins,p2Wins);
        }
        else if(p2Wins > p1Wins) {
            System.out.printf("Player 2 wins %d to %d over player 1!%n",p2Wins,p1Wins);
            formattedGameOutcome = String.format("Player 2 wins %d to %d over player 1!%n",p1Wins,p2Wins);
        }
        System.out.println("Summary statistics for the most recent playthrough will be available via the program's text file. Thank you for playing!");
        input.nextLine();
        System.out.println("All players, please enter your names.");
        System.out.println("Player 1:");
        p1Name = input.nextLine();
        System.out.println("Player 2:");
        p2Name = input.nextLine();
        String formattedPlayerNames = String.format("Player 1: %s%nPlayer 2: %s%n",p1Name,p2Name);
        try{
            writer.write("\n");
            writer.write("Game Type: 2 player\n");
            writer.write(formattedPlayerNames);
            writer.write("Game Outcomes:\n");
            writer.write("Game 1: ");
            writer.write(formattedGameOutcome);
            writer.write("\n");
        }
        catch (IOException e) {
            System.out.println("Could not write to file");
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("I/O error occurred");
            }
        }
    }

    public static void rounds2P(Scanner input, Player p1, Player p2){
        int round =0;
        String optionChose;
        for (int i=0;i<5;i++){
            round++;
            for (int j=0;j<2;j++){
                System.out.printf("Round %d start: Player %d choose your action ('u' to upgrade, 'b' to buy, 's' to access the shop%n",round, j+1);
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
                            System.out.println("invalid input, applicable responses are 'u', 'b', or 's'");
                        }
                    } else if(j==1){
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
                            System.out.println("invalid input, applicable responses are 'u', 'b', or 's'");
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
                System.out.printf("Round %d start: Player %d choose your action ('u' to upgrade, 'b' to buy, 's' to access the shop%n",round, j+1);
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
                            System.out.println("invalid input, applicable responses are 'u', 'b', or 's'");
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
                            System.out.println("invalid input, applicable responses are 'u', 'b', or 's'");
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
                            System.out.println("invalid input, applicable responses are 'u', 'b', or 's'");
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
                            System.out.println("invalid input, applicable responses are 'u', 'b', or 's'");
                        }
                    }
                }
            }
        }
    }

}
