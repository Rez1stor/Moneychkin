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

    public String getName() {
        return name;
    }

    protected int getHealth() {
        return Health;
    }

    protected int getMaxHealth() {
        return maxHealth;
    }

    protected int getStrength() {
        return strength;
    }

    public boolean isAlive() {
        return Health > 0;
    }

    protected int getExperience() {
        return experience;
    }

    protected void printExperience() {
        System.out.println("Experience: " + experience);
    }
    
    protected void setExperience(int exp){
        this.experience = exp;
    }

    protected void addExperience(int exp){
        this.experience += exp;
    }
    
    public abstract void atak(Hero other);

    protected abstract void baseAttack(Hero other);

    protected abstract void specialAttack(Hero other);

    protected void takeDamage(int damage){
        this.Health -= damage;
    }

    protected abstract boolean dodge();

    public void rest(){
        if (this.Health + this.maxHealth * 0.3 > this.maxHealth) this.Health = this.maxHealth;
        else this.Health += this.maxHealth * 0.3;
    }

    protected void heal(int healPoints){
        this.Health += healPoints;
    }
    @Override
    public String toString(){
        return "Hero: " + name + " | Health: " + Health + "/" + maxHealth + " | Strength: " + strength + " | Experience: " + experience;
    }
}

class Warrior extends Hero {

    public Warrior(String name, int maxHealth, int maxStrength) {
        super(name, maxHealth, maxStrength);
    }

    public static Warrior generateRandomWarrior(){
        String name = names[rand.nextInt(names.length)];
        int maxHealth = rand.nextInt(50, 100); 
        int strength = rand.nextInt(20, 40);   
        return new Warrior(name, maxHealth, strength);
    }
    @Override
    public void atak(Hero other){
        if(isAlive()==false){ System.out.println("Warrior" + this.getName() + "nie może zaatakować, bo jest martwy!"); return;}
        int atack = rand.nextInt(10);
        if(atack < 7) baseAttack(other);
        else if(atack == 7 || atack == 8) specialAttack(other);
        else System.out.println("Atak nie udal się");
    }
    @Override
    protected void baseAttack(Hero other) {
        int damage = this.getStrength() + rand.nextInt(6);
        System.out.println(String.format("Warrior %s atakuje Warrior %s z siłą %d", this.getName(), other.getName(), damage));
        other.takeDamage(damage);
        addExperience(damage);
    }
    @Override
    protected void specialAttack(Hero other) {
        int damage = (int) Math.round((this.getStrength()) * 1.5);
        System.out.println(String.format("Warrior %s atakuje Warrior %s z siłą %d", this.getName(), other.getName(), damage));
        other.takeDamage(damage);
        addExperience(damage);
    }
    @Override
    protected boolean dodge(){
        if (rand.nextInt(10) == 1){
            System.out.printf("Warrior %s unika obrażeń", this.getName());
            return true;
        } else return false;
    }
    @Override    
    public String toString(){
        return String.format("Warrior %s [HP: %d/%d, STR: %d, XP: %d]", getName(), getHealth(), getMaxHealth(), getStrength(), getExperience());
    }
}

interface Magic {
    
    public int spell = 40;
    public abstract void magicAttack(Hero other);
}

class Wizard extends Hero implements Magic{
    private int mana;
    private int maxMana;
    
    public Wizard(String name, int maxHealth, int maxStrength, int maxMana){
        super(name, maxHealth, maxStrength);
        this.mana = maxMana;
        this.maxMana = maxMana;
    }

    public static Wizard generateRandomWizard(){
        String name = names[rand.nextInt(names.length)];
        int maxHealth = rand.nextInt(50, 100); 
        int strength = rand.nextInt(5, 20);   
        int mana = rand.nextInt(60, 120);
        return new Wizard(name, maxHealth, strength, mana);
    }
    
    protected int getMana(){
        return mana;
    }
    
    protected int getMaxMana(){
        return maxMana;
    }
    
    protected void castSpell(int cost){
        mana -= cost;
    }

    protected boolean canUseSpell(int cost){
        return getMana() - cost >= 0;
    }
    @Override    
    public void atak(Hero other){
        if(isAlive()==false){ System.out.println("Wizard" + getName() + "nie może zaatakować, bo jest martwy!"); return;}
        int attack = rand.nextInt(10);
        if (attack < 6) magicAttack (other);
        else if (attack == 6 || attack == 7) specialAttack(other);
        else if (attack == 8) baseAttack(other);
        else dodge();
    }
    @Override
    protected void baseAttack(Hero other){
        int damage = getStrength() + rand.nextInt(5);
        System.out.println(String.format("Wizard %s atakuje Warrior %s z siłą %d", this.getName(), other.getName(), damage));
        other.takeDamage(damage);
        addExperience(damage);
    }
    @Override    
    protected void specialAttack(Hero other){
        if (!canUseSpell(30)) magicAttack(other);
        else {
            int damage = (int)(spell * 1.5) + rand.nextInt(10);
            mana-=30;
            System.out.printf("Wizard %s przywoluje potezne zaklecie blyskawicy na %s %s i zadaje %d obrazen!\n", this.getName(), other.getClass().getSimpleName(), other.getName(), damage);
            other.takeDamage(damage);
            addExperience(damage);
        }
    }
    @Override    
    public void magicAttack(Hero other){
        if (!canUseSpell(15)) {
            baseAttack(other);
             mana+=getMaxMana()/4;
        } else {
            castSpell(15);
            System.out.printf("Wizard %s rzuca czar ognia na %s %s z moca %d\n", this.getName(), other.getClass().getSimpleName(), other.getName(), spell);            
            other.takeDamage(spell);
            addExperience(spell);
        }
    }
    @Override
    protected boolean dodge(){
        if (rand.nextInt(4) == 0){
            System.out.println("Mag teleportuje się i unika ataku");
            return true;
        } else return false;
    }

    public void regenerateMana(int points){
        int added_p;
        if (mana + points > getMaxMana()) added_p = getMaxMana() - getMana();
        else added_p = points;
        mana += added_p;
        System.out.printf("`Wizard %s regeneruje mane o %d punktow", getName(), added_p);
    }
    @Override
    public void rest(){
        if (mana + getMaxMana()/3 > getMaxMana()) mana=getMaxMana();
        else mana += getMaxMana()/3; 
        super.rest();
    }
    @Override
    public String toString(){
        return String.format("Wiz %s [HP: %d/%d, STR: %d, XP: %d] [Mana: %d/%d]", getName(), getHealth(), getMaxHealth(), getStrength(), getExperience(), getMana(), getMaxMana());
    }
}




