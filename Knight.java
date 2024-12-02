public class Knight extends MOB {
    private Fortune activeFortune = null;
    protected final int id;
    protected int xp;

    public Knight(int id, String name, int hp, int armor, int hitModifier, DiceType damageDie, int xp){
        super(name, hp, armor, hitModifier, damageDie);
        this.id = id;
        this.xp = xp;
    }

    @Override
    public int getArmor() {
        if (getActiveFortune() != null){
            return activeFortune.getArmor() + super.getArmor();
        }
        return super.getArmor();
    }

    public Fortune getActiveFortune() {
        
        return activeFortune;
    }

    @Override
    public int getMaxHP() {
        if (getActiveFortune() != null){
            return activeFortune.getMaxHP() + super.getHP();
        }
        return super.getHP();
    }

    
    @Override
    public DiceType getDamageDie() {
        if (getActiveFortune() != null && activeFortune.getDamageDie() != null){
            return activeFortune.getDamageDie();
        }
        return super.getDamageDie();
    }

    @Override
    public int getHitModifier() {
        if (getActiveFortune() != null){
            return activeFortune.getHitModifier() + super.getHitModifier();
        }
        return super.getHitModifier();
    }

    public int getXP() {
        return xp;
    }

    public void setActiveFortune(Fortune activeFortune){
        this.activeFortune = activeFortune;
    }

    public void addXP(int xp){
        this.xp += xp;
    }

    public Integer getId(){
        return id;
    }

    @Override 
    public String toString(){
        return "+============================+\n" +
        String.format("| %-27s|%n", getName()) +
        String.format("| id: %-23d|%n", getId()) +
        "|                            |\n" +
        String.format("| Health: %-6d  XP: %-7d|%n", getHP(), getXP())  +
        String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie().toString(), getArmor()) +
        "|                            |\n" +
        "+============================+";
    }

    public String toCSV(){
        return(super.getName() + ',' + getMaxHP() + ',' + getArmor() + ',' + getHitModifier() + ',' + getDamageDie() + ',' + getXP());
    }
    public static void main(String[] args) {
    }
    
}