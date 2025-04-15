package sudoku;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class ImageToDraw { // stores image with TopLeft in which it will be drawn
	
	class Point {
		public int x,y;
		Point(int x_, int y_) {
			x = x_;
			y = y_;
		}
		
	}
	
	public BufferedImage img;
	public int x,y;
	
	public ImageToDraw(BufferedImage img_, Point TopLeft, int width, int height) {
		img = img_;
	}
	
	public void Draw(Graphics g, JPanel panel) {
		g.drawImage(img, x, y,  panel);
	}
	
	
}
