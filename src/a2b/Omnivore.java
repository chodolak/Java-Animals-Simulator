package a2b;

import java.awt.Color;

public class Omnivore extends Animal {

    //Creates omnivore.
    public Omnivore(Cell cell) {
        super(cell, 2, 1, 3, 3);
        setColour(Color.MAGENTA.getRGB());
    }
    
    //Checks if eddible.
    protected boolean eddible(Lifeform life) {
        if ((life instanceof OmniEddible)) {
            return true;
        }
        return false;
    }
    
    //Counts food.
    protected int countFood(Cell[] neighbors) {
        int count = 0;
        for (Cell c : neighbors) {
            if ((c.getLife() instanceof OmniEddible)) {
                count++;
            }
        }
        return count;
    }

    //Creates life.
    protected Lifeform createLife(Cell c, int eatBy) {
        return new Omnivore(pos);
    }

    //Counts life.
    protected int countLife(Cell[] neighbors) {
        int count = 0;
        for (Cell c : neighbors) {
            if ((c.getLife() instanceof Omnivore)) {
                count++;
            }
        }
        return count;
    }
}
