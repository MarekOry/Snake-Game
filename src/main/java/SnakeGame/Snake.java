package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


class Snake extends JPanel  implements ActionListener, KeyListener {
    private int boardWidth, boardHeight;
    private final int squareSize = 20;
    private Random random;
    private Timer gameLoop;
    private int horizontalVelocity;
    private int verticalVelocity;
    private boolean gameOver = false;

    private Square head;
    private ArrayList<Square> tail;

    private Square apple;

    public Snake(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.DARK_GRAY);
        addKeyListener(this);

        head = new Square(boardWidth/squareSize/2,boardHeight/squareSize/2);
        tail = new ArrayList<>();

        random = new Random();
        apple = new Square(random.nextInt(boardWidth/squareSize), random.nextInt(boardHeight/squareSize));
        newApple();

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

        for(int i =0; i<tail.size(); i++) {
            Square tailPart = tail.get(i);
            graphics.fill3DRect(tailPart.x*squareSize, tailPart.y*squareSize, squareSize, squareSize, true);
        }

        graphics.setFont(new Font("Arial",Font.PLAIN, 14));
        graphics.setColor(Color.WHITE);
        graphics.drawString("Score: " + tail.size(), squareSize - 10, squareSize);

        if(gameOver) {
            String gameOverString = "GAME OVER";
            Font gameOverFont;
            graphics.setFont(gameOverFont = new Font("Serif", Font.BOLD, 30));
            graphics.setColor(Color.YELLOW);
            FontMetrics gameOverFontMetrics = graphics.getFontMetrics(gameOverFont);
            //draw string in the middle of the board
            graphics.drawString(gameOverString,
                    (boardWidth-gameOverFontMetrics.stringWidth(gameOverString))/2,
                    ((boardHeight-gameOverFontMetrics.getHeight())/2) + gameOverFontMetrics.getAscent());
        }
    }

    void move() {
        //eat apple
        if(collision(head, apple)) {
            tail.add(new Square(apple.x, apple.y));
            newApple();
        }

        //tail
        for (int i = tail.size()-1;i>=0; i--) {
            Square tailPart = tail.get(i);
            if (i==0) {
                tailPart.x = head.x;
                tailPart.y = head.y;
            } else {
                Square prevTailPart = tail.get(i-1);
                tailPart.x = prevTailPart.x;
                tailPart.y = prevTailPart.y;
            }
        }

        head.x += horizontalVelocity;
        head.y += verticalVelocity;

        //eat tail
        for (int i = 0; i<tail.size(); i++) {
            Square tailPart = tail.get(i);
            if (collision(head, tailPart)) {
                gameOver = true;
            }
        }

        //walls
        if (head.x*squareSize < 0 || head.x*squareSize > boardWidth || head.y*squareSize < 0 || head.y*squareSize > boardHeight ) {
            gameOver = true;
        }
    }

    void newApple() {
        apple.x = random.nextInt(boardWidth/squareSize);
        apple.y = random.nextInt(boardHeight/squareSize);
    }

    boolean collision(Square a, Square b) {
        return a.x == b.x && a.y == b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) gameLoop.stop();
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
