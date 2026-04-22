package view;

import dao.RepairRequestDAO;

import javax.swing.*;
import java.awt.*;

public class ClerkPanel extends JPanel {

    public JTextField roadField = new JTextField(15);
    public JTextField suburbField = new JTextField(15);
    public JTextArea descArea = new JTextArea(3, 15);
    public JButton createBtn = new JButton("Create Request");

    public ClerkPanel() {

        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4,2,10,10));

        form.add(new JLabel("Road Name:"));
        form.add(roadField);
        form.add(new JLabel("Suburb:"));
        form.add(suburbField);
        form.add(new JLabel("Description:"));
        form.add(new JScrollPane(descArea));
        form.add(new JLabel(""));
        form.add(createBtn);

        add(form, BorderLayout.NORTH);

        createBtn.addActionListener(e -> createRequest());
    }

    private void createRequest() {

        if(roadField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Road required");
            return;
        }

        new RepairRequestDAO().create(
                roadField.getText(),
                suburbField.getText(),
                descArea.getText()
        );

        JOptionPane.showMessageDialog(this,"Request Created");
    }
}