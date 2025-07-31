package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;

import org.example.db.DBConnection;

public class QuizScreen extends JFrame {
    private int score = 0, index = 0, total = 10;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup group;
    private ArrayList<Question> questions;
    private Timer timer;

    public QuizScreen(String username) {
        try {
            setTitle("Quiz");
            setSize(400, 300);
            setLayout(new BorderLayout());
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            // Question Label
            questionLabel = new JLabel("Question");
            add(questionLabel, BorderLayout.NORTH);

            // Options Panel
            JPanel centerPanel = new JPanel(new GridLayout(4, 1));
            options = new JRadioButton[4];
            group = new ButtonGroup();
            for (int i = 0; i < 4; i++) {
                options[i] = new JRadioButton();
                group.add(options[i]);
                centerPanel.add(options[i]);
            }
            add(centerPanel, BorderLayout.CENTER);

            // Next Button
            JButton nextButton = new JButton("Next");
            add(nextButton, BorderLayout.SOUTH);

            // Load and show questions
            loadQuestions();
            displayQuestion();

            // Next button logic
            nextButton.addActionListener((ActionEvent e) -> {
                checkAnswer();
                index++;
                if (index < total) {
                    displayQuestion();
                } else {
                    timer.stop();
                    JOptionPane.showMessageDialog(this, "Quiz complete! Score: " + score);
                    storeResult(username);
                    dispose();
                    new ResultScreen(score);
                }
            });

            // Quiz Timer: 60 seconds
            timer = new Timer(60000, e -> {
                JOptionPane.showMessageDialog(this, "Time's up!");
                storeResult(username);
                dispose();
                new ResultScreen(score);
            });
            timer.setRepeats(false);
            timer.start();

            setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Load 10 random questions from DB
    private void loadQuestions() {
        questions = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM questions ORDER BY RAND() LIMIT 10")) {
            while (rs.next()) {
                questions.add(new Question(
                        rs.getString("question"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getInt("answer")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Display a question
    private void displayQuestion() {
        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No questions available in the database.");
            dispose();
            return;
        }

        group.clearSelection();
        Question q = questions.get(index);
        questionLabel.setText((index + 1) + ". " + q.question);
        options[0].setText(q.opt1);
        options[1].setText(q.opt2);
        options[2].setText(q.opt3);
        options[3].setText(q.opt4);
    }

    // Check if selected answer is correct
    private void checkAnswer() {
        Question q = questions.get(index);
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && (i + 1) == q.answer) {
                score++;
            }
        }
    }

    // Store the quiz result in DB
    private void storeResult(String username) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO results (user_id, score, time_taken) VALUES ((SELECT id FROM users WHERE username=?), ?, ?)");
            stmt.setString(1, username);
            stmt.setInt(2, score);
            stmt.setInt(3, 60); // time in seconds
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Question model class (inner)
    static class Question {
        String question, opt1, opt2, opt3, opt4;
        int answer;

        public Question(String question, String opt1, String opt2, String opt3, String opt4, int answer) {
            this.question = question;
            this.opt1 = opt1;
            this.opt2 = opt2;
            this.opt3 = opt3;
            this.opt4 = opt4;
            this.answer = answer;
        }
    }
}
