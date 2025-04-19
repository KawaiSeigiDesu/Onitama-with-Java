package model;

import javax.swing.Icon;

public class Piece {
    
    private String name;
    private int col, row;
    private Icon image;
    private boolean isblue;

    public Piece(String name, int col, int row, Icon image, boolean isblue) {
        this.name = name;
        this.col = col;
        this.row = row;
        this.image = image;
        this.isblue = isblue;
    }

    public String getName() {
        return name;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
    public void setisblue(boolean isblue){
        this.isblue = isblue;
    }

    public boolean isIsblue() {
        return isblue;
    }

    public Icon getImage() {
        return image;
    }
    
}
