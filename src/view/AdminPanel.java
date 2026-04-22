package view;

import controller.ResourceController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminPanel extends JPanel {

    public JTable resourceTable;
    public JButton addBtn;
    public JButton refreshBtn;

    public AdminPanel(){

        setLayout(new BorderLayout());

        resourceTable=new JTable();
        DefaultTableModel model=new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Category");
        model.addColumn("Available");
        resourceTable.setModel(model);

        add(new JScrollPane(resourceTable),BorderLayout.CENTER);

        JPanel bottom=new JPanel();
        addBtn=new JButton("Add Resource");
        refreshBtn=new JButton("Refresh");

        bottom.add(addBtn);
        bottom.add(refreshBtn);

        add(bottom,BorderLayout.SOUTH);

        new ResourceController(this);
    }
}