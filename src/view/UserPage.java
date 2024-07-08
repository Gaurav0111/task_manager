package view;

import model.Task;
import model.User;
import service.TaskService;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserPage extends JFrame {
    private User user;
    private JLabel listlabel;
    private JList<Task> taskList;
    private JButton viewAllTasksButton;
    private JButton optionButton;
    private JPopupMenu popUpMenu;
    private JMenuItem profileItem;
    private JMenuItem logoutItem;
    private JMenuItem crateUserItem;
    private JMenuItem viewListofTask;
    private JMenuItem crateTask;
    // private JButton profileButton;

    public UserPage(User user) {
        this.user = user;

        setTitle("Task");
        setSize(400, 330);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        viewAllTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskPage taskPage = new TaskPage();
                taskPage.setVisible(true);
            }
        });

        refreshTaskList();
    }

    String formatString(String s) {
        String s1 = s.substring(0, 1).toUpperCase();
        return s1 + s.substring(1, s.length());
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        optionButton = new JButton("Menu");
        optionButton.setBounds(350, 5, 20, 20);

        popUpMenu = new JPopupMenu();
        profileItem = new JMenuItem("Profile");
        crateUserItem = new JMenuItem("Create User");
        viewListofTask = new JMenuItem("View all task");
        crateTask = new JMenuItem("Create new task");
        logoutItem = new JMenuItem("Logout");
        popUpMenu.add(profileItem);
        popUpMenu.add(viewListofTask);
        popUpMenu.add(crateTask);
        if (user.isAdmin())
            popUpMenu.add(crateUserItem);
        popUpMenu.add(logoutItem);
        panel.add(optionButton);
        optionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popUpMenu.show(optionButton, optionButton.getWidth() / 2, optionButton.getHeight() / 2);
            }
        });

        profileItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProfilePage profile = new ProfilePage(user);
                profile.setVisible(true);
            }
        });
        logoutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // profile page;
            }
        });
        crateUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminPage adminPage = new AdminPage();
                adminPage.setVisible(true);
            }
        });
        viewListofTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPage taskPage = new TaskPage();
                taskPage.setVisible(true);
            }
        });
        crateTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateTask createTask = new CreateTask();
                createTask.setVisible(true);
            }
        });

        listlabel = new JLabel("Task assigned to " + formatString(user.getUsername()) + " :");
        listlabel.setBounds(10, 20, 200, 25);
        panel.add(listlabel);

        taskList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(0, 40, 400, 200);
        panel.add(scrollPane);

        viewAllTasksButton = new JButton("View All Tasks");
        viewAllTasksButton.setBounds(10, 260, 150, 25);
        panel.add(viewAllTasksButton);
    }

    private void refreshTaskList() {
        TaskService taskService = new TaskService();
        List<Task> tasks = taskService.getTasksByOwner(user.getUsername());
        DefaultListModel<Task> listModel = new DefaultListModel<>();
        for (Task task : tasks) {
            listModel.addElement(task);
        }
        taskList.setModel(listModel);
    }
}
