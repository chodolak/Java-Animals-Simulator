package a2b;

import java.awt.Color;

public class Plant extends Lifeform implements HerbEddible, OmniEddible {

    public Plant(Cell cell) {
        super(cell, 10, 3, 2, 0);
        setColour( Color.GREEN.getRGB());
    }

    //Check if eatten.
    public void eatten() {
        eatLast += 1;
        Color c = getColour();
        eat(null, c.getRGB());
        updateEatten();
    }

    //Counts food.
    protected int countFood(Cell[] neighbors) {
        return 0;
    }
    
    //Returns eddible.
    protected boolean eddible(Lifeform life) {
        return false;
    }
    //Creates life.
    protected Lifeform createLife(Cell pos, int eatBy) {
        return new Plant(pos);
    }

    //Counts life.
    protected int countLife(Cell[] neighbors) {
        int count = 0;
        for (Cell c : neighbors) {
            if ((c.getLife() instanceof Plant)) {
                count++;
            }
        }
        return count;
    }
}
