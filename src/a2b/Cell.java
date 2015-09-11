package a2b;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Cell extends JPanel {
    private World world;
    private int posX;
    private int posY;
    private Lifeform life;

    // Sets up cell.
    public Cell(World w, int x, int y) {
        world = w;
        posX = x;
        posY = y;
        setBackground(Color.WHITE);
    }
    
    //Paints.
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(0, 0, getWidth(), getHeight());
        if (life != null) {
            life.draw(g, getWidth() - 1, getHeight() - 1);
        }
    }
   

    // Adds life.
    public void addLife(Lifeform l) {
        life = l;
    }

   

    // Returns nearby cells.
    public Cell[] getAdjacentCells() {
        int i;
        int j;
        Cell temp = null;
        
        ArrayList<Cell> adj = new ArrayList();
        Cell[] c = new Cell[1];
        int x = posX;
        int y = posY;

        
        for (i = -1; i <= 1; i++) {
            for (j = -1; j <= 1; j++) {
                x = posX + i;
                y = posY + j;
                temp = world.getCellAt(x, y);
                if (temp != null) {
                    adj.add(temp);
                }
            }
        }
        adj.remove(this);

        return (Cell[]) adj.toArray(c);
    }
    
    // Returns location.
    public Point getLocation() {
        return new Point(posX, posY);
    }
    
    // Returns life.
    public Lifeform getLife() {
        return life;
    }
}
