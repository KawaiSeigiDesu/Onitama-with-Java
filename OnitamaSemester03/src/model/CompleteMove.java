/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class CompleteMove {
    private CustombuttonPieces pieceSelected;
    private CustomButton cardSelected;
    private CustombuttonPieces pileSelected;

    public CompleteMove() {
        this.pieceSelected = null;
        this.cardSelected = null;
        this.pileSelected = null;
    }

    @Override
    public String toString() {
        return "CompleteMove "+ super.toString() +"{" + "pieceSelected=" + pieceSelected + ", cardSelected=" + cardSelected + ", pileSelected=" + pileSelected + '}';
    }

    public CustombuttonPieces getPieceSelected() {
        return pieceSelected;
    }

    public CustomButton getCardSelected() {
        return cardSelected;
    }

    public CustombuttonPieces getPileSelected() {
        return pileSelected;
    }

    public void setPieceSelected(CustombuttonPieces pieceSelected) {
        this.pieceSelected = pieceSelected;
    }

    public void setCardSelected(CustomButton cardSelected) {
        this.cardSelected = cardSelected;
    }

    public void setPileSelected(CustombuttonPieces pileSelected) {
        this.pileSelected = pileSelected;
    }
    
    
}
