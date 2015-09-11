package a2b;

import java.awt.Color;

public class Herbivore extends Animal implements CarnEddible, OmniEddible {

    //Sets up herbivore.
    public Herbivore(Cell cell) {
        super(cell, 10, 1, 1, 2);
        setColour(Color.YELLOW.getRGB());
    }

    //Counts food.
    public int countFood(Cell[] neighbors) {
        int count = 0;
        for (Cell c : neighbors) {
            if ((c.getLife() instanceof HerbEddible)) {
                count++;
            }
        }
        return count;
    }
    
    //Returns if the lifeform can be eatten.
    public boolean eddible(Lifeform life) {
        if ((life instanceof HerbEddible)) {
            return true;
        }
        return false;
    }
    

    //Counts life.
    public int countLife(Cell[] neighbors) {
        int count = 0;
        for (Cell c : neighbors) {
            if ((c.getLife() instanceof Herbivore)) {
                count++;
            }
        }
        return count;
    }

    //Creates life.
    public Lifeform createLife(Cell pos, int eatBy) {
        return new Herbivore(pos);
    }   
}
