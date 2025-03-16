/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Vehicles;
import Model.Vehicles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Phạm Quốc Tuấn
 */
public class VehiclesDao {
    public static Vehicles getVehicleByPlate(String plateNumber) {
        DBContext db = DBContext.getInstance();
        String sql = "SELECT * FROM Vehicles WHERE PlateNumber = ?"; // Truy vấn theo biển số xe

        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, plateNumber); // Gán biển số vào câu lệnh truy vấn
            ResultSet rs = stmt.executeQuery(); // Thực thi câu lệnh truy vấn

            if (rs.next()) {
                Vehicles vehicle = new Vehicles();
                vehicle.setVehicleID(rs.getInt("VehicleID"));
                vehicle.setPlateNumber(rs.getString("PlateNumber"));
                vehicle.setOwnerID(rs.getInt("OwnerID"));
                vehicle.setVehicleType(rs.getString("VehicleType"));
                vehicle.setBrand(rs.getString("Brand"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setManufactureYear(rs.getString("ManufactureYear"));
                return vehicle; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

}
