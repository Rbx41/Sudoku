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
public class WczytajGrePanel extends JPanel /*implements MouseListener */{

    private BufferedImage tloMenu;

    int[] y0 = {200, 295, 395, 495}; // top left point of images

    List<BufferedImage>MenuButtonsImg= new ArrayList<>();
    private String[] ButtonNames = { "kontynuuj.png", "nowaGra.png", "wczytajGre.png", "zasady.png"};

    List<BufferedImage>MenuButtons2Img= new ArrayList<>();
    private String[] ButtonNames2 = { "kontynuuj2.png", "nowaGra2.png", "wczytajGre2.png", "zasady2.png"};

    //private BufferedImage  kontynuuj2, nowaGra2, wczytajGre2, zasady2;
    


    public WczytajGrePanel()
    {
        setPreferredSize(new Dimension(800, 700));
        setBackground(Color.WHITE);

        addMouseListener(new MyMouse(this));
        addMouseMotionListener(new MyMouse(this));

        loadImages();
        

    }


    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponents(g);

        g.drawImage(this.tloMenu, 0, -80, this);


        for (int i=0; i<MenuButtonsImg.size(); i++) {
            g.drawImage(this.MenuButtonsImg.get(i),265, this.y0[i]  , this);
        }

        /*
        g.drawImage(this.nowaGra, 265, 200, this);
        g.drawImage(this.kontynuuj, 265, 295, this);
        g.drawImage(this.wczytajGre, 265, 395, this);
        g.drawImage(this.zasady, 265, 495, this);
        */

        /*
        Mouse x: 877
        Mouse y: 455
         */



    }


    public void loadImages() {

        try {
            String source = "/home/rybex/IdeaProjects/Sudoku/src/sudoku/assets/";
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
    	WczytajGrePanel outer;


        public MyMouse(WczytajGrePanel outer) {
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
            for(int i=0;i<MenuButtonsImg.size();i++) {
                if ( (270 < x && x < 540)  && (y0[i]  < y && y < y0[i]+ 95) ) {
                    opcja =  ButtonNames[i];
                    break;
                }
            }

            switch (opcja) {
                case "kontynuuj.png":
                    System.out.println("Kontynuuj");
                    this.outer.setVisible(false);
                    JFrame MenuFrame = (JFrame) SwingUtilities.getWindowAncestor(this.outer);
                    
                    MenuFrame.remove(this.outer);
                    MenuFrame.getContentPane().add(new MenuPanel());
                    
                    break;
                case "nowaGra.png":
                    System.out.println("NowaGra");
                    //this.outer.option = Options.nowaGra;
                    break;
                case "wczytajGre.png":
                    System.out.println("WczytajGre");
                    //this.outer.option = Options.wczytajGre;
                    break;
                case "zasady.png":
                	//this.outer.option = Options.zasady;
                    System.out.println("Zasady");
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
                if ( (270 < x && x < 540)  && (y0[i]  < y && y < y0[i]+ 95) ) {
                    g.drawImage(this.outer.MenuButtons2Img.get(i), 265, y0[i] , this.outer);
                    //System.out.println(this.outer.ButtonNames2[i]);
                } else {
                    g.drawImage(this.outer.MenuButtonsImg.get(i), 265, y0[i], this.outer);
                    //System.out.println(this.outer.ButtonNames2[i]);
                }
            }

            //System.out.println("Mouse entered");
        }
        
        
        
    }



}
