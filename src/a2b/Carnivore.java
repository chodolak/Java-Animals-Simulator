package a2b;

import java.awt.Color;

public class Carnivore extends Animal implements OmniEddible {

    // Sets up Carnivore.
    public Carnivore(Cell cell) {
        super(cell, 3, 1, 1, 2);
        setColour(Color.BLUE.getRGB());
    }

    // Returns the current food.
    public int countFood(Cell[] neighbors) {
        int count = 0;
        for (Cell c : neighbors) {
            if ((c.getLife() instanceof CarnEddible)) {
                count++;
            }
        }
        return count;
    }
    
    // Makes sure things can be eatten.
    public boolean eddible(Lifeform life) {
        if ((life instanceof CarnEddible)) {
            return true;
        }
        return false;
    }
    
    // Creates a new life.
    public Lifeform createLife(Cell c, int eatBy) {
        return new Herbivore(pos);
    }

  
    
    // Returns the current life.
    public int countLife(Cell[] neighbors) {
        int count = 0;
        for (Cell c : neighbors) {
            if ((c.getLife() instanceof Carnivore)) {
                count++;
            }
        }
        return count;
    }
}
