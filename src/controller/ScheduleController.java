package controller;

import dao.RepairRequestDAO;
import dao.ScheduleDAO;
import dao.ResourceDAO;
import model.RepairRequest;
import view.SupervisorPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.List;

public class ScheduleController {

    private SupervisorPanel panel;
    private RepairRequestDAO requestDAO;
    private ScheduleDAO scheduleDAO;
    private ResourceDAO resourceDAO;

    public ScheduleController(SupervisorPanel panel) {
        this.panel = panel;
        this.requestDAO = new RepairRequestDAO();
        this.scheduleDAO = new ScheduleDAO();
        this.resourceDAO = new ResourceDAO();

        loadRequests();
        setupListeners();
    }

    // ✅ Load All Requests Into Table
    private void loadRequests() {

        DefaultTableModel model = (DefaultTableModel) panel.requestTable.getModel();
        model.setRowCount(0);

        List<RepairRequest> list = requestDAO.getAllRequests();

        for(RepairRequest r : list) {
            model.addRow(new Object[]{
                    r.getRid(),
                    r.getRoadName(),
                    r.getSuburb(),
                    r.getStatus()
            });
        }
    }

    // ✅ Setup All Button Listeners
    private void setupListeners() {

        // ✅ Assessment Logic
        panel.assessBtn.addActionListener(e -> {

            int row = panel.requestTable.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(panel,"Select request first");
                return;
            }

            int rid = Integer.parseInt(panel.requestTable.getValueAt(row,0).toString());
            String severity = panel.severityBox.getSelectedItem().toString();

            int priority = calculatePriority(severity);

            requestDAO.updateAssessment(rid,severity,priority);

            JOptionPane.showMessageDialog(panel,"Assessment Saved");
            loadRequests();
        });

        // ✅ Auto Schedule
        panel.scheduleBtn.addActionListener(e -> autoSchedule());

        // ✅ Mark Completed (Next Level Feature)
        panel.completeBtn.addActionListener(e -> {

            int row = panel.requestTable.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(panel,"Select request first");
                return;
            }

            int rid = Integer.parseInt(
                    panel.requestTable.getValueAt(row,0).toString()
            );

            requestDAO.markCompleted(rid);

            // ✅ Release Resources After Completion
            resourceDAO.releaseAllResources();

            JOptionPane.showMessageDialog(panel,"Work Completed & Resources Released");

            loadRequests();
        });
    }

    // ✅ Priority Calculation Based on Severity
    private int calculatePriority(String severity){

        switch(severity){
            case "CRITICAL": return 4;
            case "HIGH": return 3;
            case "MEDIUM": return 2;
            default: return 1;
        }
    }

    // ✅ Auto Scheduling Engine
    private void autoSchedule(){

        List<RepairRequest> list = requestDAO.getAllRequests();

        list.stream()
                .filter(r -> r.getStatus().equals("PENDING"))
                .sorted(Comparator.comparingInt(RepairRequest::getPriority).reversed())
                .forEach(r -> {

                    scheduleDAO.schedule(r.getRid());
                    requestDAO.updateStatus(r.getRid(),"SCHEDULED");
                });

        JOptionPane.showMessageDialog(panel,"Auto Scheduling Complete");

        loadRequests();
    }
}