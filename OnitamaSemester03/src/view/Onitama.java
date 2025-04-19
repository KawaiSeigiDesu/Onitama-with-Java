/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.MovementsDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static method.Convert.encryptCOL;
import static method.Convert.encryptROW;
import static method.Convert.decryptCOL;
import static method.Convert.decryptROW;
import model.CompleteMove;
import model.CustomButton;
import model.CustombuttonPieces;
import model.DetailMovement;
import model.Movement;
import model.Piece;

/**
 *
 * @author LENOVO
 */
public class Onitama extends javax.swing.JFrame {

    private ArrayList<Movement> movements;
    private ArrayList<Piece> redTeam = new ArrayList<>();
    private ArrayList<Piece> blueTeam = new ArrayList<>();
    
    private Piece redKing = new Piece("Red King",2,0, new ImageIcon(getClass().getResource("/assets/RedKing.png")) ,false);
    private Piece redSoldier1 = new Piece("Red Soldier 1",0,0,new ImageIcon(getClass().getResource("/assets/RedPawn.png")),false);
    private Piece redsoldier2 = new Piece("Red Soldier 2",1,0,new ImageIcon(getClass().getResource("/assets/RedPawn.png")),false);
    private Piece redsoldier3 = new Piece("Red Soldier 3",3,0,new ImageIcon(getClass().getResource("/assets/RedPawn.png")),false);
    private Piece redsoldier4 = new Piece ("Red Soldier 4",4,0,new ImageIcon(getClass().getResource("/assets/RedPawn.png")),false);
    
    
    
    private Piece blueKing = new Piece("Blue King",2,4,new ImageIcon(getClass().getResource("/assets/BlueKing.png")),true);
    private Piece bluesoldier1 = new Piece("Blue Soldier 1",0,4,new ImageIcon(getClass().getResource("/assets/BluePawn.jpg")),true);
    private Piece bluesoldier2 = new Piece("Blue Soldier 2",1,4,new ImageIcon(getClass().getResource("/assets/BluePawn.jpg")),true);
    private Piece bluesoldier3 = new Piece("Blue Soldier 3",3,4,new ImageIcon(getClass().getResource("/assets/BluePawn.jpg")),true);
    private Piece bluesoldier4 = new Piece("Blue Soldier 4",4,4,new ImageIcon(getClass().getResource("/assets/BluePawn.jpg")),true);
    
    private CompleteMove completemove = new CompleteMove();
    
    private CustombuttonPieces[][] board = new CustombuttonPieces[5][5];
    private CustomButton[] cards = new CustomButton[5];
    
    private  ArrayList<CustombuttonPieces> temp = new ArrayList<>();
    
    private static Onitama instance;
    
    /**
     * Creates new form OnitamaSemester03
     */
    public Onitama() {
        initComponents();
        init();
    }
    
    public static Onitama getInstance(){
        if(instance == null){
            instance = new Onitama();
        }
        
        return instance;
    }
    
    private void init(){
        movements = MovementsDAO.getMovements();
        
        boardButton(redSoldier1, pc0r0,redSoldier1.getImage(), 0, 0);
        boardButton(redsoldier2, pc1r0,redsoldier2.getImage() , 1, 0);
        boardButton(redKing,     pc2r0,redKing.getImage(), 2, 0);
        boardButton(redsoldier3, pc3r0,redsoldier3.getImage(), 3, 0);
        boardButton(redsoldier4, pc4r0,redsoldier4.getImage(), 4, 0);
        
        boardButton(null, pc0r1,null, 0, 1);
        boardButton(null, pc1r1,null, 1, 1);
        boardButton(null, pc2r1,null, 2, 1);
        boardButton(null, pc3r1,null,3,1);
        boardButton(null, pc4r1,null,4,1);
        
        boardButton(null, pc0r2,null,0,2);
        boardButton(null, pc1r2,null,1,2);
        boardButton(null, pc2r2,null,2,2);
        boardButton(null, pc3r2,null,3,2);
        boardButton(null, pc4r2,null,4,2);
        
        boardButton(null, pc0r3,null,0,3);
        boardButton(null, pc1r3,null,1,3);
        boardButton(null, pc2r3,null,2,3);
        boardButton(null, pc3r3,null,3,3);
        boardButton(null, pc4r3,null,4,3);
        
        boardButton(bluesoldier1, pc0r4,bluesoldier1.getImage(),0,4);
        boardButton(bluesoldier2, pc1r4,bluesoldier2.getImage(),1,4);
        boardButton(blueKing,     pc2r4,blueKing.getImage(),2,4);
        boardButton(bluesoldier3, pc3r4,bluesoldier3.getImage(),3,4);
        boardButton(bluesoldier4, pc4r4,bluesoldier4.getImage(),4,4);
        
        movements.get(4).setIsblue(true);
        movements.get(3).setIsblue(true);
        movements.get(2).setIsblue(false);
        movements.get(1).setIsblue(false);
        movements.get(0).setIsblue(true);
        
        panelButton(movements.remove(0), bluecard_Panel1, 0);
        panelButton(movements.remove(0), bluecard_Panel2,1);
        panelButton(movements.remove(0), redcard_Panel1,2);
        panelButton(movements.remove(0), redcard_Panel2,3);
        cards[4]= new CustomButton(null);
        bank_Label.setIcon(new ImageIcon(movements.get(0).getbinaryImage()));
        
        redTeam.add(redKing);
        redTeam.add(redSoldier1);
        redTeam.add(redsoldier2);
        redTeam.add(redsoldier3);
        redTeam.add(redsoldier4);
        
        blueTeam.add(blueKing); 
        blueTeam.add(bluesoldier1); 
        blueTeam.add(bluesoldier2); 
        blueTeam.add(bluesoldier3); 
        blueTeam.add(bluesoldier4); 
    }
    
