package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


class Snake extends JPanel  implements ActionListener, KeyListener {
    private int boardWidth, boardHeight;
    private final int squareSize = 20;
    private Random random;
    private Timer gameLoop;
    private int horizontalVelocity;
    private int verticalVelocity;

    private Square head;

    private Square apple;

    public Snake(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.DARK_GRAY);
        addKeyListener(this);

        head = new Square(boardWidth/squareSize/2,boardHeight/squareSize/2);
        random = new Random();
        apple = new Square(random.nextInt(boardWidth/squareSize), random.nextInt(boardHeight/squareSize));

        horizontalVelocity = 0;
        verticalVelocity = -1;

        gameLoop = new Timer(100,this);
        gameLoop.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawGraphics(graphics);
    }

    void drawGraphics(Graphics graphics) {

        for(int i=0; i<boardWidth/squareSize; i++) {
            graphics.drawLine(i*squareSize,0, i*squareSize, boardHeight);
            graphics.drawLine(0, i*squareSize, boardWidth, i*squareSize);
        }

        graphics.setColor(Color.RED);
        graphics.fill3DRect(apple.x*squareSize, apple.y*squareSize, squareSize, squareSize, true);

        graphics.setColor(Color.GREEN);
        graphics.fill3DRect(head.x*squareSize, head.y*squareSize, squareSize, squareSize, true);

    }

    void move() {
        head.x += horizontalVelocity;
        head.y += verticalVelocity;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) && verticalVelocity != 1) {
            horizontalVelocity = 0;
            verticalVelocity = -1;
        } else if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) && verticalVelocity != -1) {
            horizontalVelocity = 0;
            verticalVelocity = 1;
        } else if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && horizontalVelocity != 1) {
            horizontalVelocity = -1;
            verticalVelocity = 0;
        } else if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && horizontalVelocity != -1) {
            horizontalVelocity = 1;
            verticalVelocity = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
