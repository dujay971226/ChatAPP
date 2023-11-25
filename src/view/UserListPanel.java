package view;

import javax.swing.*;
import java.awt.*;

public class UserListPanel extends JPanel {
    public UserListPanel() {
        setLayout(new GridLayout(0, 5));  // 设置为网格布局，5列，行数自动增长

        // 假设这是你的用户列表
        String[] users = {"User1", "User2", "User3", "User4", "User5", "User6", "User7", "User8", "User9", "User10"};

        // 为每个用户创建一个标签，并添加到面板
        for (String user : users) {
            add(new JLabel(user));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("User List");
        UserListPanel userListPanel = new UserListPanel();

        // 创建一个滚动面板，将用户列表面板添加到滚动面板
        JScrollPane scrollPane = new JScrollPane(userListPanel);
        frame.getContentPane().add(scrollPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
