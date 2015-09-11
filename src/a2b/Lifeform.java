package a2b;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Lifeform {
    private Color c;
    private int numMate = 0;
    private int numEmpty = 0;
    private int numFood = 0;
    protected int eatLast = 0;
    protected int mustEatBy = 0;
    private boolean alive = true;
    protected Cell pos;
    protected Cell newPos = null;
    
    //Creates lifeform.
    public Lifeform(Cell cell, int eatby, int numMate, int numEmpty,
            int numFood) {
        this.pos = cell;
        this.mustEatBy = eatby;
        this.numMate = numMate;
        this.numEmpty = numEmpty;
        this.numFood = numFood;
    }
    
    //Sets colour.
    protected void setColour(int r, int g, int b) {
        c = new Color(r, g, b);
    }

    //Checks for eatting.
    public void eat(Lifeform l, int colour) {
        if (l != null) {
            l.dead();
            this.eatLast = 0;
            setColour(colour);
        } else {
            Color c = new Color(colour);
            setColour((int) (c.getRed() * 0.8D), (int) (c.getGreen() * 0.8D),
                    (int) (c.getBlue() * 0.8D));
        }
    }

    //Sets colour.
    protected void setColour(int rgb) {
        c = new Color(rgb);
    }
    
    //Gets colour.
    protected Color getColour() {
        return c;
    }

    //Creates new lifeform.
    public Lifeform giveBirth() {
        Lifeform l = null;

        Cell[] adj = getPos().getAdjacentCells();
        int nlife = countLife(adj);
        if (nlife >= numMate) {
            
            ArrayList<Cell> emptyCells = getEmptyNeighbors(adj);
            
            if ((emptyCells != null) && (emptyCells.size() >= numEmpty)
                    && (countFood(adj) >= numFood)) {
                
                int pos = RandomGenerator.nextNumber(emptyCells.size());
                Cell c = (Cell) emptyCells.get(pos);
                
                l = createLife(c,mustEatBy);
                
                c.addLife(l);
            }
        }
        return l;
    }

    protected abstract int countLife(Cell[] paramArrayOfCell);

    protected abstract int countFood(Cell[] paramArrayOfCell);

    protected abstract Lifeform createLife(Cell paramCell, int paramInt);

    //Gets neighbors.
    protected ArrayList<Cell> getEmptyNeighbors(Cell[] neighbors) {
        ArrayList<Cell> emptyCells = new ArrayList();
        
        for (Cell c : neighbors) {
            
            if (c.getLife() == null) {
                emptyCells.add(c);
            }
        }
        return emptyCells;
    }

    //Updates eatten status.
    public void updateEatten() {
        if (++eatLast > mustEatBy) {
            alive = false;
        }
    }

    //Checks if alive.
    public boolean isAlive() {
        return alive;
    }

    //Sets status.
    public void setStatus(boolean living) {
        alive = living;
    }
    
    //Makes alive dead.
    public void dead() {
        alive = false;
    }

    protected abstract boolean eddible(Lifeform paramLifeform);

    //Chooses the correct position.
    protected Cell choosePosition(Cell[] neighbors) {
        Cell result = null;
        
        ArrayList<Cell> cells = new ArrayList(Arrays.asList(neighbors));
        
        while (cells.size() > 0) {
            
            int choice = RandomGenerator.nextNumber(cells.size());
            result = (Cell) cells.remove(choice);
            
            if (result.getLife() == null) {
                return result;
            }
            
            if (eddible(result.getLife())) {
                return result;
            }
        }
        return result;
    }

    //Moves lifeform.
    public void move() {
        Cell[] adj = getPos().getAdjacentCells();

        Cell newpos = choosePosition(adj);
        
        if (newpos == null) {
            System.out.println("could not move");
            return;
        }
        
        Lifeform life = newpos.getLife();
        eat(life, getColour().getRGB());
        updateEatten();
        setPos(newpos);
        updatePos();
    }

    //Draws the correct background colour.
    public void draw(Graphics g, int w, int h) {
        if (pos != null) {
            pos.setBackground(c);
        }
    }
    
    //Sets position.
    public void setPos(Cell c) {
        newPos = c;
    }

    //Gets position.
    public Cell getPos() {
        return pos;
    }

    //Gets new position.
    public Cell getNewPos() {
        return newPos;
    }

    //Updates position.
    public void updatePos() {
        Lifeform l = null;
        
        if (pos != null) {
            pos.setBackground(Color.WHITE);
            pos.addLife(null);
        }
        
        if (newPos != null) {
            l = newPos.getLife();
        }
        
        if (l != null) {
            l.dead();
        }
        
        pos = newPos;
        
        if (pos != null) {
            pos.addLife(this);
        }
        newPos = null;
    }
}
