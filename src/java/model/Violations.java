/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maiki
 */
public class Violations {
    private int ViolationID;
    private int ReportID;
    private String PlateNumber;
    private int ViolatorID;
    private double FineAmount;
    private String FineDate;
    private boolean PaidStatus;

    public Violations() {
    }

    public Violations(int ViolationID, int ReportID, String PlateNumber, int ViolatorID, double FineAmount, String FineDate, boolean PaidStatus) {
        this.ViolationID = ViolationID;
        this.ReportID = ReportID;
        this.PlateNumber = PlateNumber;
        this.ViolatorID = ViolatorID;
        this.FineAmount = FineAmount;
        this.FineDate = FineDate;
        this.PaidStatus = PaidStatus;
    }

    public int getViolationID() {
        return ViolationID;
    }

    public void setViolationID(int ViolationID) {
        this.ViolationID = ViolationID;
    }

    public int getReportID() {
        return ReportID;
    }

    public void setReportID(int ReportID) {
        this.ReportID = ReportID;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String PlateNumber) {
        this.PlateNumber = PlateNumber;
    }

    public int getViolatorID() {
        return ViolatorID;
    }

    public void setViolatorID(int ViolatorID) {
        this.ViolatorID = ViolatorID;
    }

    public double getFineAmount() {
        return FineAmount;
    }

    public void setFineAmount(double FineAmount) {
        this.FineAmount = FineAmount;
    }

    public String getFineDate() {
        return FineDate;
    }

    public void setFineDate(String FineDate) {
        this.FineDate = FineDate;
    }

    public boolean isPaidStatus() {
        return PaidStatus;
    }

    public void setPaidStatus(boolean PaidStatus) {
        this.PaidStatus = PaidStatus;
    }
    
}
