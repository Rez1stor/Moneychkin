import java.util.Random;

public abstract class Hero {
    protected static Random rand = new Random();
    protected static String[] names = {"Thorin", "Aragorn", "Leonidas", "Conan", "Xena", "Gandalf", "Merlin", "Saruman", "Elminster", "Raistlin"};

    private final String name;
    private final int maxHealth;
    private int Health;
    private final int strength;
    private int experience;

    public Hero(String name, int maxHealth, int strength) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.Health = maxHealth;
        this.strength = strength;
        this.experience = 0;
    }

    public String getName(){ return name;}

    protected int getHealth(){ return Health;}

    protected int getMaxHealth(){ return maxHealth;}

    protected int getStrength(){ return strength;}

    public boolean isAlive(){ return Health > 0;}

    protected int getExperience(){ return experience;}

    protected void printExperience(){ System.out.println("Experience: " + experience);}
    
    protected void setExperience(int exp){ this.experience = exp;}

    protected void addExperience(int exp){ this.experience += exp;}
    
    public abstract void atak(Hero other);

    protected abstract void baseAttack(Hero other);

    protected abstract void specialAttack(Hero other);

    protected void takeDamage(int damage){ this.Health -= damage;}

    protected abstract boolean dodge();

    public void rest(){
        if (this.Health + this.maxHealth * 0.3 > this.maxHealth) this.Health = this.maxHealth;
        else this.Health += this.maxHealth * 0.3;
    }

    protected void heal(int healPoints){ this.Health += healPoints;}
    
    @Override
    public String toString(){
        return "Hero: " + name + " | Health: " + Health + "/" + maxHealth + " | Strength: " + strength + " | Experience: " + experience;
    }
}