package a2b;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TurnListener extends MouseAdapter {
    GameFrame game;

    public TurnListener(GameFrame g) {
        game = g;
    }

    public void mouseClicked(MouseEvent e) {
        game.takeTurn();
    }
}
