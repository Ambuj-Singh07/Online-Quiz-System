package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

import org.example.db.DBConnection;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        add(new JLabel("Username:"));
        add(userField);
        add(new JLabel("Password:"));
        add(passField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener((ActionEvent e) -> {
            try (Connection conn = DBConnection.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                stmt.setString(1, userField.getText());
                stmt.setString(2, new String(passField.getPassword()));
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    dispose();
                    new QuizScreen(userField.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        registerButton.addActionListener(e -> {
            dispose();
            new RegisterScreen();
        });

        setVisible(true);
    }
}
