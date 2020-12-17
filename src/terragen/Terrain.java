package terragen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Terrain {
	
	BufferedImage image;
	int[][] heightmap;
	int width, height;
	
	public Terrain(int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.heightmap = new int[width][height];
		for (int i =0; i<width;i++) {
			for (int j = 0; j<height;j++) {
				//TODO: change from 0 to input!
				heightmap[i][j] = 1;
			}
		}
	}
	
	/**
	 * returns true, when the terrain was successfully created.
	 *
	 */
	public boolean Createheightmap() {
		//TODO: Algorithm
		return false;
	}
	public boolean CreateImage() {
		int grey=new Color(200,200,200).getRGB();
		for(int x = 0; x < width; x++) {
		    for(int y = 0; y < height; y++) {
		        image.setRGB(x, y, grey);
		    }
		}
		return true;
	}
	public boolean Showheightmap() {
		
		for (int i =0; i<heightmap.length;i++) {
			for (int j = 0; j<heightmap[0].length;j++) {
				//TODO: change from 0 to input!
				System.out.print(heightmap[i][j]+" ");
			}
			System.out.print("\n");
		}
		
		return false;
	}
	public boolean visualize() {
		
		try {
	    // retrieve image
	    File outputfile = new File("saved.png");
	    ImageIO.write(image, "png", outputfile);
	} catch (IOException e) {
	    e.printStackTrace();
	    return false;
	}
		return true;
		
	}
}
