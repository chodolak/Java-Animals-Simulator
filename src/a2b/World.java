package a2b;

import java.awt.Color;
import java.util.ArrayList;

class World {
    private int w;
    private int h;
    private Cell[][] cell;

    //Sets up world.
    public World(int width, int height) {
        w = width;
        h = height;
        cell = new Cell[w][h];
    }

    //Updates turn.
    public void takeTurn() {
        update();
    }

   

    //Updates world.
    public void update() {
        ArrayList<Lifeform> lives = getAllLifeforms();
        for (Lifeform life : lives) {
            if (life.isAlive()) {
                life.move();

                life.giveBirth();
            }
            if (!life.isAlive()) {
                Cell pos = life.getPos();
                life.setPos(null);
                if (pos != null) {
                    pos.addLife(null);
                    pos.setBackground(Color.WHITE);
                }
                life.updatePos();
            }
        }
    }

    //Gets all lifeforms in arraylist.
    private ArrayList<Lifeform> getAllLifeforms() {
        ArrayList<Lifeform> life = new ArrayList();
        Lifeform l = null;
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[i].length; j++) {
                l = cell[i][j].getLife();
                if (l != null) {
                    life.add(l);
                }
            }
        }
        return life;
    }
    
    //Checks what in each cell.
    public void init() {

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.cell[i][j] = new Cell(this, i, j);

                int val = RandomGenerator.nextNumber(100);
                
                if (val >= 80) {
                    cell[i][j].addLife(new Herbivore(cell[i][j]));
                } else if (val >= 60) {
                    cell[i][j].addLife(new Plant(cell[i][j]));
                } else if (val >= 50) {
                    cell[i][j].addLife(new Carnivore(cell[i][j]));
                } else if (val >= 40) {
                    cell[i][j].addLife(new Omnivore(cell[i][j]));
                }
            }
        }
    }
    //Return cell at location.
    public Cell getCellAt(int row, int col) {
        if ((row >= 0) && (row < w) && (col >= 0) && (col < h)) {
            return cell[row][col];
        }
        return null;
    }
    
    //Returns rows.
    public int getRowCount() {
        return w;
    }

    //Returns columns.
    public int getColumnCount() {
        return h;
    }
}
