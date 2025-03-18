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
import java.util.List;

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
            String sql = "select * from Users where Email = ? and Password = ? AND isActive = 1"; //(2)
            PreparedStatement statment = db.getConnection().prepareStatement(sql); //(3)
            statment.setString(1, email);
            statment.setString(2, password);
            ResultSet rs = statment.executeQuery(); //(5)
            while (rs.next()) {
                user = new Users();
                user.setUserID(rs.getInt("UserID"));
                user.setAddress(rs.getString("Address"));
                user.setRoleID(rs.getInt("RoleID"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setPassword(rs.getString("Password"));
                user.setFullName(rs.getString("FullName"));
                user.setActive(rs.getBoolean("isActive")); // ✅ Đảm bảo đọc cả trạng thái tài khoản
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

    public static Users getUserByUserId(int userID) {
        DBContext db = DBContext.getInstance();
        String sql = "SELECT * FROM Users WHERE UserID = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, userID);
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

    public boolean updateUserStatus(int userID, boolean isActive) {
        DBContext db = DBContext.getInstance();
        String sql = "UPDATE Users SET IsActive = ? WHERE UserID = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setBoolean(1, isActive);
            stmt.setInt(2, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Users> getUsersByRole(int roleID, boolean isActive) {
        DBContext db = DBContext.getInstance();
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE RoleID = ? AND isActive = ?";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, roleID);
            stmt.setBoolean(2, isActive);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Users user = new Users();
                user.setUserID(rs.getInt("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setActive(rs.getBoolean("isActive")); // ✅ Đảm bảo khớp với model

                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
    public List<Users> getUsersByRoleAndStatus(int role, boolean isActive) {
    DBContext db = DBContext.getInstance(); 
    List<Users> userList = new ArrayList<>();
    String query = "SELECT * FROM Users WHERE roleID = ? AND isActive = ?";

    try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
        stmt.setInt(1, role);
        stmt.setBoolean(2, isActive);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Users user = new Users();
                user.setUserID(rs.getInt("userID"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setActive(rs.getBoolean("isActive"));
                userList.add(user);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return userList;
}

    

    public static void main(String[] args) {
        Users user = new Users();
        Users users = UsersDao.getUserByEmail("NguyenVanG@gmail.com");
        System.out.println(users);
    }

}
