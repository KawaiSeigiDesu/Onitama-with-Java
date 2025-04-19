/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author LENOVO
 */
public class CustomButton extends JButton{
    
    private Movement movement;
    private JPanel jpanel;

    public CustomButton(Movement movement) {
        this.movement = movement;
    }

    public CustomButton(Movement movement,JPanel jpanel) {
        this.movement = movement;
        this.setIcon(new ImageIcon(movement.getbinaryImage()));
        this.jpanel = jpanel;
    }

    public Movement getMovement() {
        return movement;
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }
}
