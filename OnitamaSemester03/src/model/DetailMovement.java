/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class DetailMovement {
    
    private String tag;
    private int col, row;

    public DetailMovement(String tag, int col, int row) {
        this.tag = tag;
        this.col = col;
        this.row = row;
    }

    public String getTag() {
        return tag;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
    

    @Override
    public String toString() {
        return "DetailMovement{" + "tag=" + tag + ", col=" + col + ", row=" + row + '}';
    }
}
