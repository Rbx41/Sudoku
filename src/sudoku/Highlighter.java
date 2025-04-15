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

public class Highlighter {
	class Point {
		public int x,y;
		Point(int x_, int y_) {
			x = x_;
			y = y_;
		}
		
	}
	
	
	BufferedImage square;
	BufferedImage horizontal_rect;
	BufferedImage vertical_rect;
	
	
	Point gridPoint;
	ImageToDraw SquareImgToDraw;
	
	
	
	public Highlighter(int x,int y) {
		gridPoint = new Point(x,y);
		SquareImgToDraw = getSquareImgToDraw();
		
		//LoadImages()
		
	}
	
	
	public Point getSquareImgToDraw() {
		
		
		
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
						return new ImageToDraw(x,y);
					}
				}
				x +=210;
			}
			y += 210;
		}
		
		return new Point(96,15);	
	}
	
	
	public void loadImages() {
		
		try {
			String source = "/home/rybex/eclipse-workspace/Sudoku/src/sudoku/assets/plansza/";
			this.square = ImageIO.read(new File(source+"podswietlony_kwadrat.png"));
			this.horizontal_rect = ImageIO.read(new File(source+"horizontal_rect.png"));
			this.vertical_rect = ImageIO.read(new File(source+"vertical_rect.png"));
			

        } catch ( IOException e ) {
            System.err.println("Error");
            e.printStackTrace();
        }
		
	}
	
	
	
	
}





