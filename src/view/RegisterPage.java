package view;

import javax.swing.*;

import model.Organization;
import model.User;
import service.OrganizationService;
import service.UserService;

import java.awt.event.*;
import java.sql.*;

public class RegisterPage extends JFrame {
    private JTextField firstNameField, lastNameField, emailField, phoneField, userNameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JTextField orgNameField, domainField, headOfficeField, sizeField;
    private JButton registerButton, loginButton;

    public RegisterPage() {
        setTitle("Register Organization");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel adminLabel = new JLabel("Admin Details");
        adminLabel.setBounds(10, 10, 200, 25);
        add(adminLabel);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(10, 50, 150, 25);
        add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setBounds(160, 50, 200, 25);
        add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(10, 90, 150, 25);
        add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBounds(160, 90, 200, 25);
        add(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 130, 150, 25);
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(160, 130, 200, 25);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(10, 170, 150, 25);
        add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(160, 170, 200, 25);
        add(phoneField);

        JLabel userNameLabel = new JLabel("Username:");
        userNameLabel.setBounds(10, 210, 150, 25);
        add(userNameLabel);
        userNameField = new JTextField();
        userNameField.setBounds(160, 210, 200, 25);
        add(userNameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 250, 150, 25);
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(160, 250, 200, 25);
        add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(10, 290, 150, 25);
        add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(160, 290, 200, 25);
        add(confirmPasswordField);

        JLabel orgLabel = new JLabel("Organization Details");
        orgLabel.setBounds(10, 330, 200, 25);
        add(orgLabel);

        JLabel orgNameLabel = new JLabel("Organization Name:");
        orgNameLabel.setBounds(10, 370, 150, 25);
        add(orgNameLabel);
        orgNameField = new JTextField();
        orgNameField.setBounds(160, 370, 200, 25);
        add(orgNameField);

        JLabel domainLabel = new JLabel("Company Domain:");
        domainLabel.setBounds(10, 410, 150, 25);
        add(domainLabel);
        domainField = new JTextField();
        domainField.setBounds(160, 410, 200, 25);
        add(domainField);

        JLabel headOfficeLabel = new JLabel("Head Office Address:");
        headOfficeLabel.setBounds(10, 450, 150, 25);
        add(headOfficeLabel);
        headOfficeField = new JTextField();
        headOfficeField.setBounds(160, 450, 200, 25);
        add(headOfficeField);

        JLabel sizeLabel = new JLabel("Company Size:");
        sizeLabel.setBounds(10, 490, 150, 25);
        add(sizeLabel);
        sizeField = new JTextField();
        sizeField.setBounds(160, 490, 200, 25);
        add(sizeField);

        registerButton = new JButton("Register");
        registerButton.setBounds(160, 530, 100, 25);
        add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerOrganization();
            }
        });

        JLabel loginPromptLabel = new JLabel("Already have an account?");
        loginPromptLabel.setBounds(10, 570, 200, 25);
        add(loginPromptLabel);

        loginButton = new JButton("Login");
        loginButton.setBounds(210, 570, 100, 25);
        add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginPage().setVisible(true);
            }
        });
    }

    private void registerOrganization() {
        if (!new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }

        OrganizationService organizationService = new OrganizationService();
        UserService userService = new UserService();
        User user = new User();
        user.setFirstName(firstNameField.getText());
        user.setLastName(lastNameField.getText());
        user.setEmail(emailField.getText());
        user.setPhone(phoneField.getText());
        user.setUsername(userNameField.getText());
        user.setPassword(new String(passwordField.getPassword()));
        userService.addUser(user);

        Organization organization = new Organization();
        organization.setOrgName(orgNameField.getText());
        organization.setDomain(domainField.getText());
        organization.setHeadOfficeAddress(headOfficeField.getText());
        organization.setSize(Integer.parseInt(sizeField.getText()));
        organization.setAdminId(1);
        organizationService.addOrganization(organization);
        JOptionPane.showMessageDialog(this, "Organization registered successfully!");

    }
}
