package sudoku.panels;


import sudoku.CheckSudokuBoardConfiguration;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

import sudoku.ImageToDraw;



/*




 */
public class SudokuTablePanel extends JPanel /*implements KeyListener /* /*implements MouseListener */{
	
	private boolean board_enabled;
	private int I,J; // indexes for last added digit to board 
	
	
	private int width;
	private int height;
	
    private BufferedImage plansza;
    private BufferedImage ramka;
    private BufferedImage kratka;
    private BufferedImage tlo_planszy;
    private BufferedImage czerwone_tlo_planszy;
    private BufferedImage tlo;
    private BufferedImage strzalka;
    private BufferedImage podswietlona_strzalka;
    private BufferedImage nie_prawidlowy_ruch_napis;
    
    
    private List<BufferedImage>cyfryImg;
    private String[] cyfryNames = { "1.png", "2.png", "3.png", "4.png", "5.png","6.png","7.png", "8.png", "9.png"};
    //private BufferedImage cyfry[] = {}
    
    private int numbersTable[][];
    
    private int difficultyLevel;
    
    private List<ImageToDraw>drawers;
    
    // lewy gorny rog 97 15
    
    private JTextField typingArea; 
    DigitInput digitInput;
    
    

    public SudokuTablePanel(int dif_level)
    {
    	board_enabled = true;
    	
    	difficultyLevel = dif_level;
    	numbersTable = new int[9][9];
    	
    	width=725;
    	height=680;
    	
    	requestFocusInWindow();
    	setFocusable(true);
    	
    	
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);

        addMouseListener(new MyMouse(this));
        addMouseMotionListener(new MyMouse(this));
        digitInput = new DigitInput(this);
        addKeyListener(digitInput);
        
        

        cyfryImg = new ArrayList<>();
        
        loadImages();
        SudokuGenerator generator = new SudokuGenerator(difficultyLevel);
        numbersTable = generator.getTable();
        
        drawers = new ArrayList<>();
        
        drawers.add(new ImageToDraw( tlo,new Point(0,0) ));
        drawers.add(new ImageToDraw( strzalka,new Point(12,20) ));
        drawers.add(new ImageToDraw( tlo_planszy,new Point(80,0) ));
        AddDigitsToDrawer();
        drawers.add(new ImageToDraw( kratka,new Point(83,10) ));
        drawers.add(new ImageToDraw( ramka,new Point(80,0) ));
        
        
        
