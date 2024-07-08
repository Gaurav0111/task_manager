package view;

import model.Task;
import service.TaskService;
import service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaskPage extends JFrame {
    private JButton refreshButton;
    private JList<Task> taskList;

    public TaskPage() {
        setTitle("Task Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTaskList();
            }
        });
        refreshTaskList();
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        taskList = new JList<>();
        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(230, 5, 100, 20);

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(10, 25, 355, 250);
        panel.add(scrollPane);
        panel.add(refreshButton);
    }

    private void refreshTaskList() {
        TaskService taskService = new TaskService();
        List<Task> tasks = taskService.getAllTasks();
        DefaultListModel<Task> listModel = new DefaultListModel<>();
        for (Task task : tasks) {
            listModel.addElement(task);
        }
        taskList.setModel(listModel);

        UserService userService = new UserService();
        List<model.User> users = userService.getAllUsers();
    }
}
