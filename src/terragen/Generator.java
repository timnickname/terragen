package terragen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

public class Generator {

		BufferedImage image;
		public double[][] heightmap; //TODO: Change to private! only set to public for debugging!
		private int indexsize;
		
		public Generator(int size) {
			this.indexsize = size-1;
			this.heightmap = new double[size][size];
			System.out.println("heightmap created:");
			for (int i =0; i<heightmap.length;i++) {
				for (int j = 0; j<heightmap[0].length;j++) {
					//TODO: change from 0 to input!
					System.out.print(heightmap[i][j]+" ");
				}
				System.out.print("\n");
			}
			this.image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		}
		
		public void diamond(int start,int end) {
			System.out.println("Start: "+start+" End: "+end);
			//double top = heightmap[start][end/2+start] = Math.round((heightmap[start][start]+heightmap[start][end]+middle)/3);
			//double left = heightmap[(end+start)/2][start] = Math.round((heightmap[start][start]+middle+heightmap[start][end])/3);

			int a = 0;
			int b = 0;
			int c = indexsize;
			int d = indexsize;


			calcmiddle(a,b,c,d);
			calcleft(a,b,c,d);
			calcright(a,b,c,d);
			
				
				
				
				
				/**
				calcall(a,b,c/2,d/2);
				
				calcall(a,b+(d/2),c/2,d);
				calcall(a+(c/2),b,c,d/2);
				calcall(a+(c/2),b+(d/2),c,d);
				calcall(0,0,4,4);
				calcall(0,0,2,2);*/
			
		}
		
		public boolean isInHeightmap(int y, int x) {
			try {
				double dumm = heightmap[y][x];
			}
			catch (Exception ArrayIndexOutOfBoundsException) {
				return false;
			}
			return true;
		}
		
		
		public double calcpoint(int y, int x, int r) {
			double point=0;
			if (isInHeightmap(y+r,x)) {
				
			}
			return point;
		}
		
		public void calcall(int a,int b, int c, int d) {
			calcmiddle(a,b,c,d);
			calcleft(a,b,c,d);
			calcright(a,b,c,d);
			calctop(a,b,c,d);
			calcbot(a,b,c,d);
		}
		
		public void toimage() {
			for (int i = 0; i<heightmap.length;i++) {
				for (int j = 0; j<heightmap.length;j++) {
					
				}
			}
		}

		
		public double calcleft(int starty, int startx, int endy, int endx) {
			double left;
			try {
				left = heightmap[endy-((endy-starty)/2)][startx] = Math.round((heightmap[starty][startx]+calcmiddle(starty,startx, endy, endx)+heightmap[endy][startx]+heightmap[starty+(endy/2)][startx-(endx/2)])/4);
			}
			catch (Exception ArrayIndexOutOfBoundsException) {
				System.err.println("WARN: Exception in calcleft catched!");	
				left = heightmap[endy-((endy-starty)/2)][startx] = Math.round((heightmap[starty][startx]+calcmiddle(starty, startx, endy, endx)+heightmap[endy][startx])/3);
			}
			return left;
		}
		

		public double calcright(int starty, int startx, int endy, int endx) {
			double right;
			try {
				right = heightmap[endy-((endy-starty)/2)][endx] = Math.round((calcmiddle(starty, startx, endy, endx)+heightmap[starty][endx]+heightmap[endy][endx]+heightmap[starty+(endx/2)][startx+endx+(endx/2)])/4); 
			}
			catch (Exception ArrayIndexOutOfBoundsException) {
				System.err.println("WARN: Exception in calcright catched!");
				right = heightmap[endy-((endy-starty)/2)][endx] = Math.round((calcmiddle(starty, startx, endy , endx)+heightmap[starty][endx]+heightmap[endy][endx])/3);
			}
			return right;
		}
		public double calcbot(int starty, int startx, int endy, int endx) {
			double bottom;
			try {
				bottom = heightmap[endy][endx-((endx-startx)/2)] = Math.round((calcmiddle(starty, startx, endy, endx)+heightmap[endy][startx]+heightmap[endy][endx]+heightmap[starty+endy+(endy/2)][startx+endx/2])/4);
			}
			catch (Exception ArrayIndexOutOfBoundsException) {
				System.err.println("WARN: Exception in calcbot catched!");
				bottom = heightmap[endy][endx-((endx-startx)/2)] = Math.round((calcmiddle(starty,startx,endy,endx)+heightmap[endy][startx]+heightmap[endy][endx])/3);
			}
			return bottom;
		}
		
		public double calctop(int starty, int startx, int endy, int endx) {
			double top;
			try {
				top = heightmap[starty][endx-((endx-startx)/2)] = Math.round((heightmap[starty][starty]+heightmap[starty][endx]+calcmiddle(starty, startx, endy, endx)+heightmap[starty-(endy/2)][startx+(endx/2)])/4);
			}
			catch (Exception ArrayIndexOutOfBoundsException) {
				System.err.println("WARN: Exception in calctop catched!");
				top = heightmap[starty][endx-((endx-startx)/2)] = Math.round((heightmap[starty][startx]+heightmap[starty][endx]+calcmiddle(starty, startx, endy, endx))/3);
			}
			return top;
		}
		
		public double calcmiddle(int starty, int startx, int endy, int endx) {
			double middle = heightmap[starty+((endy-starty)/2)][startx+((endx-startx)/2)] = Math.round((heightmap[starty][startx]+heightmap[endy][startx]+heightmap[starty][endx]+heightmap[endy][endx])/4);
			return middle;
		}
		
	public boolean visualize() {
		for(int x = 0; x < heightmap.length; x++) {
		    for(int y = 0; y < heightmap.length; y++) {
		    	int rdmred = ThreadLocalRandom.current().nextInt(0, 255 + 1);
		    	int rdmgre = ThreadLocalRandom.current().nextInt(0,255);
		    	int rdmblu = ThreadLocalRandom.current().nextInt(0,255);
		    	int grey = (int) (heightmap[y][x]*20);
		    	int color = new Color(20,grey,grey).getRGB();
		    	image.setRGB(x, y, color);
		    	if (heightmap[y][x] == 0) {
		    		int white = new Color(250,250,250).getRGB();
		    		image.setRGB(x, y, white);
		    	}
		    }
		    
		}
		System.out.println("image created");
		try {
		    // retrieve image
		    File outputfile = new File("saved.png");
		    ImageIO.write(image, "png", outputfile);
		    System.out.println("image saved.");
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		}
			return true;
			
		}
		
	public String toString() {
		System.out.println("heightmap:");
		for (int i =0; i<heightmap.length;i++) {
			for (int j = 0; j<heightmap[0].length;j++) {
				//TODO: change from 0 to input!
				System.out.print(heightmap[i][j]+"|");
			}
			System.out.print("\n");
		}
	return "output!";
	}
}
