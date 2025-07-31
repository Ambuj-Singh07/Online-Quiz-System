package org.example.gui;

import javax.swing.*;
import java.awt.*;

public class ResultScreen extends JFrame {
    public ResultScreen(int score) {
        setTitle("Quiz Result");
        setSize(300, 150);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel scoreLabel = new JLabel("Your Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(scoreLabel, BorderLayout.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
