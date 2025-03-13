/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Reports;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maiki
 */
public class ReportsDao {

    public static Reports getReportByUser(int ReporterId) {
        DBContext db = DBContext.getInstance();
        String sql = "  SELECT * FROM Reports WHERE ReporterID = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, ReporterId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Reports report = new Reports();
                    report.setReportID(rs.getInt("ReportID"));
                    report.setReporterID(rs.getInt("ReporterID"));
                    report.setViolationType(rs.getString("ViolationType"));
                    report.setDescription(rs.getString("Description"));
                    report.setPlateNumber(rs.getString("PlateNumber"));
                    report.setImageURL(rs.getString("ImageURL"));
                    report.setVideoURL(rs.getString("VideoURL"));
                    report.setLocation(rs.getString("Location"));
                    report.setReportDate(rs.getString("ReportDate"));
                    report.setStatus(rs.getString("Status"));
                    return report;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy user
    }

    private Connection conn;

    public ReportsDao() {
        this.conn = DBContext.getInstance().getConnection();
    }

    public boolean submitReport(Reports report) {
        String sql = "INSERT INTO Reports (ReporterID, ViolationType, Description, PlateNumber, ImageURL, VideoURL, Location, ReportDate, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, report.getReporterID());
            ps.setString(2, report.getViolationType());
            ps.setString(3, report.getDescription());
            ps.setString(4, report.getPlateNumber());
            ps.setString(5, report.getImageURL());
            ps.setString(6, report.getVideoURL());
            ps.setString(7, report.getLocation());
            ps.setString(8, report.getStatus());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi SQL khi insert: " + e.getMessage());
            return false;
        }

    }

    public static void main(String[] args) {
        Reports rp = new Reports();
        Reports rps = ReportsDao.getReportByUser(2);
        //System.out.println(rps);

    }
}
