public class Beaver extends Pet {
    Beaver() {
        this.health = 2.0;
        this.attack = 3.0;
        this.shield = 1.0;
        this.habitat = "water";
        this.attackMultiplier = 1.0;
        this.level = 1;
    }

    @Override 
    public void upgrade() {
        if(level == 1) {
            this.health = 3.0;
            this.attack = 4.0;
        }
        else if(level == 2) {
            this.health = 4.0;
            this.attack = 5.0;
            this.shield = 2.0;
        }
        else {
            System.out.println("Pet cannot be upgraded, maximum level achieved.");
        }
    }

    @Override
    public void useSpecial(Pet p2) {
        this.shield++;
        p2.shield++;
    }
}
