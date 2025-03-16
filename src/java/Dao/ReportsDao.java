/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Reports;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maiki
 */
public class ReportsDao {

    public List<Reports> getReportsByUser(int userId) {
        List<Reports> reportList = new ArrayList<>();
        String sql = "SELECT * FROM Reports WHERE reporterID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reports report = new Reports();
                report.setReportID(rs.getInt("ReportID"));
                report.setReporterID(rs.getInt("ReporterID"));
                report.setViolationType(rs.getString("ViolationType"));
                report.setDescription(rs.getString("Description"));
                report.setPlateNumber(rs.getString("PlateNumber"));
                report.setLocation(rs.getString("Location"));
                report.setReportDate(rs.getString("ReportDate"));
                report.setStatus(rs.getString("Status"));
                report.setImageURL(rs.getString("ImageURL"));
                report.setVideoURL(rs.getString("VideoURL"));
                report.setProcessedBy(rs.getInt("ProcessedBy"));
                reportList.add(report);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;

    }
    

    private static final Logger LOGGER = Logger.getLogger(ReportsDao.class.getName());

    private Connection conn;

    public ReportsDao() {
        this.conn = DBContext.getInstance().getConnection();
    }

    public boolean submitReport(Reports report) {
        String sql = "INSERT INTO Reports (ReporterID, ViolationType, Description, PlateNumber, ImageURL, VideoURL, Location, ReportDate, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            LOGGER.log(Level.INFO, "📝 Chuẩn bị thêm báo cáo - ReporterID: {0}, ViolationType: {1}, PlateNumber: {2}", 
                new Object[]{report.getReporterID(), report.getViolationType(), report.getPlateNumber()});
            ps.setInt(1, report.getReporterID());
            ps.setString(2, report.getViolationType());
            ps.setString(3, report.getDescription());
            ps.setString(4, report.getPlateNumber());
            ps.setString(5, report.getImageURL());
            ps.setString(6, report.getVideoURL());
            ps.setString(7, report.getLocation());
            ps.setString(8, report.getReportDate());
            ps.setString(9, report.getStatus());

            int result = ps.executeUpdate();
            // Kiểm tra kết quả
        if (result > 0) {
            LOGGER.log(Level.INFO, "✅ Báo cáo đã được thêm vào DB! Rows affected: {0}", result);
            return true;
        } else {
            LOGGER.log(Level.WARNING, "⚠ Không có hàng nào được chèn vào database.");
            return false;
        }
    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "❌ Lỗi SQL khi thêm báo cáo vào DB", e);
        return false;
    }

    }
    public List<Reports> getReportsByStatus(String status) {
    List<Reports> reportsList = new ArrayList<>();
    DBContext db = DBContext.getInstance();
    String sql = "SELECT * FROM Reports WHERE Status = ? ORDER BY ReportDate DESC";

    try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
        stmt.setString(1, status);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Reports report = new Reports();
                report.setReportID(rs.getInt("ReportID"));
                report.setReporterID(rs.getInt("ReporterID"));
                report.setViolationType(rs.getString("ViolationType"));
                report.setDescription(rs.getString("Description"));
                report.setPlateNumber(rs.getString("PlateNumber"));
                report.setLocation(rs.getString("Location"));
                report.setReportDate(rs.getString("ReportDate"));
                report.setStatus(rs.getString("Status"));
                reportsList.add(report);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return reportsList;
}
        public boolean updateReportStatus(int reportId, String newStatus) {
    DBContext db = DBContext.getInstance();
    String sql = "UPDATE Reports SET Status = ? WHERE ReportID = ? AND Status = 'Pending'";

    try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
        stmt.setString(1, newStatus);
        stmt.setInt(2, reportId);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public static void main(String[] args) {
        Reports rp = new Reports();
        ReportsDao rpt = new ReportsDao();
        List<Reports> rps = rpt.getReportsByStatus("Approved");
//        boolean report = rpt.submitReport(rp);
//        rp.setReporterID(3);
//        rp.setViolationType("no_helmet");
//        rp.setDescription("Không đội mũ bảo hiểm");
//        rp.setPlateNumber("30A-12345");
//        rp.setImageURL("uploads/image.jpg");
//        rp.setVideoURL("uploads/video.mp4");
//        rp.setLocation("Hà Nội");
//        rp.setReportDate("2024-03-14");
//        rp.setStatus("Pending");
//        ReportsDao rd = new ReportsDao();
//         boolean result = rd.submitReport(rp);
//        if (result) {
//            System.out.println("✅ Báo cáo được lưu thành công!");
//        } else {
//            System.err.println("❌ Gửi báo cáo thất bại!");
//        }
        //System.out.println(rps);
        for (Reports rp1 : rps) {
            System.out.println(rp);
        }
    }
}
