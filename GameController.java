public class GameController {
    private final GameData data;
    private final CombatEngine engine;
    private final GameView view;

    public GameController(GameData data, GameView view, CombatEngine engine){
        this.data = data;
        this.view = view;
        this.engine = engine;
    }

    public void start(){
        view.splashScreen();
        while(processCommand(view.displayMainMenu())){

        }
        view.endGame();
    }

    private void processSetActive(String nameOrId){
        Knight found = data.getKnight(nameOrId.trim());

        if (found != null){
            if(data.getActive(nameOrId) != null){
                System.out.println(nameOrId + " is already active!");
            }
            else {
                boolean added = data.setActive(found);
                if (!added){
                    view.setActiveFailed();
                }
            }
        }
        else {
            view.knightNotFound();
        }
    }

    private void processShowKnight(String nameOrId){
        Knight found = data.getKnight(nameOrId);
        if (found != null){
            view.showKnight(found);
        }
        else {
            view.knightNotFound();
        }
    }

    private void processRemoveActive(String nameOrId){
        Knight found = data.getKnight(nameOrId);
        if (found != null){
            data.removeActive(found);
        }
        else {
            view.knightNotFound();
        }
    }

    protected boolean processCommand(String command){
        String lowercase = command.toLowerCase();
        String copy = lowercase;
        if (lowercase.startsWith("show")){
            String nameOrId = copy.split(" ")[1];
            processShowKnight(nameOrId);
        }
        else if (lowercase.startsWith("set active")){
            String nameOrId = copy.split("set active ")[1];
            processSetActive(nameOrId);
        }
        else if (lowercase.startsWith("remove")){
            String nameOrId = copy.split("remove ")[1];
            processRemoveActive(nameOrId);
        }

        else if (lowercase.startsWith("save")){
            if (lowercase.contains(" ")){
                String fileName = copy.split("save ")[1];
                data.save(fileName);
            }
            else {
                data.save("saveData.csv");
            }
        }

        else {
            switch(lowercase){
                case "exit":
                case "goodbye":
                case "bye":
                    return false;
    
                case "ls":
                case "list all":
                    view.listKnights(data.getKnights());
                    break;
    
                case "list active":
                    view.listKnights(data.getActiveKnights());
                    break;
                    
                case "explore":
                case "adventure":
                case "quest":
                    engine.initialize();
                    engine.runCombat();
                    engine.clear();
                    break;

                default: 
                    view.printHelp();
            }
        }

        return true;
    }
}