        //drawers.add(new ImageToDraw( kratka,new Point(80,0) ));
        
        
        
    }


    @Override
    protected void paintComponent(Graphics g)
    {
    	super.paintComponents(g);

        g.drawRect(0, 0, width, height);
        g.setColor(Color.WHITE);  
        g.fillRect(0,0,width,height);
        
        //g.drawImage(this.plansza, 80, 0, this);
                
        //drawDigits(g);
    	
        for (int i = 0; i < drawers.size(); i++) {
        	drawers.get(i).Draw(g, this);
        }
    	
       

    }


    public void loadImages() {

        try {
        	String source = "/home/rybex/eclipse-workspace/Sudoku/src/sudoku/assets/plansza/";
            this.plansza = ImageIO.read(new File(source+"plansza.png"));
            this.ramka = ImageIO.read(new File(source+"ramka.png"));
            this.kratka = ImageIO.read(new File(source+"kratka.png"));
            this.tlo_planszy = ImageIO.read(new File(source+"tlo_planszy.png"));
            this.czerwone_tlo_planszy = ImageIO.read(new File(source+"czerwone_tlo_planszy.png"));
            
            this.tlo = ImageIO.read(new File(source+"tlo.png"));
            this.strzalka = ImageIO.read(new File(source+"strzalka.png"));
            this.podswietlona_strzalka = ImageIO.read(new File(source+"podswietlona_strzalka.png"));
            this.nie_prawidlowy_ruch_napis=  ImageIO.read(new File(source+"nie_prawidlowy_ruch_napis.png"));
            
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
        				  if (board_enabled) {
        					  podswietlKratki(x,y); 
            				  digitInput.Activate();
            				  digitInput.inputField(i, j);
            				  //System.out.println("i "+i+"  j "+j);
        				  }
        				
        			  }
        		  }
        		  xGrid += 70;
        	  }
        	  yGrid += 70;
          }
          
          
          if (x >= 12 && x < 74 && y > 18  && y < 85) {
        	  board_enabled = true;
        	  numbersTable[I][J] = 0;
			  odswiezPlansze();
			  repaint();
          }
          
          requestFocusInWindow();

          
        }
        
       
        
        
        @Override
        public void mouseMoved(MouseEvent e) {
            // 270 < x < 540
            Point point = getMousePosition();
            int x=(int) point.getX();
            int y=(int) point.getY();

            Graphics g = getGraphics();

           //74 22
           if (x >= 12 && x < 74 && y > 18  && y < 85 ) {
        	   g.drawImage(this.outer.podswietlona_strzalka, 11, 20 , this.outer);
           }
           else {
        	   g.drawImage(this.outer.strzalka, 11, 20 , this.outer);
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
    
    
    public void AddDigitsToDrawer() {
    	
    	
    	int x = 0;
     	int y = 0;
         
         
     	 for (int[] row : numbersTable) {
     		 x = 0;
              for (int cell : row) {
             	 
             	 if (cell != 0) {
             		 BufferedImage digit = cyfryImg.get(cell-1);
             		 Point p = new Point(106+x, 19+y);
             		 
             		 ImageToDraw imtd = new ImageToDraw(digit,p);
             		 drawers.add(imtd);
             		 
             		 
             	 }
             	 //System.out.println(cell);
             	 x+=70;
              }
              y += 70;
          }
    	
    	
    	
    }
    
    
    
    public void podswietlKratki(int x, int y) {
    	  Highlighter highlighter = new Highlighter(x,y);
		  
		  drawers.clear();
		  
		  drawers.add(new ImageToDraw( tlo,new Point(0,0) ));
		  drawers.add(new ImageToDraw( tlo_planszy,new Point(80,0) ));
		  drawers.add(new ImageToDraw( strzalka,new Point(12,20) ));
		  
		  drawers.add(highlighter.getSquareImgToDraw());
		  drawers.add(highlighter.getHorizontalImgToDraw());
		  drawers.add(highlighter.getVerticalImgToDraw());
		  
		  AddDigitsToDrawer();
	      drawers.add(new ImageToDraw( kratka,new Point(83,10) ));
	      drawers.add(new ImageToDraw( ramka,new Point(80,0) ));
		  
	      repaint();
		  
		  //System.out.println("Jest w kratce x "+ xGrid+ " y "+ yGrid);	
    }
    
    
    public void wstawCyfre() {
    	
    }
    
    
    
    public class DigitInput extends KeyAdapter {
    	private boolean active;
    	private SudokuTablePanel outer;
    	private int row;
    	private int col;
    	
        public DigitInput(SudokuTablePanel outer) {
        	row = -1;
        	col = -1;
        	active = false;
            this.outer = outer;
        }
        
        public void Activate() {
        	active = true;
        }
        public void inputField(int row_, int col_) {
        	row = row_;
        	col = col_;
        }
        
        public void Deactivate() {
        	active = false;
        }
        
        
    	
        @Override
     	public void keyPressed(KeyEvent e) {
        	if (active == true) {
        		if (Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '0')  {
        			
        			if (row != -1 && col != -1) {
        				System.out.println("SPRAWDZANIE");
        				int tempTable[][] = numbersTable.clone();
        				int num = Integer.parseInt(String.valueOf(e.getKeyChar()));
        				tempTable[row][col] = num;
        				boolean isValid = CheckSudokuBoardConfiguration.isValid(tempTable);
        				System.out.println(isValid);
        				I = row;
    					J = col;
        				
        				if (isValid) {
        					numbersTable[row][col] = num;
        					odswiezPlansze();
            				repaint();
        				}
        				else {
        					board_enabled = false;
        					
        					drawers.clear();
        			    	drawers.add(new ImageToDraw( tlo,new Point(0,0) ));
        			        drawers.add(new ImageToDraw( strzalka,new Point(12,20) ));
        			        drawers.add(new ImageToDraw( czerwone_tlo_planszy,new Point(84,4) ));
        			        AddDigitsToDrawer();
        			        drawers.add(new ImageToDraw( kratka,new Point(83,10) ));
        			        drawers.add(new ImageToDraw( ramka,new Point(80,0) ));
        			        
        			        drawers.add(new ImageToDraw( nie_prawidlowy_ruch_napis,new Point(160,100) ));
        			        repaint();
        				}
        					
        			}
        			
        			//System.out.println("keyPressed: "+e.getKeyChar());
        			
        		}
        	}
     	}
        
        
       
        
        
    	
    }
    
    
    
    public void odswiezPlansze() {
    	drawers.clear();
    	drawers.add(new ImageToDraw( tlo,new Point(0,0) ));
        drawers.add(new ImageToDraw( strzalka,new Point(12,20) ));
        drawers.add(new ImageToDraw( tlo_planszy,new Point(80,0) ));
        AddDigitsToDrawer();
        drawers.add(new ImageToDraw( kratka,new Point(83,10) ));
        drawers.add(new ImageToDraw( ramka,new Point(80,0) ));
    	
    }
    
    
    
    
    
    
    
    
    
    
    



}
