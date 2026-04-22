package view;

import dao.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class MayorPanel extends JPanel {

    private JTextArea statsArea;

    public MayorPanel(){

        setLayout(new BorderLayout());

        statsArea=new JTextArea();
        statsArea.setEditable(false);

        add(new JScrollPane(statsArea),BorderLayout.CENTER);

        loadStatistics();
    }

    private void loadStatistics(){

        try(Connection con= DBConnection.getConnection()){

            ResultSet total = con.createStatement().executeQuery(
                    "SELECT COUNT(*) as total FROM repair_requests"
            );
            total.next();

            ResultSet completed = con.createStatement().executeQuery(
                    "SELECT COUNT(*) as completed FROM repair_requests WHERE status='COMPLETED'"
            );
            completed.next();

            ResultSet pending = con.createStatement().executeQuery(
                    "SELECT COUNT(*) as pending FROM repair_requests WHERE status='PENDING'"
            );
            pending.next();

            StringBuilder sb=new StringBuilder();

            sb.append("📊 ROAD REPAIR STATISTICS\n\n");
            sb.append("Total Requests: ").append(total.getInt("total")).append("\n");
            sb.append("Completed: ").append(completed.getInt("completed")).append("\n");
            sb.append("Pending: ").append(pending.getInt("pending")).append("\n");

            statsArea.setText(sb.toString());

        }catch(Exception e){ e.printStackTrace();}
    }
}