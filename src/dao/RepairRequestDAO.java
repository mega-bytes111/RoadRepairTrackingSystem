package dao;

import model.RepairRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepairRequestDAO {

    // ✅ Create New Repair Request
    public void create(String road, String suburb, String desc) {

        try(Connection con = DBConnection.getConnection()) {

            String sql = "INSERT INTO repair_requests(road_name, suburb, description) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, road);
            ps.setString(2, suburb);
            ps.setString(3, desc);

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Get All Requests
    public List<RepairRequest> getAllRequests() {

        List<RepairRequest> list = new ArrayList<>();

        try(Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM repair_requests";
            ResultSet rs = con.createStatement().executeQuery(sql);

            while(rs.next()) {

                list.add(new RepairRequest(
                        rs.getInt("rid"),
                        rs.getString("road_name"),
                        rs.getString("suburb"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("priority")
                ));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Update Severity & Priority (Assessment)
    public void updateAssessment(int rid, String severity, int priority){

        try(Connection con = DBConnection.getConnection()){

            String sql="UPDATE repair_requests SET severity=?, priority=? WHERE rid=?";
            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,severity);
            ps.setInt(2,priority);
            ps.setInt(3,rid);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // ✅ Update Status (Generic)
    public void updateStatus(int rid,String status){

        try(Connection con = DBConnection.getConnection()){

            String sql="UPDATE repair_requests SET status=? WHERE rid=?";
            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,status);
            ps.setInt(2,rid);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // ✅ NEW: Mark Road as Completed (Next Level Feature)
    public void markCompleted(int rid){

        try(Connection con = DBConnection.getConnection()){

            String sql = "UPDATE repair_requests " +
                    "SET status=?, completion_date=NOW() " +
                    "WHERE rid=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,"COMPLETED");
            ps.setInt(2,rid);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}