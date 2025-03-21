/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Users;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author maiki
 */
public class UsersDao {

    public static boolean registerUser(Users user) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        String sql = "INSERT INTO Users (FullName, Email, Password, RoleID, Phone, Address) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());// Đã sửa lỗi email -> password
            statement.setInt(4, 3);
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getAddress());

            rs = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return rs > 0;
    }

    public static Users authenticate(String email, String password) {
        DBContext db = DBContext.getInstance();
        Users user = null;
        try {
            String sql = "select * from Users where Email = ? and Password = ?"; //(2)
            PreparedStatement statment = db.getConnection().prepareStatement(sql); //(3)
            statment.setString(1, email);
            statment.setString(2, password);
            ResultSet rs = statment.executeQuery(); //(5)
            while (rs.next()) {
                user = new Users();
                user.setRoleID(rs.getInt("RoleID"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setFullName(rs.getString("FullName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean updatePassword(String email, String newPassword) {
        DBContext db = DBContext.getInstance();
        String sql = "UPDATE Users SET Password = ? WHERE Email = ?";
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setString(2, email);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProfile(String email, String fullName, String phone, String address) {
        DBContext db = DBContext.getInstance();
        String sql = "UPDATE Users SET FullName = ?, Phone = ?, Address = ? WHERE Email = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4, email);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có dòng nào đó được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu cập nhật thất bại
    }

    public static Users getUserByEmail(String email) {
        DBContext db = DBContext.getInstance();
        String sql = "SELECT * FROM Users WHERE Email = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Users user = new Users();
                user.setEmail(rs.getString("Email"));
                user.setFullName(rs.getString("FullName"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setPassword(rs.getString("Password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy user
    }

    public static void main(String[] args) {
        Users user = new Users();
        Users users = UsersDao.getUserByEmail("NguyenVanG@gmail.com");
        System.out.println(users);
    }

}
