package sudoku;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import java.awt.Point;

public class ImageToDraw { // stores image with TopLeft in which it will be drawn
	
	
	public BufferedImage img;
	public Point TopLeft;
	
	
	public ImageToDraw(BufferedImage img_, Point TopLeft_) {
		img = img_;
		TopLeft = TopLeft_;
	}
	
	
	
	public void Draw(Graphics g, JPanel panel) {
		g.drawImage(img, TopLeft.x, TopLeft.y,  panel);
	}
	
	
}
