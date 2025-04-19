/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author ASUS
 */
public class CustombuttonPieces extends JButton{
    private Piece piece;
    private int col;
    private int row;

    public CustombuttonPieces(Piece piece, Icon icon, int col, int row) {
        super(icon);
        this.piece = piece;
        this.col = col;
        this.row = row;
    }


    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
