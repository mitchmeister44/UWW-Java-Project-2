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
        Random rand = new Random();
        int r1 = rand.nextInt(5);
        int r2 = rand.nextInt(5);
        while(r2 == r1) {
            r2 = rand.nextInt(5);
        }
        int r3 = rand.nextInt(5);
        while(r3 == r2 || r3 == r1) {
            r3 = rand.nextInt(5);
        }
        Pet[] basePetArr = new Pet[]{new Ant(), new Beaver(), new Cricket(), new Duck(), new Horse()};

        this.numTurn = 1;
        this.goldCurrency = 10;
        this.numFood = 0;
        this.petDeck = new LinkedList<Pet>();
        petDeck.add(basePetArr[r1]);
        petDeck.add(basePetArr[r2]);
        petDeck.add(basePetArr[r3]);
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
