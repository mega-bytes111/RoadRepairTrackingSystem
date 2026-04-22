package view;

import controller.ScheduleController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SupervisorPanel extends JPanel {

    public JTable requestTable;
    public JComboBox<String> severityBox;
    public JButton assessBtn;
    public JButton scheduleBtn;
    public JButton completeBtn;   // ✅ IMPORTANT

    public SupervisorPanel() {

        setLayout(new BorderLayout());

        // ✅ Table
        requestTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("RID");
        model.addColumn("Road");
        model.addColumn("Suburb");
        model.addColumn("Status");
        requestTable.setModel(model);

        add(new JScrollPane(requestTable), BorderLayout.CENTER);

        // ✅ Bottom Panel
        JPanel bottom = new JPanel();

        severityBox = new JComboBox<>(new String[]{
                "CRITICAL","HIGH","MEDIUM","LOW"
        });

        assessBtn = new JButton("Assess & Set Priority");
        scheduleBtn = new JButton("Auto Schedule");
        completeBtn = new JButton("Mark As Completed");  // ✅ BUTTON CREATED

        bottom.add(new JLabel("Severity:"));
        bottom.add(severityBox);
        bottom.add(assessBtn);
        bottom.add(scheduleBtn);
        bottom.add(completeBtn);   // ✅ ADDED TO PANEL

        add(bottom, BorderLayout.SOUTH);

        // ✅ Attach Controller
        new ScheduleController(this);
    }
}