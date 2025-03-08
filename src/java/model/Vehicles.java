/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maiki
 */
public class Vehicles {
    private int VehicleID; 
    private String PlateNumber;
    private int OwnerID; 
    private String Brand; 
    private String Model; 
    private String ManufactureYear;

    public Vehicles() {
    }

    public Vehicles(int VehicleID, String PlateNumber, int OwnerID, String Brand, String Model, String ManufactureYear) {
        this.VehicleID = VehicleID;
        this.PlateNumber = PlateNumber;
        this.OwnerID = OwnerID;
        this.Brand = Brand;
        this.Model = Model;
        this.ManufactureYear = ManufactureYear;
    }

    public int getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(int VehicleID) {
        this.VehicleID = VehicleID;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String PlateNumber) {
        this.PlateNumber = PlateNumber;
    }

    public int getOwnerID() {
        return OwnerID;
    }

    public void setOwnerID(int OwnerID) {
        this.OwnerID = OwnerID;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getManufactureYear() {
        return ManufactureYear;
    }

    public void setManufactureYear(String ManufactureYear) {
        this.ManufactureYear = ManufactureYear;
    }
    
}
