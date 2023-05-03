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
    Scanner input= new Scanner(System.in);
    

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
    
    public void upgradePets(Scanner input){
        displayPets();
        int userChoice;
        //initiate temp pet
        System.out.println("Which pet would you like to upgrade a tier");
        while(true){
            try {
                userChoice=input.nextInt();
                if (userChoice>=0 && userChoice<=petDeck.size()){
                    //temp pet goes here= petDeck.get(userChoice);
                    //set new pet which is identical just upgraded to petDeck(UserChoice)
                    break;
                }
            } catch (InputMismatchException e){
                System.out.println("you must input a number that corrisponds to one of your pets");
            }
                
            }
        }
    
    
    public void buyPets(){
        
    }
    
    public void shop(Scanner input){
        Random rand = new Random();
        int returnValue;
        String[] foodItems= new String[5];
        foodItems[0]= "Coconut: a temporary shield that fully blocks 1 attack";
        foodItems[1]= "Cherry: a perk that grants increased luck";
        foodItems[2]= "Garlic: a shield that blocks 1 damage each attack";
        foodItems[3]= "Apple: a buff that grants your pet plus 2 health for each fight";
        foodItems[4]= "Meat: a buff that grants your pet plus 2 attack for each fight";
        foodItems[5]= "Chili: a buff that on attack also deals 1 damage to the next pet";
        int item1 = rand.nextInt(5);
        int item2= rand.nextInt(5);
        System.out.printf("Please chose the item you would like:%n %s %n%s",foodItems[item1], foodItems[item2]);
        
        while(true){
            
            try {
               int foodChoice= input.nextInt(); 
               if (foodChoice==1){
                   returnValue= item1;
                   break;
               } else if (foodChoice==2){
                   returnValue = item2;
                   break;
               }
            } catch (InputMismatchException e){
                System.out.println("you must input either a 1 or a 2");
            }
        }
        System.out.println("Which pet would you like to apply the food to?");
        displayPets();
        //temp pet goes here= petDeck.get(userChoice);
        //set new pet which is identical just with food boolean to true
        
    }
}