    private void testButton(CustombuttonPieces data){
        if (data.getPiece() == null){
            completemove.setPileSelected(data);
        }
        else{
            if (data.getPiece().isIsblue()==true){
                completemove.setPieceSelected(data);
            }
            else{
                completemove.setPileSelected(data);
            }
        }
    }
    
    private void panelButton(Movement movement, JPanel panel, int index){
        CustomButton movementCard_Button = new CustomButton(movement,panel);
        movementCard_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completemove.setCardSelected( movementCard_Button);
                check();
            }
        });
        movementCard_Button.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.add(movementCard_Button);
        if(index>1)
            movementCard_Button.setEnabled(false);
        else
            movementCard_Button.setEnabled(true);
        
        cards[index] = movementCard_Button;
    }
    
    private void boardButton(Piece piece, JPanel panel, Icon icon, int col, int row){
        CustombuttonPieces data = new CustombuttonPieces(piece, icon ,col,row);
        data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testButton(data);
                check();
            }
        });

        board[col][row] = data;
        data.setBounds(0, 0, 50, 50);
        panel.add(data);
        if(piece == null)
            data.setEnabled(false);
        else{
            data.setEnabled(piece.isIsblue());
        }
    }
    
    private void getSwitchturn( ){
//        Reset all button board
        for (int col=0; col<5;col++){
            for(int row=0;row<5;row++){
                board[col][row].setIcon(null);
                board[col][row].setPiece(null);
                board[col][row].setEnabled(false);
                board[col][row].setBackground(Color.white);
            }
        }
        
//        switch and set the piece and board buttons
        for (Piece p : blueTeam){
            p.setisblue(!p.isIsblue());
            p.setCol(decryptCOL(encryptCOL(p.getCol()) * -1 )  );
            p.setRow(decryptROW(encryptROW(p.getRow()) * -1 ) );
            setBoardPieces(p, p.isIsblue());
        }
        
        for (Piece p : redTeam){
            p.setisblue(!p.isIsblue());
            p.setCol(decryptCOL(encryptCOL(p.getCol()) * -1 )  );
            p.setRow(decryptROW(encryptROW(p.getRow()) * -1 ) );
            setBoardPieces(p, p.isIsblue());
        }
        
//        switch the enemy and allies movement and cards movement
        cards[4].setMovement(cards[0].getMovement());
        cards[0].setMovement(cards[3].getMovement());
        cards[3].setMovement(cards[1].getMovement());
        cards[1].setMovement(cards[2].getMovement());
        cards[2].setMovement(cards[4].getMovement());
        
        cards[0].setIcon(new ImageIcon(cards[0].getMovement().getbinaryImage()));
        cards[1].setIcon(new ImageIcon(cards[1].getMovement().getbinaryImage()));
        cards[2].setIcon(new ImageIcon(cards[2].getMovement().getbinaryImage()));
        cards[3].setIcon(new ImageIcon(cards[3].getMovement().getbinaryImage()));
    }
    
    private void eat(){
        if (completemove.getPileSelected().getPiece().isIsblue() != true){
//            Remove target that will be killed from red or blue team
            for (int i=0 ; i<redTeam.size()-1 ; i++){
                if(completemove.getPileSelected().getPiece() == redTeam.get(i))
                    redTeam.remove(i);
            }
            for (int i=0 ; i<blueTeam.size()-1 ; i++){
                if(completemove.getPileSelected().getPiece() == blueTeam.get(i))
                    blueTeam.remove(i);
            }
            
            completemove.getPieceSelected().getPiece().setCol(completemove.getPileSelected().getCol());
            completemove.getPieceSelected().getPiece().setRow(completemove.getPileSelected().getRow());
            completemove.getPileSelected().setPiece(completemove.getPieceSelected().getPiece());
            
            completemove.getPileSelected().setIcon(completemove.getPieceSelected().getIcon());
            completemove.getPieceSelected().setIcon(null);
            
            completemove.getPieceSelected().setPiece(null);
            completemove.getPieceSelected().setPiece(null);
        }
    }
    
    private void placement(){
        
        if (completemove.getPileSelected() != null){
//            >> Regular Moveents
            if (completemove.getPileSelected().getPiece() == null){
                System.out.println("Move");
                completemove.getPieceSelected().setEnabled(false);
                completemove.getPieceSelected().getPiece().setCol(completemove.getPileSelected().getCol());
                completemove.getPieceSelected().getPiece().setRow(completemove.getPileSelected().getRow());

                completemove.getPileSelected().setPiece(completemove.getPieceSelected().getPiece());

                completemove.getPieceSelected().setPiece(null);
                completemove.getPieceSelected().setIcon(null);
            }
//            >> Kill/Eating Movements
            else if(completemove.getPileSelected().getPiece() != null){
                System.out.println("eating");
                if (completemove.getPileSelected().getPiece().getName().contains("King")){
                        if(completemove.getPieceSelected().getPiece().getName().contains("Red")){
                            String message = "CONGRATS PLAYER RED WIN !!";
                            JOptionPane.showMessageDialog(rootPane, message);
                            instance = null;
                            dispose();
                        }
                        else if (completemove.getPieceSelected().getPiece().getName().contains("Blue")){
                            String message = "CONGRATS PLAYER BLUE WIN !!";
                            JOptionPane.showMessageDialog(rootPane, message);
                            instance = null;
                            dispose();
                        }
//                        else if (completemove.getPileSelected().getCol() == 0 && completemove.getPileSelected().getCol() == 0 )
                    }
                eat();
            }
//            Condition To End Game or Win
            if (completemove.getPileSelected().getCol() == 2 && completemove.getPileSelected().getRow()== 0 ){
                if(completemove.getPileSelected().getPiece().getName() =="Red King"){
                            String message = "CONGRATS PLAYER RED WIN !!";
                            JOptionPane.showMessageDialog(rootPane, message);
                            instance = null;
                            dispose();
                        }
                        else if (completemove.getPileSelected().getPiece().getName() == "Blue King"){
                            String message = "CONGRATS PLAYER BLUE WIN !!";
                            JOptionPane.showMessageDialog(rootPane, message);
                            instance = null;
                            dispose();
                        }
            }
//            Selected Cards Movements Change with Movements in bank
            movements.add(completemove.getCardSelected().getMovement());
            Movement bank1 = movements.get(1);
            bank1.setIsblue(!bank1.isIsblue());
            completemove.getCardSelected().setMovement(movements.remove(0));
            bank_Label.setIcon(new ImageIcon(movements.get(0).getbinaryImage()));
            
//            Switch the board and movements
            getSwitchturn();
            
//            make the color different for legal movements
            if(completemove.getPileSelected().getPiece() != null){
                for (CustombuttonPieces p : temp){
                    if(p.getPiece() != null){
                        p.setEnabled(p.getPiece().isIsblue());
                        p.setBackground(Color.white);
                    }
                    else{
                        p.setEnabled(false);
                        p.setBackground(Color.white);
                    }
                }
                temp.removeAll(temp);
            }
        }
        else{
            System.out.println("completemove.getPileSelected() = null, Placement() 323 Error");
        }
    }
    
    private void movementEnable(Movement movements, CustombuttonPieces button){
//        encrypt (01234)col row to (210-1-2) and + with movements then decrypt it
       ArrayList<DetailMovement> movement = movements.getListMovements();
       for (int detail=0; detail <= movement.size()-1; detail++){
           int totalcol = decryptCOL( movement.get(detail).getCol() + encryptCOL(button.getPiece().getCol()) );
           int totalrow = decryptROW( movement.get(detail).getRow() + encryptROW(button.getPiece().getRow()) );
           
           if ((totalrow>=0 && totalrow<=4) && (totalcol>=0 && totalcol<=4)){
               if(board[totalcol][totalrow].getPiece() != null){
                   if(board[totalcol][totalrow].getPiece().isIsblue() != true){
                       board[totalcol][totalrow].setBackground(Color.red);
                       board[totalcol][totalrow].setEnabled(true);
                   }
               }
               else{
                   board[totalcol][totalrow].setBackground(Color.red);
                   board[totalcol][totalrow].setEnabled(true);
               }
               temp.add(board[totalcol][totalrow]);
           }
       }
    }
    
    private void check(){
//        Enabled some Board Button that have icon or not null
        for (int col=0;col<5;col++){
            for (int row=0;row<5; row++){
                if (board[col][row].getIcon() != null){
                    board[col][row].setEnabled(board[col][row].getPiece().isIsblue());
                    board[col][row].setBackground(Color.white);
                }
                else{
                    board[col][row].setEnabled(false);
                }
            }
        }
//        get legal movement
        if ((completemove.getPieceSelected() != null) && (completemove.getCardSelected() != null)){
            movementEnable(completemove.getCardSelected().getMovement(), completemove.getPieceSelected());
            
            if(completemove.getPileSelected() != null){
                if (completemove.getPileSelected().getPiece() != null){
                    placement();
                    
                }
                else{
                    placement();
                }
                completemove = new CompleteMove();
            }
        }
    }
    
    private void setBoardPieces(Piece nextdata, boolean onOff){
//        Set icon, piece, enabled the board
       board[nextdata.getCol()][nextdata.getRow()].setIcon(nextdata.getImage());
       board[nextdata.getCol()][nextdata.getRow()].setPiece(nextdata);
       board[nextdata.getCol()][nextdata.getRow()].setEnabled(onOff);
    }
    
    /** 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        board_Panel = new javax.swing.JPanel();
        pc0r0 = new javax.swing.JPanel();
        pc1r0 = new javax.swing.JPanel();
        pc2r0 = new javax.swing.JPanel();
        pc3r0 = new javax.swing.JPanel();
        pc4r0 = new javax.swing.JPanel();
        pc0r1 = new javax.swing.JPanel();
        pc1r1 = new javax.swing.JPanel();
        pc2r1 = new javax.swing.JPanel();
        pc3r1 = new javax.swing.JPanel();
        pc4r1 = new javax.swing.JPanel();
        pc0r2 = new javax.swing.JPanel();
        pc1r2 = new javax.swing.JPanel();
        pc2r2 = new javax.swing.JPanel();
        pc3r2 = new javax.swing.JPanel();
        pc4r2 = new javax.swing.JPanel();
        pc0r3 = new javax.swing.JPanel();
        pc1r3 = new javax.swing.JPanel();
        pc2r3 = new javax.swing.JPanel();
        pc3r3 = new javax.swing.JPanel();
        pc4r3 = new javax.swing.JPanel();
        pc0r4 = new javax.swing.JPanel();
        pc1r4 = new javax.swing.JPanel();
        pc2r4 = new javax.swing.JPanel();
        pc3r4 = new javax.swing.JPanel();
        pc4r4 = new javax.swing.JPanel();
        redcard_Panel1 = new javax.swing.JPanel();
        redcard_Panel2 = new javax.swing.JPanel();
        bluecard_Panel1 = new javax.swing.JPanel();
        bluecard_Panel2 = new javax.swing.JPanel();
        bankcard_Panel = new javax.swing.JPanel();
        bank_Label = new javax.swing.JLabel();
        exit_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        board_Panel.setBackground(new java.awt.Color(255, 204, 102));
        board_Panel.setPreferredSize(new java.awt.Dimension(250, 260));
        board_Panel.setLayout(new java.awt.GridBagLayout());

        pc0r0.setBackground(new java.awt.Color(255, 153, 0));
        pc0r0.setEnabled(false);
        pc0r0.setMaximumSize(new java.awt.Dimension(72, 72));
        pc0r0.setMinimumSize(new java.awt.Dimension(72, 72));
        pc0r0.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc0r0Layout = new javax.swing.GroupLayout(pc0r0);
        pc0r0.setLayout(pc0r0Layout);
        pc0r0Layout.setHorizontalGroup(
            pc0r0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );
        pc0r0Layout.setVerticalGroup(
            pc0r0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        board_Panel.add(pc0r0, gridBagConstraints);

        pc1r0.setBackground(new java.awt.Color(255, 153, 0));
        pc1r0.setEnabled(false);
        pc1r0.setMaximumSize(new java.awt.Dimension(72, 72));
        pc1r0.setMinimumSize(new java.awt.Dimension(72, 72));
        pc1r0.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc1r0Layout = new javax.swing.GroupLayout(pc1r0);
        pc1r0.setLayout(pc1r0Layout);
        pc1r0Layout.setHorizontalGroup(
            pc1r0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc1r0Layout.setVerticalGroup(
            pc1r0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        board_Panel.add(pc1r0, gridBagConstraints);

        pc2r0.setBackground(new java.awt.Color(255, 153, 0));
        pc2r0.setEnabled(false);
        pc2r0.setMaximumSize(new java.awt.Dimension(72, 72));
        pc2r0.setMinimumSize(new java.awt.Dimension(72, 72));
        pc2r0.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc2r0Layout = new javax.swing.GroupLayout(pc2r0);
        pc2r0.setLayout(pc2r0Layout);
        pc2r0Layout.setHorizontalGroup(
            pc2r0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc2r0Layout.setVerticalGroup(
            pc2r0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        board_Panel.add(pc2r0, gridBagConstraints);

        pc3r0.setBackground(new java.awt.Color(255, 153, 0));
        pc3r0.setEnabled(false);
        pc3r0.setMaximumSize(new java.awt.Dimension(72, 72));
        pc3r0.setMinimumSize(new java.awt.Dimension(72, 72));
        pc3r0.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc3r0Layout = new javax.swing.GroupLayout(pc3r0);
        pc3r0.setLayout(pc3r0Layout);
        pc3r0Layout.setHorizontalGroup(
            pc3r0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc3r0Layout.setVerticalGroup(
            pc3r0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        board_Panel.add(pc3r0, gridBagConstraints);

        pc4r0.setBackground(new java.awt.Color(255, 153, 0));
        pc4r0.setEnabled(false);
        pc4r0.setMaximumSize(new java.awt.Dimension(72, 72));
        pc4r0.setMinimumSize(new java.awt.Dimension(72, 72));
        pc4r0.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc4r0Layout = new javax.swing.GroupLayout(pc4r0);
        pc4r0.setLayout(pc4r0Layout);
        pc4r0Layout.setHorizontalGroup(
            pc4r0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc4r0Layout.setVerticalGroup(
            pc4r0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        board_Panel.add(pc4r0, gridBagConstraints);

        pc0r1.setBackground(new java.awt.Color(255, 153, 0));
        pc0r1.setEnabled(false);
        pc0r1.setMaximumSize(new java.awt.Dimension(72, 72));
        pc0r1.setMinimumSize(new java.awt.Dimension(72, 72));
        pc0r1.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc0r1Layout = new javax.swing.GroupLayout(pc0r1);
        pc0r1.setLayout(pc0r1Layout);
        pc0r1Layout.setHorizontalGroup(
            pc0r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc0r1Layout.setVerticalGroup(
            pc0r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        board_Panel.add(pc0r1, gridBagConstraints);

        pc1r1.setBackground(new java.awt.Color(255, 153, 0));
        pc1r1.setEnabled(false);
        pc1r1.setMaximumSize(new java.awt.Dimension(72, 72));
        pc1r1.setMinimumSize(new java.awt.Dimension(72, 72));
        pc1r1.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc1r1Layout = new javax.swing.GroupLayout(pc1r1);
        pc1r1.setLayout(pc1r1Layout);
        pc1r1Layout.setHorizontalGroup(
            pc1r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc1r1Layout.setVerticalGroup(
            pc1r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        board_Panel.add(pc1r1, gridBagConstraints);

        pc2r1.setBackground(new java.awt.Color(255, 153, 0));
        pc2r1.setEnabled(false);
        pc2r1.setMaximumSize(new java.awt.Dimension(72, 72));
        pc2r1.setMinimumSize(new java.awt.Dimension(72, 72));
        pc2r1.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc2r1Layout = new javax.swing.GroupLayout(pc2r1);
        pc2r1.setLayout(pc2r1Layout);
        pc2r1Layout.setHorizontalGroup(
            pc2r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc2r1Layout.setVerticalGroup(
            pc2r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        board_Panel.add(pc2r1, gridBagConstraints);

        pc3r1.setBackground(new java.awt.Color(255, 153, 0));
        pc3r1.setEnabled(false);
        pc3r1.setMaximumSize(new java.awt.Dimension(72, 72));
        pc3r1.setMinimumSize(new java.awt.Dimension(72, 72));
        pc3r1.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc3r1Layout = new javax.swing.GroupLayout(pc3r1);
        pc3r1.setLayout(pc3r1Layout);
        pc3r1Layout.setHorizontalGroup(
            pc3r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc3r1Layout.setVerticalGroup(
            pc3r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        board_Panel.add(pc3r1, gridBagConstraints);

        pc4r1.setBackground(new java.awt.Color(255, 153, 0));
        pc4r1.setEnabled(false);
        pc4r1.setMaximumSize(new java.awt.Dimension(72, 72));
        pc4r1.setMinimumSize(new java.awt.Dimension(72, 72));
        pc4r1.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc4r1Layout = new javax.swing.GroupLayout(pc4r1);
        pc4r1.setLayout(pc4r1Layout);
        pc4r1Layout.setHorizontalGroup(
            pc4r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc4r1Layout.setVerticalGroup(
            pc4r1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        board_Panel.add(pc4r1, gridBagConstraints);

        pc0r2.setBackground(new java.awt.Color(255, 153, 0));
        pc0r2.setEnabled(false);
        pc0r2.setMaximumSize(new java.awt.Dimension(72, 72));
        pc0r2.setMinimumSize(new java.awt.Dimension(72, 72));
        pc0r2.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc0r2Layout = new javax.swing.GroupLayout(pc0r2);
        pc0r2.setLayout(pc0r2Layout);
        pc0r2Layout.setHorizontalGroup(
            pc0r2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc0r2Layout.setVerticalGroup(
            pc0r2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        board_Panel.add(pc0r2, gridBagConstraints);

        pc1r2.setBackground(new java.awt.Color(255, 153, 0));
        pc1r2.setEnabled(false);
        pc1r2.setMaximumSize(new java.awt.Dimension(72, 72));
        pc1r2.setMinimumSize(new java.awt.Dimension(72, 72));
        pc1r2.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc1r2Layout = new javax.swing.GroupLayout(pc1r2);
        pc1r2.setLayout(pc1r2Layout);
        pc1r2Layout.setHorizontalGroup(
            pc1r2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc1r2Layout.setVerticalGroup(
            pc1r2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        board_Panel.add(pc1r2, gridBagConstraints);

        pc2r2.setBackground(new java.awt.Color(255, 153, 0));
        pc2r2.setEnabled(false);
        pc2r2.setMaximumSize(new java.awt.Dimension(72, 72));
        pc2r2.setMinimumSize(new java.awt.Dimension(72, 72));
        pc2r2.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc2r2Layout = new javax.swing.GroupLayout(pc2r2);
        pc2r2.setLayout(pc2r2Layout);
        pc2r2Layout.setHorizontalGroup(
            pc2r2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc2r2Layout.setVerticalGroup(
            pc2r2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        board_Panel.add(pc2r2, gridBagConstraints);

        pc3r2.setBackground(new java.awt.Color(255, 153, 0));
        pc3r2.setEnabled(false);
        pc3r2.setMaximumSize(new java.awt.Dimension(72, 72));
        pc3r2.setMinimumSize(new java.awt.Dimension(72, 72));
        pc3r2.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc3r2Layout = new javax.swing.GroupLayout(pc3r2);
        pc3r2.setLayout(pc3r2Layout);
        pc3r2Layout.setHorizontalGroup(
            pc3r2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc3r2Layout.setVerticalGroup(
            pc3r2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        board_Panel.add(pc3r2, gridBagConstraints);

        pc4r2.setBackground(new java.awt.Color(255, 153, 0));
        pc4r2.setEnabled(false);
        pc4r2.setMaximumSize(new java.awt.Dimension(72, 72));
        pc4r2.setMinimumSize(new java.awt.Dimension(72, 72));
        pc4r2.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc4r2Layout = new javax.swing.GroupLayout(pc4r2);
        pc4r2.setLayout(pc4r2Layout);
        pc4r2Layout.setHorizontalGroup(
            pc4r2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc4r2Layout.setVerticalGroup(
            pc4r2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        board_Panel.add(pc4r2, gridBagConstraints);

        pc0r3.setBackground(new java.awt.Color(255, 153, 0));
        pc0r3.setEnabled(false);
        pc0r3.setMaximumSize(new java.awt.Dimension(72, 72));
        pc0r3.setMinimumSize(new java.awt.Dimension(72, 72));
        pc0r3.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc0r3Layout = new javax.swing.GroupLayout(pc0r3);
        pc0r3.setLayout(pc0r3Layout);
        pc0r3Layout.setHorizontalGroup(
            pc0r3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc0r3Layout.setVerticalGroup(
            pc0r3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        board_Panel.add(pc0r3, gridBagConstraints);

        pc1r3.setBackground(new java.awt.Color(255, 153, 0));
        pc1r3.setEnabled(false);
        pc1r3.setMaximumSize(new java.awt.Dimension(72, 72));
        pc1r3.setMinimumSize(new java.awt.Dimension(72, 72));
        pc1r3.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc1r3Layout = new javax.swing.GroupLayout(pc1r3);
        pc1r3.setLayout(pc1r3Layout);
        pc1r3Layout.setHorizontalGroup(
            pc1r3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc1r3Layout.setVerticalGroup(
            pc1r3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        board_Panel.add(pc1r3, gridBagConstraints);

        pc2r3.setBackground(new java.awt.Color(255, 153, 0));
        pc2r3.setEnabled(false);
        pc2r3.setMaximumSize(new java.awt.Dimension(72, 72));
        pc2r3.setMinimumSize(new java.awt.Dimension(72, 72));
        pc2r3.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc2r3Layout = new javax.swing.GroupLayout(pc2r3);
        pc2r3.setLayout(pc2r3Layout);
        pc2r3Layout.setHorizontalGroup(
            pc2r3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc2r3Layout.setVerticalGroup(
            pc2r3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        board_Panel.add(pc2r3, gridBagConstraints);

        pc3r3.setBackground(new java.awt.Color(255, 153, 0));
        pc3r3.setEnabled(false);
        pc3r3.setMaximumSize(new java.awt.Dimension(72, 72));
        pc3r3.setMinimumSize(new java.awt.Dimension(72, 72));
        pc3r3.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc3r3Layout = new javax.swing.GroupLayout(pc3r3);
        pc3r3.setLayout(pc3r3Layout);
        pc3r3Layout.setHorizontalGroup(
            pc3r3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc3r3Layout.setVerticalGroup(
            pc3r3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        board_Panel.add(pc3r3, gridBagConstraints);

        pc4r3.setBackground(new java.awt.Color(255, 153, 0));
        pc4r3.setEnabled(false);
        pc4r3.setMaximumSize(new java.awt.Dimension(72, 72));
        pc4r3.setMinimumSize(new java.awt.Dimension(72, 72));
        pc4r3.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc4r3Layout = new javax.swing.GroupLayout(pc4r3);
        pc4r3.setLayout(pc4r3Layout);
        pc4r3Layout.setHorizontalGroup(
            pc4r3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc4r3Layout.setVerticalGroup(
            pc4r3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        board_Panel.add(pc4r3, gridBagConstraints);

        pc0r4.setBackground(new java.awt.Color(255, 153, 0));
        pc0r4.setEnabled(false);
        pc0r4.setMaximumSize(new java.awt.Dimension(72, 72));
        pc0r4.setMinimumSize(new java.awt.Dimension(72, 72));
        pc0r4.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc0r4Layout = new javax.swing.GroupLayout(pc0r4);
        pc0r4.setLayout(pc0r4Layout);
        pc0r4Layout.setHorizontalGroup(
            pc0r4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc0r4Layout.setVerticalGroup(
            pc0r4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        board_Panel.add(pc0r4, gridBagConstraints);

        pc1r4.setBackground(new java.awt.Color(255, 153, 0));
        pc1r4.setEnabled(false);
        pc1r4.setMaximumSize(new java.awt.Dimension(72, 72));
        pc1r4.setMinimumSize(new java.awt.Dimension(72, 72));
        pc1r4.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc1r4Layout = new javax.swing.GroupLayout(pc1r4);
        pc1r4.setLayout(pc1r4Layout);
        pc1r4Layout.setHorizontalGroup(
            pc1r4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc1r4Layout.setVerticalGroup(
            pc1r4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        board_Panel.add(pc1r4, gridBagConstraints);

        pc2r4.setBackground(new java.awt.Color(255, 153, 0));
        pc2r4.setEnabled(false);
        pc2r4.setMaximumSize(new java.awt.Dimension(72, 72));
        pc2r4.setMinimumSize(new java.awt.Dimension(72, 72));
        pc2r4.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc2r4Layout = new javax.swing.GroupLayout(pc2r4);
        pc2r4.setLayout(pc2r4Layout);
        pc2r4Layout.setHorizontalGroup(
            pc2r4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc2r4Layout.setVerticalGroup(
            pc2r4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        board_Panel.add(pc2r4, gridBagConstraints);

        pc3r4.setBackground(new java.awt.Color(255, 153, 0));
        pc3r4.setEnabled(false);
        pc3r4.setMaximumSize(new java.awt.Dimension(72, 72));
        pc3r4.setMinimumSize(new java.awt.Dimension(72, 72));
        pc3r4.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc3r4Layout = new javax.swing.GroupLayout(pc3r4);
        pc3r4.setLayout(pc3r4Layout);
        pc3r4Layout.setHorizontalGroup(
            pc3r4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc3r4Layout.setVerticalGroup(
            pc3r4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        board_Panel.add(pc3r4, gridBagConstraints);

        pc4r4.setBackground(new java.awt.Color(255, 153, 0));
        pc4r4.setEnabled(false);
        pc4r4.setMaximumSize(new java.awt.Dimension(72, 72));
        pc4r4.setMinimumSize(new java.awt.Dimension(72, 72));
        pc4r4.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pc4r4Layout = new javax.swing.GroupLayout(pc4r4);
        pc4r4.setLayout(pc4r4Layout);
        pc4r4Layout.setHorizontalGroup(
            pc4r4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pc4r4Layout.setVerticalGroup(
            pc4r4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        board_Panel.add(pc4r4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(board_Panel, gridBagConstraints);

        redcard_Panel1.setMaximumSize(new java.awt.Dimension(160, 100));
        redcard_Panel1.setMinimumSize(new java.awt.Dimension(160, 100));
        redcard_Panel1.setPreferredSize(new java.awt.Dimension(160, 100));

        javax.swing.GroupLayout redcard_Panel1Layout = new javax.swing.GroupLayout(redcard_Panel1);
        redcard_Panel1.setLayout(redcard_Panel1Layout);
        redcard_Panel1Layout.setHorizontalGroup(
            redcard_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        redcard_Panel1Layout.setVerticalGroup(
            redcard_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(redcard_Panel1, gridBagConstraints);

        redcard_Panel2.setMaximumSize(new java.awt.Dimension(160, 100));
        redcard_Panel2.setMinimumSize(new java.awt.Dimension(160, 100));
        redcard_Panel2.setPreferredSize(new java.awt.Dimension(160, 100));

        javax.swing.GroupLayout redcard_Panel2Layout = new javax.swing.GroupLayout(redcard_Panel2);
        redcard_Panel2.setLayout(redcard_Panel2Layout);
        redcard_Panel2Layout.setHorizontalGroup(
            redcard_Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        redcard_Panel2Layout.setVerticalGroup(
            redcard_Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(redcard_Panel2, gridBagConstraints);

        bluecard_Panel1.setMaximumSize(new java.awt.Dimension(160, 100));
        bluecard_Panel1.setMinimumSize(new java.awt.Dimension(160, 100));
        bluecard_Panel1.setPreferredSize(new java.awt.Dimension(160, 100));

        javax.swing.GroupLayout bluecard_Panel1Layout = new javax.swing.GroupLayout(bluecard_Panel1);
        bluecard_Panel1.setLayout(bluecard_Panel1Layout);
        bluecard_Panel1Layout.setHorizontalGroup(
            bluecard_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        bluecard_Panel1Layout.setVerticalGroup(
            bluecard_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(bluecard_Panel1, gridBagConstraints);

        bluecard_Panel2.setMaximumSize(new java.awt.Dimension(160, 100));
        bluecard_Panel2.setMinimumSize(new java.awt.Dimension(160, 100));
        bluecard_Panel2.setPreferredSize(new java.awt.Dimension(160, 100));

        javax.swing.GroupLayout bluecard_Panel2Layout = new javax.swing.GroupLayout(bluecard_Panel2);
        bluecard_Panel2.setLayout(bluecard_Panel2Layout);
        bluecard_Panel2Layout.setHorizontalGroup(
            bluecard_Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        bluecard_Panel2Layout.setVerticalGroup(
            bluecard_Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(bluecard_Panel2, gridBagConstraints);

        bankcard_Panel.setMaximumSize(new java.awt.Dimension(160, 100));
        bankcard_Panel.setMinimumSize(new java.awt.Dimension(160, 100));
        bankcard_Panel.setPreferredSize(new java.awt.Dimension(160, 100));

        bank_Label.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bank_Label.setMaximumSize(new java.awt.Dimension(160, 100));
        bank_Label.setMinimumSize(new java.awt.Dimension(160, 100));
        bank_Label.setPreferredSize(new java.awt.Dimension(160, 100));

        javax.swing.GroupLayout bankcard_PanelLayout = new javax.swing.GroupLayout(bankcard_Panel);
        bankcard_Panel.setLayout(bankcard_PanelLayout);
        bankcard_PanelLayout.setHorizontalGroup(
            bankcard_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bank_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bankcard_PanelLayout.setVerticalGroup(
            bankcard_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bankcard_PanelLayout.createSequentialGroup()
                .addComponent(bank_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(bankcard_Panel, gridBagConstraints);

        exit_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/close.png"))); // NOI18N
        exit_Button.setBorderPainted(false);
        exit_Button.setContentAreaFilled(false);
        exit_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit_Button.setPreferredSize(new java.awt.Dimension(20, 20));
        exit_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        getContentPane().add(exit_Button, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exit_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_ButtonActionPerformed
        // TODO add your handling code here:
        instance = null;
        dispose();
    }//GEN-LAST:event_exit_ButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bank_Label;
    private javax.swing.JPanel bankcard_Panel;
    private javax.swing.JPanel bluecard_Panel1;
    private javax.swing.JPanel bluecard_Panel2;
    private javax.swing.JPanel board_Panel;
    private javax.swing.JButton exit_Button;
    private javax.swing.JPanel pc0r0;
    private javax.swing.JPanel pc0r1;
    private javax.swing.JPanel pc0r2;
    private javax.swing.JPanel pc0r3;
    private javax.swing.JPanel pc0r4;
    private javax.swing.JPanel pc1r0;
    private javax.swing.JPanel pc1r1;
    private javax.swing.JPanel pc1r2;
    private javax.swing.JPanel pc1r3;
    private javax.swing.JPanel pc1r4;
    private javax.swing.JPanel pc2r0;
    private javax.swing.JPanel pc2r1;
    private javax.swing.JPanel pc2r2;
    private javax.swing.JPanel pc2r3;
    private javax.swing.JPanel pc2r4;
    private javax.swing.JPanel pc3r0;
    private javax.swing.JPanel pc3r1;
    private javax.swing.JPanel pc3r2;
    private javax.swing.JPanel pc3r3;
    private javax.swing.JPanel pc3r4;
    private javax.swing.JPanel pc4r0;
    private javax.swing.JPanel pc4r1;
    private javax.swing.JPanel pc4r2;
    private javax.swing.JPanel pc4r3;
    private javax.swing.JPanel pc4r4;
    private javax.swing.JPanel redcard_Panel1;
    private javax.swing.JPanel redcard_Panel2;
    // End of variables declaration//GEN-END:variables
}
