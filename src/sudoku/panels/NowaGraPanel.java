package sudoku.panels;


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

//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JPanel;





/*




 */
public class NowaGraPanel extends JPanel /*implements MouseListener */{

    private BufferedImage tloMenu;

    int[] y0 = {200, 295, 395}; // top left point of images

    List<BufferedImage>MenuButtonsImg= new ArrayList<>();
    private String[] ButtonNames = { "latwy.png", "sredni.png", "trudny.png"};

    List<BufferedImage>MenuButtons2Img= new ArrayList<>();
    private String[] ButtonNames2 = { "latwy2.png", "sredni2.png", "trudny2.png"};

    //private BufferedImage  kontynuuj2, nowaGra2, wczytajGre2, zasady2;
    
    
    public final int LATWY = 1;
    public final int SREDNI = 2;
    public final int TRUDNY = 3;
    
    

    public NowaGraPanel()
    {
    	System.out.println("NowaGraPanel");
        setPreferredSize(new Dimension(725, 680));
        setBackground(Color.WHITE);

        addMouseListener(new MyMouse(this));
        addMouseMotionListener(new MyMouse(this));

        loadImages();
        

    }


    @Override
    protected void paintComponent(Graphics g)
    {
    	System.out.println("NowaGraPanel");
    	
        super.paintComponents(g);

        g.drawImage(this.tloMenu, -40, -80, this);


        for (int i=0; i<MenuButtonsImg.size(); i++) {
            g.drawImage(this.MenuButtonsImg.get(i),225, this.y0[i]  , this);
        }



    }


    public void loadImages() {

        try {
            String source = "/home/rybex/eclipse-workspace/Sudoku/src/sudoku/assets/nowaGra/";
            this.tloMenu = ImageIO.read(new File(source+"tloMenu.png"));

            for (int i = 0; i < this.ButtonNames.length; i++) {
                this.MenuButtonsImg.add(ImageIO.read(new File(source+ButtonNames[i])));
            }

            for (int i = 0; i < this.ButtonNames2.length; i++) {
                this.MenuButtons2Img.add(ImageIO.read(new File(source+ButtonNames2[i])));
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
    	NowaGraPanel outer;


        public MyMouse(NowaGraPanel outer) {
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
          String opcja = "";
          JFrame GameFrame;
          
            for(int i=0;i<MenuButtonsImg.size();i++) {
                if ( (230 < x && x < 500)  && (y0[i]  < y && y < y0[i]+ 95) ) {
                    opcja =  ButtonNames[i];
                    break;
                }
            }

            switch (opcja) {
                case "latwy.png":
                    System.out.println("latwy");
                    this.outer.setVisible(false);
                    GameFrame = (JFrame) SwingUtilities.getWindowAncestor(this.outer);
                    GameFrame.remove(this.outer);
                    GameFrame.getContentPane().add(new SudokuTablePanel(this.outer.LATWY));
                    break;
                case "sredni.png":
                	 System.out.println("sredni");
                     this.outer.setVisible(false);
                     GameFrame = (JFrame) SwingUtilities.getWindowAncestor(this.outer);
                     GameFrame.remove(this.outer);
                     GameFrame.getContentPane().add(new SudokuTablePanel(this.outer.SREDNI));
                    break;
                case "trudny.png":
                	 System.out.println("trudny");
                	 this.outer.setVisible(false);
                     GameFrame = (JFrame) SwingUtilities.getWindowAncestor(this.outer);
                     GameFrame.remove(this.outer);
                     GameFrame.getContentPane().add(new SudokuTablePanel(this.outer.TRUDNY));
                    break;
            }


        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // 270 < x < 540
            Point point = getMousePosition();
            int x=(int) point.getX();
            int y=(int) point.getY();

            Graphics g = getGraphics();

            for(int i=0;i<MenuButtonsImg.size();i++) {
                if ( (230 < x && x < 500)  && (y0[i]  < y && y < y0[i]+ 95) ) {
                    g.drawImage(this.outer.MenuButtons2Img.get(i), 225, y0[i] , this.outer);
                    //System.out.println(this.outer.ButtonNames2[i]);
                } else {
                    g.drawImage(this.outer.MenuButtonsImg.get(i), 225, y0[i], this.outer);
                    //System.out.println(this.outer.ButtonNames2[i]);
                }
            }

            //System.out.println("Mouse entered");
        }
        
        
        
    }



}
