package terragen;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Terrain t1 = new Terrain(5,5);
		//t1.square(0,0,4,4); // 0, 0, height, width
		//t1.CreateImage(); //creates image
		//t1.Showheightmap(); // shows the heightmap in the console
		//t1.visualize(); //saves image as file
		Generator terragen = new Generator(17);
		terragen.heightmap[0][0] = 7;
		terragen.heightmap[0][16] = 5;
		terragen.heightmap[16][0] = 1;
		terragen.heightmap[16][16] = 3;
		terragen.diamond(0,4);
		terragen.toString();
		terragen.visualize();

	}	

}
