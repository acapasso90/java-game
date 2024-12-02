import java.util.Random;

public class DiceSet {
    private Random rnd = new Random();

    public int roll(DiceType diceType) {
        int myval = 0;
        switch(diceType) {
            case D4:
                myval = rnd.nextInt(4) + 1;
            break;
            case D6:
                myval = rnd.nextInt(6) + 1;
            break;
            case D8:
                myval = rnd.nextInt(8) + 1;
            break;
            case D10:
                myval = rnd.nextInt(10) + 1;
            break;
            case D12:
                myval = rnd.nextInt(12) + 1;
            break;
            default:
                myval = rnd.nextInt(12) + 1;
        }


        return myval;
    }

    public static void main(String[] args) {
    }
}
