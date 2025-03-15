/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author maiki
 */
public class Users {
    private int UserID; 
    private String FullName;
    private String Email;
    private String Password;
    private int RoleID;
    private String Phone;
    private String Address;

    public Users() {
    }

    public Users(int UserID, String FullName, String Email, String Password, int RoleID, String Phone, String Address) {
        this.UserID = UserID;
        this.FullName = FullName;
        this.Email = Email;
        this.Password = Password;
        this.RoleID = RoleID;
        this.Phone = Phone;
        this.Address = Address;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @Override
    public String toString() {
        return "Users{" + "UserID=" + UserID + ", FullName=" + FullName + ", Email=" + Email + ", Password=" + Password + ", RoleID=" + RoleID + ", Phone=" + Phone + ", Address=" + Address + '}';
    }

    
}
