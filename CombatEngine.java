import java.util.Random;
import java.util.List;

public class CombatEngine {
    private final GameData data;
    private final DiceSet dice = new DiceSet();
    private final Random rnd = new Random();
    private final GameView view;

    public CombatEngine(GameData data, GameView view){
        this.data = data;
        this.view = view;
    }

    public void initialize(){
        for(Knight knight: data.getActiveKnights()){
            knight.setActiveFortune(data.getRandomFortune());
        }
        view.printFortunes(data.getActiveKnights());
    }

    public void runCombat(){
        List<MOB> randomMonsters = data.getRandomMonsters();
        view.printBattleText(randomMonsters, data.getActiveKnights());
        while (!data.getActiveKnights().isEmpty() && !randomMonsters.isEmpty()){
            knightAttack(data.getActiveKnights(), randomMonsters);
            mobAttack(randomMonsters, data.getActiveKnights());
        }

        if (data.getActiveKnights().isEmpty()){
            view.printDefeated();
        }
        else {
            if(view.checkContinue()){
                runCombat();
            }
        }
    }

    private void knightAttack(List<Knight> attackers, List<MOB> defenders){
        for(Knight knight: attackers){
            if (defenders.isEmpty()){
                break;
            }
            MOB defender = defenders.get(rnd.nextInt(defenders.size()));
            int roll = dice.roll(DiceType.D20);
            if (roll + knight.getHitModifier() > defender.getArmor()){
                int dmg = dice.roll(knight.getDamageDie());

                defender.addDamage(dmg);
                System.out.println(knight.getName() + " hit " + defender.getName() + " for " +  dmg + " damage!");

                if (defender.getHP() <= 0 ){
                    view.printBattleText(defender);
                    defenders.remove(defender);
                    for(Knight active: attackers){
                        active.addXP(1);
                    }
                    if (defenders.isEmpty()){
                        break;
                    }
                }
            }
        }
    }

    private void mobAttack(List<MOB> attackers, List<Knight> defenders){
        for(MOB mob: attackers){
            if (defenders.isEmpty()){
                break;
            }
            Knight defender = defenders.get(rnd.nextInt(defenders.size()));
            int roll = dice.roll(DiceType.D20);
            if (roll + mob.getHitModifier() > defender.getArmor()){
                int dmg = dice.roll(mob.getDamageDie());
                defender.addDamage(dmg);
                System.out.println(mob.getName() + " hit " + defender.getName() + " for " +  dmg + " damage!");

                if (defender.getHP() <= 0 ){
                    view.printBattleText(defender);
                    data.removeActive(defender);
                    if(defenders.isEmpty()){
                        break;
                    }
                }
            }
        }
    }

    public void clear(){
        for(Knight knight: data.getKnights()){
            knight.setActiveFortune(null);
        }
    }
}
