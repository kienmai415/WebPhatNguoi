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

    public static void main(String[] args) {
        Reports rp = new Reports();
        ReportsDao rpt = new ReportsDao();
        Reports rps = ReportsDao.getReportByUser(2);
        boolean report = rpt.submitReport(rp);
        rp.setReporterID(3);
        rp.setViolationType("no_helmet");
        rp.setDescription("Không đội mũ bảo hiểm");
        rp.setPlateNumber("30A-12345");
        rp.setImageURL("uploads/image.jpg");
        rp.setVideoURL("uploads/video.mp4");
        rp.setLocation("Hà Nội");
        rp.setReportDate("2024-03-14");
        rp.setStatus("Pending");
        ReportsDao rd = new ReportsDao();
         boolean result = rd.submitReport(rp);
        if (result) {
            System.out.println("✅ Báo cáo được lưu thành công!");
        } else {
            System.err.println("❌ Gửi báo cáo thất bại!");
        }
        //System.out.println(rps);

    }
}
