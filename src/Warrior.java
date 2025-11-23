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
        System.out.println(String.format("Warrior %s atakuje %s %s z siłą %d", this.getName(), other.getClass().getSimpleName(), other.getName(), damage));
        other.takeDamage(damage);
        addExperience(damage);
    }
    @Override
    protected void specialAttack(Hero other) {
        int damage = (int) Math.round((this.getStrength()) * 1.5);
        System.out.println(String.format("Warrior %s atakuje %s %s z siłą %d", this.getName(), other.getClass().getSimpleName(), other.getName(), damage));
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