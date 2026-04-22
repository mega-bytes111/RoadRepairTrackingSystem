package controller;

import dao.RepairRequestDAO;
import view.ClerkPanel;

import javax.swing.*;

public class RepairRequestController {
    public RepairRequestController(ClerkPanel panel) {
        panel.createBtn.addActionListener(e -> {
            String road = panel.roadField.getText();
            String suburb = panel.suburbField.getText();
            String desc = panel.descArea.getText();

            if(road.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Road name required");
                return;
            }

            new RepairRequestDAO().create(road, suburb, desc);
            JOptionPane.showMessageDialog(panel, "Request Created");
        });
    }
}