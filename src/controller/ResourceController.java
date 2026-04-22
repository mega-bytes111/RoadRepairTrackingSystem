package controller;

import dao.ResourceDAO;
import model.Resource;
import view.AdminPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ResourceController {

    private AdminPanel panel;
    private ResourceDAO dao;

    public ResourceController(AdminPanel panel){
        this.panel = panel;
        this.dao = new ResourceDAO();

        loadResources();
        setupListeners();
    }

    private void loadResources(){

        List<Resource> list = dao.getAllResources();

        DefaultTableModel model =
                (DefaultTableModel) panel.resourceTable.getModel();

        model.setRowCount(0);

        for(Resource r : list){
            model.addRow(new Object[]{
                    r.getId(),
                    r.getName(),
                    r.getCategory(),
                    r.getAvailable()
            });
        }
    }

    private void setupListeners(){

        panel.refreshBtn.addActionListener(e -> loadResources());

        panel.addBtn.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("Enter Resource Name:");
            String category = JOptionPane.showInputDialog("Enter Category (MANPOWER/MACHINE/MATERIAL):");
            String qtyStr = JOptionPane.showInputDialog("Enter Quantity:");

            if(name == null || category == null || qtyStr == null)
                return;

            int qty = Integer.parseInt(qtyStr);

            dao.addResource(name, category, qty);

            loadResources();
        });
    }
}