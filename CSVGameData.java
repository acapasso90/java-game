import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVGameData extends GameData {
    
    public CSVGameData(String gamedata, String saveData ){
        loadGameData(gamedata);
        loadSaveData(saveData);
    }

    void loadSaveData(String saveData){
        readFile(saveData, "save");
        
    }

    void continueLoadSaveData(Scanner fileReader){
        int counter = 0;

        while(fileReader.hasNextLine()) {
            Scanner line = new Scanner(fileReader.nextLine());
            line.useDelimiter(","); 
            Knight kt = new Knight(
                ++counter,
                line.next().trim(),
                line.nextInt(),
                line.nextInt(),
                line.nextInt(),
                DiceType.valueOf(line.next()),
                line.nextInt()
            );
            knights.add(kt);
        }
    }

    private void readFile(String fileName, String type){
        try (Scanner fileReader = new Scanner(new File(fileName)))  {
            if (type.equals("save")){
                continueLoadSaveData(fileReader);
            }
            else {
                continueLoadGameData(fileReader);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Uh oh! I couldn't find your file!!");
        }
    }

    void loadGameData(String gamedata){
        readFile(gamedata, "game");
    }

    void continueLoadGameData(Scanner fileReader){
        while(fileReader.hasNextLine()) {
            Scanner line = new Scanner(fileReader.nextLine());

            line.useDelimiter(","); 
            String type = line.next();
            if (type.startsWith("MOB")){
                MOB newMob = new MOB(
                    line.next().trim(),
                    line.nextInt(),
                    line.nextInt(),
                    line.nextInt(),
                    DiceType.valueOf(line.next())
                );
                monsters.add(newMob);
            }
            else {
                String name = line.next().trim();
                int hpBonus = line.nextInt();
                int armor = line.nextInt();
                int hitModifier = line.nextInt();
                String dice = line.next();
                Fortune newFortune = null;
                if (dice.equals("-")){
                    newFortune = new Fortune(                    
                        name,
                        hpBonus,
                        armor,
                        hitModifier
                    );
                }
                else {
                    newFortune = new Fortune(                    
                        name,
                        hpBonus,
                        armor,
                        hitModifier,
                        DiceType.valueOf(dice)
                    );
                }
               
                fortunes.add(newFortune);
            }
    
        }
    }

    public void save(String filename){
        try(FileOutputStream fileStream = new FileOutputStream((filename))){
            PrintWriter output = new PrintWriter(fileStream);

            for (Knight currKnight: knights){
                output.println(currKnight.toCSV());
            }
            output.close();
            System.out.println(filename + " saved!");

        }
        catch(IOException e){
            System.out.println("Can not save to file");
        }

    }
}