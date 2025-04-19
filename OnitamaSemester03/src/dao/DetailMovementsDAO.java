package dao;

import database.JDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.DetailMovement;

public class DetailMovementsDAO {
    
    public static DetailMovement extract(ResultSet resultSet){
        try {
            DetailMovement detailMovement = new DetailMovement(
                    resultSet.getString("tag"), 
                    resultSet.getInt("Col"),
                    resultSet.getInt("Row"));
            return detailMovement;
        } catch (SQLException ex) {
//            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ArrayList<DetailMovement> getDetailMovementsList(int id){
        
        ArrayList<DetailMovement> listDetailMovements = new ArrayList<>();
        
        try{
            Connection connection = JDBC.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String query = """
                           SELECT * FROM DetailMovements
                           WHERE Id_M = """ + id;
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                listDetailMovements.add(extract(resultSet));
            }
            resultSet.close();
            statement.close();
            connection.close();
            
            return listDetailMovements;
        }
        catch (SQLException ex){
//            throw new RuntimeException("SQL EXCEPTION error retrieving table users", ex);
            return null;
        }
    }
}
