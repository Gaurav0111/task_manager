package view;

import model.User;

import javax.swing.*;

public class ProfilePage extends JFrame {

    public ProfilePage(User user) {
        setTitle("Profile " + user.getUsername());
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);
        JLabel fullName = new JLabel("Full Name: " + user.getFullName());
        fullName.setBounds(10, 20, 150, 25);
        panel.add(fullName);

        JLabel username = new JLabel("User Name: " + user.getUsername());
        username.setBounds(10, 50, 150, 25);
        panel.add(username);

        JButton updatePassword = new JButton("Update Password");
        updatePassword.setBounds(10, 80, 150, 25);
        panel.add(updatePassword);

    }
}
