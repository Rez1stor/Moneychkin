public interface Magic {
    public abstract void magicAttack(Hero other);
    public enum SpellType{
        FIREBALL(20, 15, 30, 20, "Kula ognia"),
        ICE_SHARD(15, 10, 25, 20, "Odłamek lodu"),
        LIGHTNING(30, 20, 45, 30, "Piorun"),
        ARCANE_BLAST(35, 25, 50, 35, "Eksplozja magii");

        
        private final int baseDamage; // obrażeń podstawowych,
        private final int baseManaCost; // kosztu many wersji podstawowej
        
        private final int specialDamage; // obrażeń specjalnych
        private final int specialManaCost; // kosztu many wersji specjalnej
        
        private final String description; // opisu nazwy zaklęcia.

        SpellType(int baseDamage, int baseManaCost, int specialDamage, int specialManaCost, String description) {
            this.baseDamage = baseDamage;
            this.baseManaCost = baseManaCost;
            this.specialDamage = specialDamage;
            this.specialManaCost = specialManaCost;
            this.description = description;
    };
        public int getBaseDamage(){ return baseDamage;  }
        public int getBaseManaCost(){ return baseManaCost;  }
        public int getSpecialDamage(){ return baseManaCost; }
        public int getSpecialManaCost(){ return baseManaCost; }
        public String getDescription(){ return description; }
    }
    public static SpellType getRandomnSpell(){
        SpellType[] spells = SpellType.values();
        return spells[Hero.rand.nextInt(spells.length)];     
    }
}