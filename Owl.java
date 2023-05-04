public class Owl extends Pet {
    Owl() {
        this.health = 5.0;
        this.attack = 3.0;
        this.habitat = "air";
        this.attackMultiplier = 1.0;
        this.level = 1;
        this.luck = 5;
    }

    @Override 
    public void upgrade() {
        if(level == 1) {
            this.health = 6.0;
            this.attack = 5.0;
            level++;
        }
        else if(level == 2) {
            this.health = 7.0;
            this.attack = 6.0;
            level++;
        }
        else {
            System.out.println("Pet cannot be upgraded, maximum level achieved. You will now lose this turn.");
        }
    }

    @Override
    public void useSpecial(Pet p2) {
        this.health--;
        p2.health = p2.attack+p2.attack;
    }
    
    @Override
    public void reset() {
        this.attack = 6.0;
    }
}
