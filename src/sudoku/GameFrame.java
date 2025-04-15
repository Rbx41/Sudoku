package sudoku;


//import javax.swing.*;


import sudoku.panels.MenuPanel;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;



class GameFrame extends JFrame /*implements MouseListener */{

	JPanel Panel;
	

    public GameFrame()
    {
    	Panel = new MenuPanel();
    	
        this.getContentPane().add(Panel);
        
        //add(new MousePanel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(725, 680);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    
    
   






}
