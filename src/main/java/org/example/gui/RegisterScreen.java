package org.example.gui;


import javax.swing.*;
import java.awt.*;
import java.sql.*;

import org.example.db.DBConnection;

public class RegisterScreen extends JFrame {
    public RegisterScreen() {
        setTitle("Register");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton registerButton = new JButton("Register");

        add(new JLabel("Username:")); add(userField);
        add(new JLabel("Password:")); add(passField);
        add(new JLabel()); add(registerButton);

        registerButton.addActionListener(e -> {
            try (Connection conn = DBConnection.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                stmt.setString(1, userField.getText());
                stmt.setString(2, new String(passField.getPassword()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose();
                new LoginScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}

