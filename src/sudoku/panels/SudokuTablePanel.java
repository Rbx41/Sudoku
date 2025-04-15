package sudoku.panels;

import sudoku.Highlighter;
import sudoku.SudokuGenerator;
import sudoku.panels.*;

import java.util.*;
import java.awt.Point;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;





/*




 */
public class SudokuTablePanel extends JPanel /*implements MouseListener */{
	
	

	private int width;
	private int height;
	
    private BufferedImage plansza;
    
    
    List<BufferedImage>cyfryImg= new ArrayList<>();
    private String[] cyfryNames = { "1.png", "2.png", "3.png", "4.png", "5.png","6.png","7.png", "8.png", "9.png"};
    //private BufferedImage cyfry[] = {}
    
    int numbersTable[][];
    
        
    int difficultyLevel;
    
    
    // lewy gorny rog 97 15
    
    
    

    public SudokuTablePanel(int dif_level)
    {
    	difficultyLevel = dif_level;
    	numbersTable = new int[9][9];
    	
    	width=725;
    	height=680;
    	
    	
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);

        addMouseListener(new MyMouse(this));
        addMouseMotionListener(new MyMouse(this));

        
        loadImages();
        SudokuGenerator generator = new SudokuGenerator(difficultyLevel);
        numbersTable = generator.getTable();
        
        
        
        
    }


    @Override
    protected void paintComponent(Graphics g)
    {
    	super.paintComponents(g);

        g.drawRect(0, 0, width, height);
        g.setColor(Color.WHITE);  
        g.fillRect(0,0,width,height);  
        g.drawImage(this.plansza, 80, 0, this);
                
        drawDigits(g);
    	 
    	
       


    }


    public void loadImages() {

        try {
        	String source = "/home/rybex/eclipse-workspace/Sudoku/src/sudoku/assets/plansza/";
            this.plansza = ImageIO.read(new File(source+"plansza.png"));

            
            //this.cyfryImg.add(ImageIO.read(new File(source+Integer.toString(1)+".png")));
           
            
            for (int i = 0; i < this.cyfryNames.length; i++) {
            	System.out.println(Integer.toString(i)+".png");
            	System.out.println(i);
                this.cyfryImg.add(ImageIO.read(new File(source+Integer.toString(i+1)+".png")));  
            }
            
			


        } catch ( IOException e ) {
            System.err.println("Error");
            e.printStackTrace();
        }

    }

    public Point getMousePosition(MouseEvent e)
    {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point point = new Point(a.getLocation());
        SwingUtilities.convertPointFromScreen(point, e.getComponent());
        return point;
    }



    private class MyMouse extends MouseAdapter {
    	SudokuTablePanel outer;


        public MyMouse(SudokuTablePanel outer) {
            this.outer = outer;
        }

        @SuppressWarnings("deprecation")
		@Override
        public void mouseClicked(MouseEvent e) {
            // create a random color
          System.out.println("Mouse clicked");

          Point point = getMousePosition();
          int x=(int) point.getX();
          int y=(int) point.getY();
          System.out.println("x "+ x);
          System.out.println("y "+ y);
          
          int xGrid;
          int yGrid = 15;
          for (int i = 0; i < 9; i++) {
        	  xGrid = 96;
        	  for (int j=0; j < 9; j++) {
        		  if (x >= xGrid &&  x <= xGrid+70) {
        			  if (y >= yGrid &&  y <= yGrid+70) {
        				  Highlighter highlighter = new Highlighter(x,y);
        				  //System.out.println("Jest w kratce x "+ x+ " y "+ y);
        			  }
        		  }
        		  xGrid += 70;
        	  }
        	  yGrid += 70;
          }
          
         

        }

        
        
        
    }
    
    public void drawDigits(Graphics g) {
    	
    	 int x = 0;
     	int y = 0;
         
         
     	 for (int[] row : numbersTable) {
     		 x = 0;
              for (int cell : row) {
             	 
             	 if (cell != 0) {
             		 BufferedImage digit = cyfryImg.get(cell-1);
             		 
             		 g.drawImage(digit, 106+x, 19+y, this); 
             	 }
             	 System.out.println(cell);
             	 x+=70;
              }
              y += 70;
          }
    	
    }
    



}
