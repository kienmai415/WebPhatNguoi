/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author maiki
 */
public class Notifications {
    private int NotificationID;
    private int UserID;
    private int Message;
    private String PlateNumber;
    private String SentDate;
    private boolean IsRead;

    public Notifications() {
    }

    public Notifications(int NotificationID, int UserID, int Message, String PlateNumber, String SentDate, boolean IsRead) {
        this.NotificationID = NotificationID;
        this.UserID = UserID;
        this.Message = Message;
        this.PlateNumber = PlateNumber;
        this.SentDate = SentDate;
        this.IsRead = IsRead;
    }

    public int getNotificationID() {
        return NotificationID;
    }

    public void setNotificationID(int NotificationID) {
        this.NotificationID = NotificationID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getMessage() {
        return Message;
    }

    public void setMessage(int Message) {
        this.Message = Message;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String PlateNumber) {
        this.PlateNumber = PlateNumber;
    }

    public String getSentDate() {
        return SentDate;
    }

    public void setSentDate(String SentDate) {
        this.SentDate = SentDate;
    }

    public boolean isIsRead() {
        return IsRead;
    }

    public void setIsRead(boolean IsRead) {
        this.IsRead = IsRead;
    }
    
}
