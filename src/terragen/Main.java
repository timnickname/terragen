package terragen;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Terrain t1 = new Terrain(500,500);
		t1.Createheightmap();
		t1.CreateImage();
		t1.Showheightmap();
		t1.visualize();
	}	

}
