package terragen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

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
		heightmap[0][0] = 8;
		heightmap[0][heightmap[0].length-1] = 6;
		heightmap[heightmap.length-1][0] = 6;
		heightmap[heightmap.length-1][heightmap[0].length-1] = 7;
	}
	public boolean Createheightmap(int sh, int sw) {
		return true;
	}
	/**
	 * returns true, when the terrain was successfully created.
	 * Note: heightmap[y][x]; !!!
	 * sw = square width
	 * sh = square height
	 * Accepts indexes!
	 */
	public boolean square(int starty, int startx, int endy, int endx) {
		//TODO: Algorithm
		//calculate Middle point:
		float upleftcorner = heightmap[starty][startx];
		float uprightcorner = heightmap[starty][endx];
		float lowleftcorner = heightmap[endy][startx];
		float lowrightcorner = heightmap[endy][endx];
		
		heightmap[starty+(endy/2)][startx+(endx/2)] = Math.round((upleftcorner+uprightcorner+lowleftcorner+lowrightcorner)/4);
		
		//fun starts here.
		//calculate index of the next points
		diamond((int)(upleftcorner-(uprightcorner-upleftcorner)/2),0,3,3); //TODO: WRONG COORDS!
		return true;
	}
	private boolean diamond(int starty, int startx, int endy, int endx) {
		if (heightmap[starty][startx] != 0) {
			if (heightmap[endy][endx] != 0) {
				float upcorner = heightmap[starty][(endx/2)-startx];
				float leftcorner = heightmap[(endy/2)-starty][startx];
				//float rightcorner = heightmap[][];
				System.err.println("Zahl in der heightmap!");
			}
			else {
				
			}
		}
		else {
			
		}
		
		return true;
	}
	public boolean calcdiamond(int starty, int startx, int endy, int endx) {
		return true;
	}
	
	public boolean CreateImage() {
		int grey=new Color(200,200,200).getRGB();
		for(int x = 0; x < width; x++) {
		    for(int y = 0; y < height; y++) {
		    	int rdmred = ThreadLocalRandom.current().nextInt(0, 255 + 1);
		    	int rdmgre = ThreadLocalRandom.current().nextInt(0,255);
		    	int rdmblu = ThreadLocalRandom.current().nextInt(0,255);
		    	int color = new Color(rdmred,rdmgre,rdmblu).getRGB();
		    	image.setRGB(x, y, color);
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
