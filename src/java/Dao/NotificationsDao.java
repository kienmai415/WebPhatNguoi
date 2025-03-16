/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Notifications;
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
public class NotificationsDao {
    public List<Notifications> getNotificationsByUser(int userID) {
    List<Notifications> notifications = new ArrayList<>();
    DBContext db = DBContext.getInstance();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = db.getConnection();
        String sql = "SELECT * FROM Notifications WHERE UserID = ? ORDER BY CreatedAt DESC";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userID);
        rs = stmt.executeQuery();

        while (rs.next()) {
            notifications.add(new Notifications(
                rs.getInt("NotificationID"),
                rs.getInt("UserID"),
                rs.getInt("Message"),
                rs.getString("PlateNumber"),
                rs.getString("SentDate"),
                rs.getBoolean("IsRead")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    return notifications;
}
}
