package dao;

import java.sql.*;

public class ScheduleDAO {

    public void schedule(int rid) {
        try(Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO schedules(rid) VALUES(?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, rid);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllSchedules() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM schedules";
            return con.createStatement().executeQuery(sql);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}