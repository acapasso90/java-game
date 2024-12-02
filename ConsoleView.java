import java.util.Scanner;
import java.util.List;

public class ConsoleView implements GameView {
    private final Scanner in = new Scanner(System.in);

    public ConsoleView(){
    };

    public void splashScreen(){
        // TODO - update splash screen. 
        System.out.println("Game beginning!");
    }

    public void endGame(){
        System.out.println("Thank you for playing!");
    }

    public String displayMainMenu(){
        System.out.println("What would you like to do?");
        return in.nextLine();
    }

    public void printHelp(){
        System.out.println("Unsure what to do, here are some options:");
        System.out.println("\tls or list all  - listing the knights");
        System.out.println("\tlist active  - list the active knights knights only");
        System.out.println("\tshow name or id - show the knight details card");
        System.out.println("\tset active name or id - set knight as active (note: only 4 knights can be active)");
        System.out.println("\tremove active name or id - remove a knight from active status (heals knight)");
        System.out.println("\texplore or adventure or quest - find random monsters to fight");
        System.out.println("\tsave filename - save the game to the file name (default: saveData.csv)");
        System.out.println("\texit or goodbye - to leave the game");
        System.out.println("");
        System.out.println("Game rules: You can have four active knights. As long as they are active, they won't heal, but they can gain XP by going on adventures.");
        System.out.println("When you make a knight inactive, they will heal. How many monsters can you defeat before, you have to heal?");
        System.out.println("");

    }

    public void listKnights(List<Knight> knights){
        if (!knights.isEmpty()){
            for(Knight knight: knights){
                System.out.println(knight.getId() + ": " + knight.getName());
            }
        }
        else {
            System.out.println("No knights to list");
        }
    }

    public void knightNotFound(){
        System.out.println("Knight not found!");
    }

    public void showKnight(Knight knight){
        System.out.println(knight.toString());
        System.out.println();

    }

    public void setActiveFailed(){
        System.out.println("Unable to seet active knight. Only four can be active at a time.");
        System.out.println();
    }

    public void printBattleText(List<MOB> monsters, List<Knight> activeKnights){
        System.out.println("");
        System.out.println("Our heroes come across the following monsters. Prepare for battle!");
        System.out.printf("%-50s %-50s\n", "Knights", "Foes");
        for (int i = 0; i < activeKnights.size(); i++){
            if (monsters.size() > i){
                System.out.printf("%-50s %-50s\n",  activeKnights.get(i).getName(), monsters.get(i).getName());
            }
            else {
                System.out.println(activeKnights.get(i).getName());
            }
        }
        System.out.println("");

    }

    public void printBattleText(MOB dead){
        System.out.println(dead.getName() + " was defeated!");
    }


    public void printFortunes(List<Knight> activeKnights){
        for(Knight knight: activeKnights){
            Fortune activeFortune = knight.getActiveFortune();
            System.out.println(knight.getName() + " drew");
            if (activeFortune != null){
                System.out.println(activeFortune.toString());
            }
        }
    }

    public boolean checkContinue(){
        System.out.println("Would you like to continue on your quest? (y/n)?");
        return in.next().equalsIgnoreCase("y") || in.next().equalsIgnoreCase("yes");
        
    }

    public void printDefeated(){
        System.out.println("All active knights have been defeated!");
    }
}
