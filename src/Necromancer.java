public class Necromancer extends Wizard {
    
    private class Skeleton{
        private int health;
        private int maxHealth;
        private int strength;

        public Skeleton(int maxHealth, int strength){
            this.health = maxHealth;
            this.maxHealth = maxHealth;
            this.strength = strength;
        }

        public boolean isAlive(){ return health > 0;}
        public boolean isWounded(){ return health < maxHealth;}

        public void attack(Hero enemy){
            System.out.printf("Szkielet %s atakuje %s %s i zadaje %d obrażeń!\n", getName(), enemy.getClass().getSimpleName(), enemy.getName(), strength);
            enemy.takeDamage(strength);
        }

        public void takeDamage(int damage){
            health -= damage;
            if (isAlive()){
                System.out.printf("Szkielet (Necromancer %s) otrzymał %d obrażeń i nadal żyje.\n", getName(), damage);
            }else{
                System.out.printf("Szkielet (Necromancer %s) otrzymał %d obrażeń i zmarł.\n", getName(), damage);
                skeleton = null;
            }
        }

        public void heal(){this.health = this.maxHealth;}

        public String toString(){
            return String.format("[Skeleton(HP: %d/%d STR: %d)]", this.health, this.maxHealth, this.strength);
        }

    }

    private Skeleton skeleton;

    protected Skeleton callSkeleton(){
        if (skeleton == null){
            System.out.println("Nekromanta wywołuje swojego Skeletona z pod ziemi");
            return new Skeleton((int)Math.round(getHealth()*0.3), getStrength()*2);
        } else if (skeleton.isWounded()){
            System.out.println("Nekromanta regeneruje swojego Skeletona:");
            skeleton.heal();
            return skeleton;
        } else { 
            System.out.println("Skeleton nie potrzebuje regeneracji"); 
            return skeleton;
        }
    }
    
    public Necromancer(String name, int maxHealth, int maxStrength, int maxMana){
        super(name, maxHealth, maxStrength, maxMana);
        skeleton = callSkeleton();
    }

    public static Necromancer generateRandomNecromancer(){
        return new Necromancer(names[rand.nextInt(names.length)], 
            rand.nextInt(60, 111), 
                rand.nextInt(5, 21), 
                     rand.nextInt(80, 151));
    }

    @Override 
    protected void baseAttack(Hero other){
        if (skeleton != null){ skeleton.attack(other);}
        else {super.baseAttack(other);}
    }

    @Override
    protected void takeDamage(int damage){
        if (skeleton != null){ skeleton.takeDamage(damage);}
        else {super.takeDamage(damage);}
    }
    
    @Override
    protected boolean dodge(){
        if (rand.nextInt(100) < 15){
            System.out.println("Mag teleportuje się i unika ataku");
            return true;
        } else return false;
    }

    @Override
    public void rest(){
        super.rest();
        skeleton = callSkeleton();
    }

    @Override
    public String toString(){
        return String.format("Necromancer %s [HP: %d/%d STR: %d XP: %d Mana: %d/%d] %s",
            getName(), getHealth(), getMaxHealth(), getStrength(), getExperience(), getMana(), getMaxMana(), 
            ((skeleton != null) ? skeleton.toString() : "[Skeleton(Brak)]"));
        }
}
