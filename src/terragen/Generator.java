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
			/** 
			 * 	calcright(start, end);
				calctop(start, end);
				calcbot(start, end);
				calcleft(start, end);
			*/
			
			//calcmiddle(0,0,indexsize,indexsize);
			/**
			 * while (x>1) {
				
					calcmiddle(0,0,x,x);
					
					//calcmiddle()
					
					calcleft(0,0,x,x);
					calcright(0,0,x,x);
					calctop(0,0,x,x);
					calcbot(0,0,x,x);
				x/=2;
				
			}*/
			int a = 0;
			int b = 0;
			int c = indexsize;
			int d = indexsize;
			int x = indexsize;
			while (x>1) {
				calcall(a,b,c,d);
				calcall(a,b,c/2,d/2);
				calcall(a,b+(d/2),c/2,d);
				calcall(a+(c/2),b,c,d/2);
				calcall(a+(c/2),b+(d/2),c,d);
				x--;
			}
			
			/**
				calcmiddle(0,0,x,x);
				calcleft(0,0,x,x);
				calcmiddle(0,0,x/2,x/2);
				calcmiddle(0,0,x/4,x/4);
				calcmiddle(0,0,x/8,x/8);
				*/
			/**
			calcmiddle(0,2,2,4);
			calcmiddle(2,0,4,2);
			calcmiddle(2,2,4,4);
			calcmiddle(0,0,2,2);
			//
			calcleft(0,0,2,2);
			calcright(0,0,2,2);
			calctop(0,0,2,2);
			calcbot(0,0,2,2);
			//
			calcleft(0,2,2,4);
			calcright(0,2,2,4);
			calctop(0,2,2,4);
			calcbot(0,2,2,4);
			//
			calcleft(2,0,4,2);
			calcright(2,0,4,2);
			calctop(2,0,4,2);
			calcbot(2,0,4,2);
			//
			calcleft(2,2,4,4);
			calcright(2,2,4,4);
			calctop(2,2,4,4);
			calcbot(2,2,4,4);
			*/
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
		
		public double calcleft(int start, int end) {
			double left;
			try {
				left = heightmap[(end+start)/2][start] = Math.round((heightmap[start][start]+calcmiddle(start, end)+heightmap[start][end]+heightmap[start+(end/2)][start-(end/2)])/4);
			}
			catch (Exception ArrayIndexOutOfBoundsException) {
				System.err.println("WARN: Exception in calcleft catched!");				
				left = heightmap[(end+start)/2][start] = Math.round((heightmap[start][start]+calcmiddle(start, end)+heightmap[start][end])/3);
			}
			return left;
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
		
		public double calcright(int start, int end) {
			double right;
			try {
				right = heightmap[(end+start)/2][end+start] = Math.round((calcmiddle(start, end)+heightmap[start][end]+heightmap[end][end]+heightmap[start+(end/2)][start+end+(end/2)])/4); 
			}
			catch (Exception ArrayIndexOutOfBoundsException) {
				System.err.println("WARN: Exception in calcright catched!");
				right = heightmap[(end+start)/2][end+start] = Math.round((calcmiddle(start, end)+heightmap[start][end]+heightmap[end][end])/3);
			}
			return right;
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
		public double calcbot(int start, int end) {
			double bottom;
			try {
				bottom = heightmap[end+start][(end+start)/2] = Math.round((calcmiddle(start, end)+heightmap[end][start]+heightmap[end][end]+heightmap[start+end+(end/2)][start+end/2])/4);
			}
			catch (Exception ArrayIndexOutOfBoundsException) {
				System.err.println("WARN: Exception in calcbot catched!");
				bottom = heightmap[end+start][(end+start)/2] = Math.round((calcmiddle(start, end)+heightmap[end][start]+heightmap[end][end])/3);
			}
			return bottom;
		}
		public double calctop(int start, int end) {
			double top;
			try {
				top = heightmap[start][end/2+start] = Math.round((heightmap[start][start]+heightmap[start][end]+calcmiddle(start, end)+heightmap[start-(end/2)][start+(end/2)])/4);
			}
			catch (Exception ArrayIndexOutOfBoundsException) {
				System.err.println("WARN: Exception in calctop catched!");
				top = heightmap[start][end/2+start] = Math.round((heightmap[start][start]+heightmap[start][end]+calcmiddle(start, end))/3);
			}
			return top;
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
		public double calcmiddle(int start, int end) {
			double middle = heightmap[end/2+start][end/2+start] = Math.round((heightmap[start][start]+heightmap[start][end]+heightmap[end][start]+heightmap[end][end])/4);
			return middle;
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
