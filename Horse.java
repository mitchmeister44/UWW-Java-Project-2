public class Horse extends Pet {
    Horse() {
        this.health = 1.0;
        this.attack = 2.0;
        this.shield = 2.0;
        this.habitat = "land";
        this.attackMultiplier = 1.0;
        this.level = 1;
    }

    @Override 
    public void upgrade() {
        if(level == 1) {
            this.health = 2.0;
            this.attack = 4.0;
        }
        else if(level == 2) {
            this.health = 4.0;
            this.attack = 5.0;
            this.shield = 3.0;
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
