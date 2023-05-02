public abstract class Pet {
    public double health;
    public double attack;
    public double shield;
    //Land, air, water, garden
    //Land trumps water, air trumps land, garden trumps air, water trumps garden
    public String habitat;
    public double attackMultiplier;
    public int level;
    public int luck;

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
            attackMultiplier = 1.0;
            p2.attackMultiplier = 1.0;
        }
    }

    public abstract void upgrade();
    //Garden adds 1 attack point to itself and another pet
    //Water adds 1 luck point to itself and another pet
    //Land adds 1 health point to itself and another pet
    //Air substracts 1 health point from the pet casting its special while doubling the attack of another pet
    public abstract void useSpecial(Pet p2);
    
    //public abstract void attack();

    @Override
    public String toString() {
        return String.format("%s:%n %-10s %-10s%n %-10s %-10s%n %-10s %-10s%n %-10s %-10s%n %-10s %-10s%n",getName(), "Tier:", level, "Attack:", attack, "Luck:", luck, "Health:", health, "Habitat:", habitat);
        //return String.format("%s is of tier %d status, has %.1f attack, %.1f shield, %.1f health, and is of the %s habitat type.",getName(), level, attack, shield, health, habitat);
    }
}
