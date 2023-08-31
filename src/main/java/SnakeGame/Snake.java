package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


class Snake extends JPanel {
    private int boardWidth, boardHeight;
    private final int squareSize = 20;
    private Random random;
    private Square head;

    private Square apple;

    public Snake(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        new Dimension(boardWidth, boardHeight);
        setBackground(Color.DARK_GRAY);

        head = new Square(15,15);
        random = new Random();
        apple = new Square(random.nextInt(boardWidth/squareSize), random.nextInt(boardHeight/squareSize));
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawGraphics(graphics);
    }

    void drawGraphics(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(apple.x, apple.y, squareSize, squareSize);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(head.x, head.y, squareSize, squareSize);
    }
}
