
public class Main {
    private static String gamedata = "gamedata.csv";
    private static String saveData = "knights.csv";

    public Main(){
    }

    private static void processArgs(String[] args){
        for(String currArg: args){
            if (currArg.toLowerCase().startsWith("--data")){
                Main.gamedata = currArg.split("=")[1];
            }
            else {
                Main.saveData = currArg;
            }
        }
    }
    public static void main(String[] args){
        processArgs(args); // method that parses the args, optional but cleaned up the code
        GameData data = new CSVGameData(gamedata, saveData);
        GameView view  = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);
        controller.start();
    }
}
