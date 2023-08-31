package SnakeGame;

import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int boardWidth = 800;
                int boardHeight = boardWidth;

                JFrame window = new JFrame("Snake");
                window.setVisible(true);
                window.setSize(boardWidth, boardHeight);
                window.setLocationRelativeTo(null); //centers on screen
                window.setResizable(false);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Snake snake = new Snake(boardWidth, boardHeight);
                window.add(snake);
                window.pack();
                snake.requestFocus();
            }
        });
    }
}