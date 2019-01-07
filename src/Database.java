import java.awt.Image;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Database {
	private String productDB;
	private String imagePath;
	private Item [] items;
	private Image defaultImage;
	private final String DEFAULT_IMG = "pexels-photo-1549702.jpg";
	private final int SIZE = 199; // inventory size
	
	public Database(String productDB, String imagePath) {
		this.productDB = productDB;
		this.imagePath = imagePath;
		//defaultImage = getImage(DEFAULT_IMG);
	}
	
	public void loadInventory(){
		try {
			File file = new File(productDB);
			Scanner s = new Scanner(file);
			items = new Item [SIZE];
			s.useDelimiter(",");
			s.nextLine();
			String a, b, c, d;
			int e;
			double f, g, h;
			
			for(int i = 0; i < SIZE; i++){
				a = s.next();
				b = s.next();
				c = s.next();
				d = s.next();
				e = Integer.parseInt(s.next());
				f = Double.parseDouble(s.next());
				g = Double.parseDouble(s.next());
				h = Double.parseDouble(s.next());
				items[i] = new Item(a, b, new Product(c, d, f), e, h);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ImageIcon getIcon(String name){
		ImageIcon icon = null;
		
		try {
			Image img = ImageIO.read(new File(imagePath + "/" + name));
			icon = new ImageIcon(img);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return icon;
	}
	
	public Image getImage(String name){
		Image img = null;
		
		try {
			img = ImageIO.read(new File(imagePath + "/" + name));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return img;
	}
	
	public Image getImage(String name, int width, int height) {
		return getImage(name).getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public Image getImage(File file, int width, int height) {
		Image img = null;
		
		try {
			img = ImageIO.read(file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
}