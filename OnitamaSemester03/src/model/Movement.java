package model;

import dao.DetailMovementsDAO;
import java.util.ArrayList;


public class Movement {
    
    private int id_Movements;
    private String tag;
    private ArrayList<DetailMovement> listMovements;
    private byte[] binaryImage;
    private boolean isblue;

    public Movement(int id_Movements, String tag, byte[] binaryImage) {
        this.id_Movements = id_Movements;
        this.tag = tag;
        this.binaryImage = binaryImage;
        
        this.listMovements = DetailMovementsDAO.getDetailMovementsList(id_Movements);
    }
    
    public int getId_Movements() {
        return id_Movements;
    }

    public String getTag() {
        return tag;
    }

    public ArrayList<DetailMovement> getListMovements() {
        return listMovements;
    }

    public byte[] getbinaryImage() {
        return binaryImage;
    }

    public void setIsblue(boolean isblue) {
        this.isblue = isblue;
    }

    public boolean isIsblue() {
        return isblue;
    }
    
    
    
    @Override
    public String toString() {
        return "Movement{" + "id_Movements=" + id_Movements + ", tag=" + tag + ", listMovements=" + listMovements + '}';
    }
}
