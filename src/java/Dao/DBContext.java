/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 *
 * @author Vuh26
 */
public class DBContext { //singleton pattern
    //ket noi den co so du lieu cua minh
    private static DBContext instance   = new DBContext();
    Connection connection;
    
    public static DBContext getInstance(){
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    private DBContext(){
        try{
            if(connection == null || connection.isClosed()){
                String user = "sa"; //tk, mk go vao
                
                String password = "sa";
                //duong dan
                String url = "jdbc:sqlserver://localhost:1433;databaseName=AppGiaoThongPRJ301;user=sa;password=sa;";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(url, user, password);
                        
            }
        }catch(Exception e){
            e.printStackTrace();
            connection = null;
        }
    }
    public static void main(String[] args) {
        DBContext db = new DBContext();
        Connection con = db.getConnection();
        System.out.println(con);
    }
}
