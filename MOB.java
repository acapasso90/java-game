public class MOB implements Attributes {
    protected int armor;
    protected int damage;
    protected DiceType damageDie;
    protected int hitModifier;
    protected int maxHP;
    protected final String name;

    public MOB(String name, int hp, int armor, int hitModifier, DiceType damageDie){
        this.name = name;
        this.maxHP = hp;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.damageDie = damageDie;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public void addDamage(int damage){
        this.damage += damage;
    }

    public int getHP(){
        return maxHP - damage;
    }

    public void resetDamage(){
        damage = 0;
    }

    @Override
    public DiceType getDamageDie() {
        return damageDie;
    }

    @Override
    public String toString(){
        return "+============================+\n" +
        String.format("| %-27s|%n", getName()) +
        "|                            |\n" +
        String.format("|         Health: %-10d |%n", getHP())  +
        String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie().toString(), getArmor()) +
        "|                            |\n" +
        "+============================+";
    }
    
    public MOB copy(){
        return new MOB(getName(), getMaxHP(), getArmor(), getHitModifier(), getDamageDie());
    }
    public static void main(String[] args) {
    }
}