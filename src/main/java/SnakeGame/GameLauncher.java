package SnakeGame;

import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int boardWidth = 800;
                int boardHeight = boardWidth;

                JFrame frame = new JFrame("Snake");
                frame.setVisible(true);
                frame.setSize(boardWidth, boardHeight);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Snake snake = new Snake(boardWidth, boardHeight);
                frame.add(snake);
                frame.pack();
            }
        });
    }
}