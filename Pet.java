public abstract class Pet implements Comparable<Pet> {
    public double health;
    public double attack;
    public double shield;
    public String habitat;
    public double attackMultiplier;
    public int level;
    public int luck;

    //Boolean array, used to assign foods to pets (pet cannot possess more than one of the same food type)
    boolean[] foodData= new boolean[] {false, false, false, false, false,false};
    //Sets the enemy pets attack to 0 for one turn
    public boolean hasCoco= foodData[0];
    //Decreases the enemy pets luck
    public boolean hasCherry= foodData[1];
    //Decreases enemy attack by 1
    public boolean hasGarlic= foodData[2];
    //Increases friendly health by 2
    public boolean hasApple= foodData[3];
    //Increases friendly attack by 1
    public boolean hasMeat= foodData[4];
    //Decreases enemy health by 2
    public boolean hasChili= foodData[5];

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public double getAttackMultiplier() {
        return this.attackMultiplier;
    }

    public double getAttack() {
        return this.attack;
    }

    public double getHealth() {
        return this.health;
    }

    public int getLuck() {
        return this.luck;
    }

    public void setAttackMultiplier(Pet p2) {
        if((this.habitat == "water" && p2.habitat == "land") || (this.habitat == "land" && p2.habitat == "air") || (this.habitat == "air" && p2.habitat == "garden") || (this.habitat == "garden" && p2.habitat == "water")) {
            attackMultiplier = 0.5;
            p2.attackMultiplier = 2.0;
        }
        else if ((this.habitat == "land" && p2.habitat == "water") || (this.habitat == "air" && p2.habitat == "land") || (this.habitat == "garden" && p2.habitat == "air") || (this.habitat == "water" && p2.habitat == "garden")) {
            attackMultiplier = 2.0;
            p2.attackMultiplier = 0.5;
        }
        else if (this.habitat == p2.habitat) {
            attackMultiplier = attackMultiplier;
            p2.attackMultiplier = p2.attackMultiplier;
        }
    }

    public abstract void upgrade();
    //Garden adds 1 attack point to itself and another pet
    //Water adds 1 luck point to itself and another pet
    //Land adds 1 health point to itself and another pet
    //Air substracts 1 health point from the pet casting its special while doubling the attack of another pet
    public abstract void useSpecial(Pet p2);

    public abstract void reset();
    //Comparator creation (used to sort pets by attack when displaying pet decks
    @Override
    public int compareTo(Pet p) {
        if(attack > p.attack) {
            return -1;
        }
        else if (attack == p.attack) {
            return 0;
        }
        else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return String.format("%s:%n %-10s %-10s%n %-10s %-10s%n %-10s %-10s%n %-10s %-10s%n %-10s %-10s%n",getName(), "Tier:", level, "Attack:", attack, "Luck:", luck, "Health:", health, "Habitat:", habitat);
        //return String.format("%s is of tier %d status, has %.1f attack, %.1f shield, %.1f health, and is of the %s habitat type.",getName(), level, attack, shield, health, habitat);
    }
}
