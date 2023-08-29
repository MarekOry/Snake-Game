package SnakeGame;

import javax.swing.*;
import java.awt.*;


class Snake extends JPanel {
    private int boardWidth, boardHeight;

    public Snake(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        new Dimension(boardWidth, boardHeight);
        setBackground(Color.DARK_GRAY);
    }
}
