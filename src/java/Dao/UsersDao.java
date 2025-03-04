/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author maiki
 */
public class UsersDao {
    public static ArrayList<Users> getUsers() {
        DBContext db = DBContext.getInstance();
        ArrayList<Users> userList = new ArrayList<Users>();
        try {
            String sql = "select * from Users"; //(2)
            PreparedStatement statment = db.getConnection().prepareStatement(sql); //(3)
             ResultSet rs = statment.executeQuery(); //(5)
            while (rs.next()) {
                Users user = new Users(rs.getInt("UserID"), rs.getString("FullName"),rs.getString("Email"), rs.getString("Password"), rs.getInt("RoleID"), rs.getString("Phone"), rs.getString("Address"));
                userList.add(user);
            }
        } catch (Exception e) {
            return userList;
        }
            return userList;
        }
    public static void main(String[] args) {
        ArrayList<Users> us = getUsers();
        for (Users u : us) {
            System.out.println(us);
        }
    }
}
