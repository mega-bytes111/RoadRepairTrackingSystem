package controller;

import dao.UserDAO;
import model.User;
import view.*;

import javax.swing.*;

public class LoginController {

    public LoginController(LoginView view) {

        view.loginButton.addActionListener(e -> {

            String username = view.usernameField.getText();
            String password = new String(view.passwordField.getPassword());

            if(username.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(view,"All fields required");
                return;
            }

            UserDAO dao = new UserDAO();
            User user = dao.login(username,password);

            if(user != null){
                view.dispose();
                new DashboardView(user);
            } else {
                JOptionPane.showMessageDialog(view,"Invalid Login");
            }
        });
    }
}