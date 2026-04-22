package view;

import model.User;
import javax.swing.*;

public class DashboardView extends JFrame {

    public DashboardView(User user){

        setTitle("Dashboard - " + user.getRole());
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        switch(user.getRole()){

            case "CLERK":
                add(new ClerkPanel());
                break;

            case "SUPERVISOR":
                add(new SupervisorPanel());   // ✅ must be this
                break;

            case "ADMIN":
                add(new AdminPanel());        // ✅ must be this
                break;

            case "MAYOR":
                add(new MayorPanel());        // ✅ must be this
                break;
        }

        setVisible(true);
    }
}