package dao;

import model.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceDAO {

    // ✅ Get All Resources
    public List<Resource> getAllResources() {

        List<Resource> list = new ArrayList<>();

        try(Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM resources";
            ResultSet rs = con.createStatement().executeQuery(sql);

            while(rs.next()) {

                list.add(new Resource(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getInt("available")
                ));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Add New Resource
    public void addResource(String name, String category, int qty){

        try(Connection con = DBConnection.getConnection()){

            String sql="INSERT INTO resources(name,category,quantity,available) VALUES(?,?,?,?)";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,name);
            ps.setString(2,category);
            ps.setInt(3,qty);
            ps.setInt(4,qty);

            ps.executeUpdate();

        }catch(Exception e){ e.printStackTrace();}
    }

    // ✅ Release All Resources (After Road Completion)
    public void releaseAllResources(){

        try(Connection con = DBConnection.getConnection()){

            String sql="UPDATE resources SET available = quantity";
            PreparedStatement ps=con.prepareStatement(sql);

            ps.executeUpdate();

        }catch(Exception e){ e.printStackTrace();}
    }
}