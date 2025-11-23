class Wizard extends Hero implements Magic{
    private int mana;
    private final int maxMana;
    private final SpellType spell = spell();
    
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
    
    protected void castSPELL(int cost){
        mana -= cost;
    }

    protected boolean canUseSPELL(int cost){
        return getMana() - cost >= 0;
    }
    @Override    
    public void atak(Hero other){
        if(isAlive()==false){ System.out.println(this.getClass().getName()+ " " + getName() + " nie może zaatakować, bo jest martwy!"); return;}
        int attack = rand.nextInt(10);
        if (attack < 6) magicAttack (other);
        else if (attack == 6 || attack == 7) specialAttack(other);
        else if (attack == 8) baseAttack(other);
        else dodge();
    }
    @Override
    protected void baseAttack(Hero other){
        int damage = getStrength() + rand.nextInt(5);
        System.out.println(String.format("%s %s atakuje Warrior %s z siłą %d",this.getClass().getName(), this.getName(), other.getName(), damage));
        other.takeDamage(damage);
        addExperience(damage);
    }
    @Override    
    protected void specialAttack(Hero other){
        if (!canUseSPELL(spell.getSpecialManaCost())) magicAttack(other);
        else {
            int damage = (int)(spell.getSpecialDamage() * 1.5);
            mana-=spell.getSpecialManaCost();
            System.out.printf("%s %s przywoluje potezne zaklecie %s na %s %s i zadaje %d obrazen!\n",this.getClass().getName(), this.getName(), spell.getDescription(), other.getClass().getSimpleName(), other.getName(), damage);
            other.takeDamage(damage);
            addExperience(damage);
        }
    }
    @Override    
    public void magicAttack(Hero other){
        if (!canUseSPELL(spell.getBaseManaCost())) {
            baseAttack(other);
             mana+=getMaxMana()/4;
        } else {
            castSPELL(spell.getBaseManaCost());
            System.out.printf("%s %s rzuca czar ognia na %s %s z %s\n",this.getClass().getName(), this.getName(), other.getClass().getSimpleName(), other.getName(), spell.getDescription());            
            other.takeDamage(spell.getBaseDamage());
            addExperience(spell.getBaseDamage());
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
        System.out.printf("Wizard %s regeneruje mane o %d punktow", getName(), added_p);
    }
    @Override
    public void rest(){
        if (mana + getMaxMana()/3 > getMaxMana()) mana=getMaxMana();
        else mana += getMaxMana()/3; 
        super.rest();
    }
    @Override
    public String toString(){
        return String.format("Wizard %s [HP: %d/%d, STR: %d, XP: %d] [Mana: %d/%d]", getName(), getHealth(), getMaxHealth(), getStrength(), getExperience(), getMana(), getMaxMana());
    }

    protected SpellType spell(){
        return Magic.getRandomnSpell();
    }
}




