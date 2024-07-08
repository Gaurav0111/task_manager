package view;

import model.Task;
import service.TaskService;
import service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateTask extends JFrame {
    private JTextField titleField;
    private JTextField descriptionField;
    private JComboBox<String> statusField;
    private JComboBox<String> ownerComboBox;
    private JButton createButton;
    final String Status[] = { "new", "active", "pending", "closed" };

    public CreateTask() {
        setTitle("Task Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskService taskService = new TaskService();
                Task task = new Task();
                task.setTitle(titleField.getText());
                task.setDescription(descriptionField.getText());
                task.setStatus((String) statusField.getSelectedItem());
                task.setOwner((String) ownerComboBox.getSelectedItem());
                taskService.addTask(task);
                JOptionPane.showMessageDialog(null, "Task created successfully!");
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        titleField = new JTextField(20);
        titleField.setBounds(100, 20, 165, 25);
        panel.add(titleField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10, 50, 80, 25);
        panel.add(descriptionLabel);

        descriptionField = new JTextField(20);
        descriptionField.setBounds(100, 50, 165, 25);
        panel.add(descriptionField);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(10, 80, 80, 25);
        panel.add(statusLabel);

        statusField = new JComboBox<>();
        statusField.setBounds(100, 80, 165, 25);
        panel.add(statusField);
        for (String s : Status)
            statusField.addItem(s);

        JLabel ownerLabel = new JLabel("Owner:");
        ownerLabel.setBounds(10, 110, 80, 25);
        
        ownerComboBox = new JComboBox<>();
        ownerComboBox.setBounds(100, 110, 165, 25);
        panel.add(ownerComboBox);
        
        UserService userService = new UserService();
        List<model.User> users = userService.getAllUsers();
        try {
            ownerComboBox.removeAllItems();
        } catch (Exception e) {
        }
        for (model.User user : users) {
            ownerComboBox.addItem(user.getUsername());
        }
        panel.add(ownerLabel);


        createButton = new JButton("Create Task");
        createButton.setBounds(10, 140, 150, 25);
        panel.add(createButton);
    }

}
