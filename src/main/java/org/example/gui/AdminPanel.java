package org.example.gui;


import javax.swing.*;
import java.awt.*;
import java.sql.*;

import org.example.db.DBConnection;

public class AdminPanel extends JFrame {
    public AdminPanel() {
        setTitle("Admin Panel - Add Question");
        setSize(400, 400);
        setLayout(new GridLayout(7, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField question = new JTextField();
        JTextField opt1 = new JTextField();
        JTextField opt2 = new JTextField();
        JTextField opt3 = new JTextField();
        JTextField opt4 = new JTextField();
        JTextField answer = new JTextField();
        JButton submit = new JButton("Add Question");

        add(new JLabel("Question:")); add(question);
        add(new JLabel("Option 1:")); add(opt1);
        add(new JLabel("Option 2:")); add(opt2);
        add(new JLabel("Option 3:")); add(opt3);
        add(new JLabel("Option 4:")); add(opt4);
        add(new JLabel("Correct Option (1-4):")); add(answer);
        add(new JLabel()); add(submit);

        submit.addActionListener(e -> {
            try (Connection conn = DBConnection.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO questions (question, option1, option2, option3, option4, answer) VALUES (?, ?, ?, ?, ?, ?)");
                stmt.setString(1, question.getText());
                stmt.setString(2, opt1.getText());
                stmt.setString(3, opt2.getText());
                stmt.setString(4, opt3.getText());
                stmt.setString(5, opt4.getText());
                stmt.setInt(6, Integer.parseInt(answer.getText()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Question added successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding question.");
            }
        });

        setVisible(true);
    }
}

