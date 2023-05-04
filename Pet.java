import java.util.*;
public class Player {
    //Pet deck (player's lineup) and pet shop will behave as separate linked lists
    //Other variables are self-explanatory
    public LinkedList<Pet> petDeck;
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
        Collections.sort(petDeck);
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
                    petDeck.get(userChoice).upgrade();
                    //temp pet goes here= petDeck.get(userChoice);
                    //set new pet which is identical just upgraded to petDeck(UserChoice)
                    break;
                }
                else {
                    throw new Exception();
                }
            } catch (InputMismatchException e){
                System.out.println("You must input a number that corrisponds to one of your pets");
            }
            catch(Exception e) {
                System.out.println("Please input an integer from 0 (corresponding to your first pet) to a number that corresponds to your final pet in your deck (4 for 5th pet, 3 for 4th, etc.)");
            }

        }
    }

    public int attack(int p1Wins, int p2Wins, Pet p1, Pet p2) {
        while(true) {
            Random rand = new Random();
            p1.setAttackMultiplier(p2);
            p2.setAttackMultiplier(p1);
            if(p1.hasCoco) {
                p2.attack = 0.0;
                p1.hasCoco = false;
                System.out.printf("%s casts the coconut food, setting its opponents attack to 0 for this turn!",p1.getClass().getSimpleName());
            }
            else if(p1.hasCherry) {
                p2.luck--;
                p1.hasCherry = false;
                System.out.printf("%s casts the cherry food, decreasing its opponents luck by one point!%n",p1.getClass().getSimpleName());
            }
            else if(p1.hasGarlic) {
                p2.attack--;
                p1.hasGarlic = false;
                System.out.printf("%s casts the garlic food, decreasing its opponents attack by 1!%n",p1.getClass().getSimpleName());
            }
            else if(p1.hasApple) {
                p1.health+=2;
                p1.hasApple = false;
                System.out.printf("%s casts the apple food, increasing its health by 2!%n",p1.getClass().getSimpleName());
            }
            else if(p1.hasMeat) {
                p1.attack++;
                p1.hasMeat = false;
                System.out.printf("%s casts the meat food, increasing its attack by 1!%n",p1.getClass().getSimpleName());
            }
            else if(p1.hasChili) {
                p2.health-=2;
                p1.hasChili = false;
                System.out.printf("%s casts the chili food, decreasing its opponents health by 2!%n",p1.getClass().getSimpleName());
            }
            if(p2.hasCoco) {
                p1.attack = 0.0;
                p2.hasCoco = false;
                System.out.printf("%s casts the coconut food, setting its opponents attack to 0 for this turn!",p2.getClass().getSimpleName());
            }
            else if(p2.hasCherry) {
                p1.luck--;
                p2.hasCherry = false;
                System.out.printf("%s casts the cherry food, decreasing its opponents luck by one point!%n",p2.getClass().getSimpleName());
            }
            else if(p2.hasGarlic) {
                p1.attack--;
                p2.hasGarlic = false;
                System.out.printf("%s casts the garlic food, decreasing its opponents attack by 1!%n",p2.getClass().getSimpleName());
            }
            else if(p2.hasApple) {
                p2.health+=2;
                p2.hasMeat = false;
                System.out.printf("%s casts the apple food, increasing its health by 2!%n",p2.getClass().getSimpleName());
            }
            else if(p2.hasMeat) {
                p2.attack++;
                p2.hasMeat = false;
                System.out.printf("%s casts the meat food, increasing its attack by 1!%n",p2.getClass().getSimpleName());
            }
            else if(p2.hasChili) {
                p2.health-=2;
                p2.hasChili = false;
                System.out.printf("%s casts the chili food, decreasing its opponents health by 2!%n",p2.getClass().getSimpleName());
            }
            if(p1.luck > p2.luck) {
                System.out.printf("Player 1's %s has a higher luck attribute than that of player 2's %s. %s will attack first!%n",p1.getClass().getSimpleName(),p2.getClass().getSimpleName(),p1.getClass().getSimpleName());
                p2.health = p2.health-(p1.attack*p1.attackMultiplier);
                System.out.printf("Player 1's %s attacked %s, dealing %.1f damage!%n",p1.getClass().getSimpleName(),p2.getClass().getSimpleName(),p1.attack);
                if(p2.health <= 0) {
                    System.out.println("Player 1 wins this fight!\n");
                    p1Wins++;
                    return 1;
                }
                System.out.printf("Player 2's %s is now attacking!%n",p2.getClass().getSimpleName());
                p1.health = p1.health-(p2.attack*p2.attackMultiplier);
                System.out.printf("Player 2's %s attacked %s, dealing %.1f damage!%n",p2.getClass().getSimpleName(),p1.getClass().getSimpleName(),p2.attack);
                if(p1.health <= 0) {
                    System.out.println("Player 2 wins this fight!\n");
                    p2Wins++;
                    return 2;
                }
            }
            else if(p2.luck > p1.luck) {
                System.out.printf("Player 2's %s has a higher luck attribute than that of player 1's %s. %s will attack first!%n",p2.getClass().getSimpleName(),p1.getClass().getSimpleName(),p2.getClass().getSimpleName());
                p1.health = p1.health-(p2.attack*p2.attackMultiplier);
                System.out.printf("Player 2's %s attacked %s, dealing %.1f damage!%n",p2.getClass().getSimpleName(),p1.getClass().getSimpleName(),p2.attack);
                if(p1.health <= 0) {
                    System.out.println("Player 2 wins this fight!\n");
                    p2Wins++;
                    return 2;
                }
                System.out.printf("Player 1's %s is now attacking!%n",p1.getClass().getSimpleName());
                p2.health = p2.health-(p1.attack*p1.attackMultiplier);
                System.out.printf("Player 1's %s attacked %s, dealing %.1f damage!%n",p1.getClass().getSimpleName(),p2.getClass().getSimpleName(),p1.attack);
                if(p2.health <= 0) {
                    System.out.println("Player 1 wins this fight!\n");
                    p1Wins++;
                    return 1;
                }
            }
            else {
                System.out.println("The two battling pets have equivalent luck attributes, a coin will be flipped to decide who attacks first!");
                if(rand.nextInt(1) == 0) {
                    System.out.printf("Luck is on the player 1's %s's side, it will attack first!%n",p1.getClass().getSimpleName());
                    p2.health = p2.health-(p1.attack*p1.attackMultiplier);
                    System.out.printf("Player 1's %s attacked %s, dealing %.1f damage!%n",p1.getClass().getSimpleName(),p2.getClass().getSimpleName(),p1.attack);
                    if(p2.health <= 0) {
                        System.out.println("Player 1 wins this fight!\n");
                        p1Wins++;
                        return 1;
                    }
                    System.out.printf("Player 2's %s is now attacking!%n",p2.getClass().getSimpleName());
                    p1.health = p1.health-(p2.attack*p2.attackMultiplier);
                    System.out.printf("Player 2's %s attacked %s, dealing %.1f damage!%n",p2.getClass().getSimpleName(),p1.getClass().getSimpleName(),p2.attack);
                    if(p1.health <= 0) {
                        System.out.println("Player 2 wins this fight!\n");
                        p2Wins++;
                        return 2;
                    }
                }
                else if(rand.nextInt(1) == 1) {
                    System.out.printf("Luck is on the player 2's %s's side, it will attack first!%n",p2.getClass().getSimpleName());
                    p1.health = p1.health-(p2.attack*p2.attackMultiplier);
                    System.out.printf("Player 2's %s attacked %s, dealing %.1f damage!%n",p2.getClass().getSimpleName(),p1.getClass().getSimpleName(),p2.attack);
                    if(p1.health <= 0) {
                        System.out.println("Player 2 wins this fight!\n");
                        p2Wins++;
                        return 2;
                    }
                    System.out.printf("Player 1's %s is now attacking!%n",p1.getClass().getSimpleName());
                    p2.health = p2.health-(p1.attack*p1.attackMultiplier);
                    System.out.printf("Player 1's %s attacked %s, dealing %.1f damage!%n",p1.getClass().getSimpleName(),p2.getClass().getSimpleName(),p1.attack);
                    if(p2.health <= 0) {
                        System.out.println("Player 1 wins this fight!\n");
                        p1Wins++;
                        return 1;
                    }
                }
            }

            //Debuffing statements, alleviating one use food castings
            if(p1.attack == 0.0) {
                p1.reset();
            }
            if(p2.attack == 0.0) {
                p2.reset();
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
