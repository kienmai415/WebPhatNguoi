/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author maiki
 */
public class Reports {
    private int ReportID;
    private int ReporterID;
    private String ViolationType ;
    private String Description;
    private String PlateNumber;
    private String ImageURL;
    private String VideoURL;
    private String Location;
    private String ReportDate;
    private String Status; 
    private int ProcessedBy;

    public Reports() {
    }

    public Reports(int ReportID, int ReporterID, String ViolationType, String Description, String PlateNumber, String ImageURL, String VideoURL, String Location, String ReportDate, String Status, int ProcessedBy) {
        this.ReportID = ReportID;
        this.ReporterID = ReporterID;
        this.ViolationType = ViolationType;
        this.Description = Description;
        this.PlateNumber = PlateNumber;
        this.ImageURL = ImageURL;
        this.VideoURL = VideoURL;
        this.Location = Location;
        this.ReportDate = ReportDate;
        this.Status = Status;
        this.ProcessedBy = ProcessedBy;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public String getDescription() {
        return Description;
    }

    public String getLocation() {
        return Location;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public int getProcessedBy() {
        return ProcessedBy;
    }

    public String getReportDate() {
        return ReportDate;
    }

    public int getReporterID() {
        return ReporterID;
    }

    public int getReportID() {
        return ReportID;
    }

    public String getStatus() {
        return Status;
    }

    public String getVideoURL() {
        return VideoURL;
    }

    public String getViolationType() {
        return ViolationType;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setPlateNumber(String PlateNumber) {
        this.PlateNumber = PlateNumber;
    }

    public void setProcessedBy(int ProcessedBy) {
        this.ProcessedBy = ProcessedBy;
    }

    public void setReportDate(String ReportDate) {
        this.ReportDate = ReportDate;
    }

    public void setReporterID(int ReporterID) {
        this.ReporterID = ReporterID;
    }

    public void setReportID(int ReportID) {
        this.ReportID = ReportID;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setVideoURL(String VideoURL) {
        this.VideoURL = VideoURL;
    }

    public void setViolationType(String ViolationType) {
        this.ViolationType = ViolationType;
    }

    @Override
    public String toString() {
        return "Reports{" + "ReportID=" + ReportID + ", ReporterID=" + ReporterID + ", ViolationType=" + ViolationType + ", Description=" + Description + ", PlateNumber=" + PlateNumber + ", ImageURL=" + ImageURL + ", VideoURL=" + VideoURL + ", Location=" + Location + ", ReportDate=" + ReportDate + ", Status=" + Status + ", ProcessedBy=" + ProcessedBy + '}';
    }

    

    

    
    
    
}
