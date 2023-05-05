import java.util.*;
//Mitchell's segment of work
public class Player {
    //Pet deck (player's lineup) and pet shop will behave as separate linked lists
    //Other variables are self-explanatory
    public LinkedList<Pet> petDeck;
    private LinkedList<Pet> petShop;
    private int numTurn;
    private int goldCurrency;
    private int numFood;
    private int numPlayer;
    private LinkedList<Pet> allPetsArr;
    Scanner input= new Scanner(System.in);
    //Player class is initialized with its number, 3 randomized an non-duplicate pets, and other corresponding variables
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
        //Base pet list to select from for starting pets
        Pet[] basePetArr = new Pet[]{new Ant(), new Beaver(), new Cricket(), new Duck(), new Horse()};
        //Pet list to display for pet buying in game
        allPetsArr = new LinkedList<Pet>();
        allPetsArr.add(new Ant());
        allPetsArr.add(new Beaver());
        allPetsArr.add(new Cricket());
        allPetsArr.add(new Duck());
        allPetsArr.add(new Horse());
        allPetsArr.add(new Fish());
        allPetsArr.add(new Owl());
        allPetsArr.add(new Dog());
        this.numTurn = 1;
        //this.goldCurrency = 10;
        this.numFood = 0;
        this.petDeck = new LinkedList<Pet>();
        //Added pets according to 3 unique randomly generated values
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
    //Method called numerously to display pets, also uses collections to sort by attack
    public void displayPets() {
        Collections.sort(petDeck);
        System.out.printf("Player %d's pet deck:%n",numPlayer);
        for(int i = 0; i < petDeck.size(); i++) {
            System.out.println(petDeck.get(i).toString());
        }
    }
    //Upgrade pets method
    public void upgradePets(Scanner input){
        displayPets();
        int userChoice;
        //initiate temp pet
        System.out.println("Which pet would you like to upgrade a tier");
        while(true){
            try {
                userChoice=input.nextInt();
                //Ensures that user input is applicable given pet deck size
                if (userChoice>=0 && userChoice<=petDeck.size()){
                    petDeck.get(userChoice).upgrade();
                    break;
                }
                else {
                    throw new Exception();
                }
            } catch (InputMismatchException e){
                System.out.println("You must input a number that corrisponds to one of your pets");
                input.nextLine();
            }
            catch(Exception e) {
                System.out.println("Please input an integer from 0 (corresponding to your first pet) to a number that corresponds to your final pet in your deck (4 for 5th pet, 3 for 4th, etc.)");
                input.nextLine();

            }

        }
    }
    //Attack method used for pet battles
    public int attack(int p1Wins, int p2Wins, Pet p1, Pet p2) {
        while(true) {
            Random rand = new Random();
            //Casts pet foods if either pet posseses one or multiple
            if(p1.foodData[0]) {
                p2.attack = 0.0;
                p1.hasCoco = false;
                System.out.printf("%s casts the coconut food, setting its opponents attack to 0 for this turn!",p1.getClass().getSimpleName());
            }
            else if(p1.foodData[1]) {
                p2.luck--;
                p1.hasCherry = false;
                System.out.printf("%s casts the cherry food, decreasing its opponents luck by one point!%n",p1.getClass().getSimpleName());
            }
            else if(p1.foodData[2]) {
                p2.attack--;
                p1.hasGarlic = false;
                System.out.printf("%s casts the garlic food, decreasing its opponents attack by 1!%n",p1.getClass().getSimpleName());
            }
            else if(p1.foodData[3]) {
                p1.health+=2;
                p1.hasApple = false;
                System.out.printf("%s casts the apple food, increasing its health by 2!%n",p1.getClass().getSimpleName());
            }
            else if(p1.foodData[4]) {
                p1.attack++;
                p1.hasMeat = false;
                System.out.printf("%s casts the meat food, increasing its attack by 1!%n",p1.getClass().getSimpleName());
            }
            else if(p1.foodData[5]) {
                p2.health-=2;
                p1.hasChili = false;
                System.out.printf("%s casts the chili food, decreasing its opponents health by 2!%n",p1.getClass().getSimpleName());
            }
            if(p2.foodData[0]) {
                p1.attack = 0.0;
                p2.hasCoco = false;
                System.out.printf("%s casts the coconut food, setting its opponents attack to 0 for this turn!",p2.getClass().getSimpleName());
            }
            else if(p2.foodData[1]) {
                p1.luck--;
                p2.hasCherry = false;
                System.out.printf("%s casts the cherry food, decreasing its opponents luck by one point!%n",p2.getClass().getSimpleName());
            }
            else if(p2.foodData[2]) {
                p1.attack--;
                p2.hasGarlic = false;
                System.out.printf("%s casts the garlic food, decreasing its opponents attack by 1!%n",p2.getClass().getSimpleName());
            }
            else if(p2.foodData[3]) {
                p2.health+=2;
                p2.hasMeat = false;
                System.out.printf("%s casts the apple food, increasing its health by 2!%n",p2.getClass().getSimpleName());
            }
            else if(p2.foodData[4]) {
                p2.attack++;
                p2.hasMeat = false;
                System.out.printf("%s casts the meat food, increasing its attack by 1!%n",p2.getClass().getSimpleName());
            }
            else if(p2.foodData[5]) {
                p2.health-=2;
                p2.hasChili = false;
                System.out.printf("%s casts the chili food, decreasing its opponents health by 2!%n",p2.getClass().getSimpleName());
            }
            //Case 1, pet 1's luck eclipses pet 2's
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
            //Case 2, pet 2's luck eclipses pet 1's
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
            //Case 3, pets share an equivalent luck attribute, computer flips a coin to decide (50/50 chance)
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
    //Pet purchasing method
    public void buyPets(){
        /*
        for(int i = 0; i < allPetsArr.size(); i++) {
        if(i < 3) {
        if(petDeck.get(i).getClass().getSimpleName() == allPetsArr.get(i).getClass().getSimpleName()){
        allPetsArr.remove(i);
        }
        }
        if(i >= 3) {
        for(int j = 0; j < petDeck.size(); j++) {
        if(petDeck.get(j).getClass().getSimpleName() == allPetsArr.get(i).getClass().getSimpleName()){
        allPetsArr.remove(i);
        }
        }
        }
        }*/
        //Sourced from created array above
        System.out.println("Here are all pets available for purchase:");
        for(int i = 0; i < allPetsArr.size(); i++) {
            System.out.println(allPetsArr.get(i).toString());
        }
        System.out.println("Which one would you like to purchase? (enter '0' for the first and so on)");
        while(true){
            try{
                int petChoice = input.nextInt();
                //Ensures that user response is applicable given pet array size
                if(petChoice >= 0 && petChoice <= allPetsArr.size()-1){
                    petDeck.add(allPetsArr.get(petChoice));
                    break;
                }
                else{
                    throw new Exception();
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid input entered, please ensure your entered value is numerical and within the specified range.");
                input.nextLine();
            }
            catch(Exception e) {
                System.out.println("Invalid input, value outside specified range.");
                input.nextLine();
            }
        }
    }
    //Food shop display and purchase method
    public void shop(Scanner input){
        //Random value to randomize food selection (2 random foods each time method is called)
        Random rand = new Random();
        int returnValue;
        String[] foodItems= new String[6];
        foodItems[0]= "Coconut: a temporary shield that fully blocks 1 attack";
        foodItems[1]= "Cherry: a perk that grants increased luck";
        foodItems[2]= "Garlic: a shield that blocks 1 from damage each attack";
        foodItems[3]= "Apple: a buff that grants your pet plus 2 health for each fight";
        foodItems[4]= "Meat: a buff that grants your pet plus 2 attack for each fight";
        foodItems[5]= "Chili: a debuff that decreases enemy health by 2 points";
        int item1 = rand.nextInt(6);
        int item2= rand.nextInt(6);
        System.out.printf("Please choose the item you would like ('1' or '2'):%n%s%n%s",foodItems[item1], foodItems[item2]);

        while(true){
            try {
                int foodChoice= input.nextInt(); 
                //Sets user response equivalent to boolean index (created in pets class)
                if (foodChoice==1){
                    returnValue= item1;
                    break;
                } else if (foodChoice==2){
                    returnValue = item2;
                    break;
                }
            } catch (InputMismatchException e){
                System.out.println("You must input either a '1' or a '2'");
            }
        }
        System.out.println("Which pet would you like to apply the food to? ('0' for the first pet and added values for suceeding pets)");
        displayPets();
        int userChoice = input.nextInt();
        while(true){
            try {
                userChoice=input.nextInt();
                //Adds the food, given the user's response, to the pet in question
                if (userChoice>=0 && userChoice<=petDeck.size()-1){
                    (petDeck.get(userChoice)).foodData[returnValue] = true;
                    System.out.printf("Added %s, to the %s pet.%n",foodItems[returnValue],petDeck.get(userChoice).getClass().getSimpleName());
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
}
