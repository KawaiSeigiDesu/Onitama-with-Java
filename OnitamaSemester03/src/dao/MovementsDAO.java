package dao;

import database.JDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Movement;

public class MovementsDAO {
    
    
    public static ArrayList<Movement> getMovements(){
        ArrayList<Movement> listMovements = new ArrayList<>();
        try{
            Connection connection = JDBC.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT TOP 5 * FROM Movements ORDER BY NEWID()";
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                listMovements.add(new Movement(
                        resultSet.getInt("id"), 
                        resultSet.getString("name"), 
                        resultSet.getBytes("binaryImage")) );
            }
            resultSet.close();
            statement.close();
            connection.close();
            
            return listMovements;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public static byte[] getBytesByID(int id){
        try{
            Connection connection = JDBC.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT binaryImage FROM Movements Where id = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            
            byte[] myByte = resultSet.getBytes("binaryImage");
            resultSet.close();
            statement.close();
            connection.close();
            
            return myByte;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
