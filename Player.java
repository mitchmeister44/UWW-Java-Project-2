import java.util.*;
public class Player {
    //Pet deck (player's lineup) and pet shop will behave as separate linked lists
    //Other variables are self-explanatory
    private LinkedList<Pet> petDeck;
    private LinkedList<Pet> petShop;
    private int numTurn;
    private int goldCurrency;
    private int numFood;
    private int numPlayer;
    
    Player(int numPlayer) {
        this.numTurn = 1;
        this.goldCurrency = 10;
        this.numFood = 0;
        this.petDeck = new LinkedList<Pet>();
        petDeck.add(new Ant());
        petDeck.add(new Beaver());
        petDeck.add(new Cricket());
        petDeck.add(new Duck());
        petDeck.add(new Horse());
        this.numPlayer = numPlayer;
    }
    //Will be contained within each game method (computer or human)
    public void playerTurn(Scanner input) {
        System.out.printf("Player %d's turn %d will now commence!%n", numPlayer, numTurn);
    }
    //Game progression method (gold addition, shop reset, etc.)
    public void progress() {
        this.goldCurrency = 10;
        this.petShop = new LinkedList<Pet>();
        numTurn++;
    }
    
    public void displayPets() {
        System.out.printf("Player %d's pet deck:%n",numPlayer);
        for(int i = 0; i < petDeck.size(); i++) {
            System.out.println(petDeck.get(i).toString());
        }
    }
}
