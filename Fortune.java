public class Fortune implements Attributes {
    private int armor;
    private String name;
    private int hpBonus;
    private int hitModifier;
    private DiceType type;

    public Fortune(String name, int hpBonus, int armor, int hitModifier) {
        this(name, hpBonus, armor, hitModifier, null);
    }

    public Fortune(String name, int hpBonus, int armor, int hitModifier, DiceType type) {
        this.name = name;
        this.armor = armor;
        this.hpBonus = hpBonus;
        this.hitModifier = hitModifier;
        this.type = type;
    }

    @Override
    public int getArmor() {
        return armor;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public int getMaxHP() {
        return hpBonus;
    }

    @Override
    public DiceType getDamageDie() {
        return type;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    @Override
    public String toString(){
        return "+======================+\n" +
        String.format("|%-22s|%n", getName()) +
        String.format("|HP Bonus: %12s|%n", "+" + getMaxHP()) +
        String.format("|AC Bonus: %12s|%n", "+" + getArmor()) +
        String.format("|Hit Bonus: %11s|%n", "+" + getHitModifier()) +
        String.format("|Damage Adj: %10s|%n", getDamageDie() != null ? getDamageDie() : "-") +
        "+======================+";
    }

    public static void main(String[] args) {
    }
    
}