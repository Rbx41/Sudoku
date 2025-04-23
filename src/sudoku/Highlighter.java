package sudoku;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * this class inputs sudoku grid cordinates and adds one square , two rects to drawing queue  
 * 
 */

import sudoku.ImageToDraw;
import java.awt.Point;

public class Highlighter {
	
	BufferedImage squareImg;
	BufferedImage horizontal_rect;
	BufferedImage vertical_rect;
	
	
	Point gridPoint;
	ImageToDraw SquareImgToDraw;
	ImageToDraw HorizontalImgToDraw;
	ImageToDraw VerticalImgToDraw;
	
	
	//Point horizontal_rect_tab[][] = { {new Point(96,), new Point(304,), new Point(509,) },{},{}, {},{},{}, {},{},{}   };
	
	
	public Highlighter(int x,int y) {
		gridPoint = new Point(x,y);
		loadImages();
		
		Point p = getGridLoc();
		
		HorizontalImgToDraw = new ImageToDraw(horizontal_rect,new Point(95,p.y-6));
		VerticalImgToDraw = new ImageToDraw(vertical_rect,new Point(p.x-8,15));
		
		SquareImgToDraw =  new ImageToDraw(squareImg, getSquareLoc());
		
		//ImageToDraw(BufferedImage img_, Point TopLeft, int width, int height);
		
		
	}
	
	
	private Point getSquareLoc() {
		
		
		int gridX = gridPoint.x;
		int gridY = gridPoint.y;
		
		
		int y = 15;
		int x;
		for (int i = 0; i < 3; i++) {
			x = 96;
			for (int j = 0; j < 3; j++) {
				if (gridX >= x && gridX <= x+210 ) {
					if (gridY >= y && gridY <= y+210) {
						System.out.println("Znaleziono kwadrat: "+Integer.toString(x)+"  "+Integer.toString(y));
						return new Point(x-7,y-3);
					}
				}
				x +=210;
			}
			y += 210;
		}
		
		return new Point(96,15);	
	}
	
	private Point getGridLoc() {
		int gridX = gridPoint.x;
		int gridY = gridPoint.y;
		
		int y = 15;
		int x;
        for (int i = 0; i < 9; i++) {
      	  x = 96;
      	  for (int j=0; j < 9; j++) {
      		  if (gridX >= x &&  gridX <= x+70 ) {
      			  if (gridY >= y &&  gridY <= y+70) {
      				  System.out.println("Jest w kratce x "+ x+ " y "+ y);
      				  return new Point(x,y);
      				  
      			  }
      		  }
      		  x += 70;
      	  }
      	  y += 70;
        }
        return new Point(96,15);	
       

      }

	
	
	public void loadImages() {
		
		try {
			String source = "/home/rybex/eclipse-workspace/Sudoku/src/sudoku/assets/plansza/";
			this.squareImg = ImageIO.read(new File(source+"podswietlony_kwadrat.png"));
			this.horizontal_rect = ImageIO.read(new File(source+"horizontal_rect.png"));
			this.vertical_rect = ImageIO.read(new File(source+"vertical_rect.png"));
			

        } catch ( IOException e ) {
            System.err.println("Error");
            e.printStackTrace();
        }
		
	}
	
	
	public ImageToDraw getSquareImgToDraw() {
		return SquareImgToDraw;
	}
	
	
	public ImageToDraw getHorizontalImgToDraw() {
		return HorizontalImgToDraw;
	}
	
	public ImageToDraw getVerticalImgToDraw() {
		return VerticalImgToDraw;
	}
	
	
	
}





