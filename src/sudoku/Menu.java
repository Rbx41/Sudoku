package sudoku;


//import javax.swing.*;

import sudoku.panels.MenuPanel;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;


//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JPanel;


/*

Menu example = new Example3();

JFrame frame = new JFrame("GUI");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.add(example);
frame.pack();
frame.setLocationRelativeTo(null);
frame.setVisible(true);

 */
class Menu extends JFrame /*implements MouseListener */{



    public Menu()
    {

        this.getContentPane().add(new MenuPanel());
        //add(new MousePanel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setVisible(true);




    }






}
