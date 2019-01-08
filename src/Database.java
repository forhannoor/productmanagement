import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Database {
	private String productDB;
	private String imagePath;
	private Item [] items;
	private Image defaultImage;
	private final String DEFAULT_IMG = "pexels-photo-1549702.jpeg";
	
	public Database(String productDB, String imagePath) {
		this.productDB = productDB;
		this.imagePath = imagePath;
		defaultImage = getImage(DEFAULT_IMG);
	}
	
	// parse CSV file to initialize list of items
	public void loadInventory(){
		try {
			ArrayList <Item> lst = new ArrayList<Item>(50);
			File file = new File(productDB);
			Scanner s = new Scanner(file);
			s.useDelimiter(",|\n");
			s.nextLine();
			String a, b, c, d;
			int e;
			double f, g, h;
			
			while(s.hasNext()){
				a = s.next(); // product line
				b = s.next(); // product type
				c = s.next(); // product title
				d = s.next(); // product description
				e = Integer.parseInt(s.next()); // stock 
				f = Double.parseDouble(s.next()); // unit price
				g = Double.parseDouble(s.next()); // retail price
				h = Double.parseDouble(s.next()); // profit margin
				lst.add(new Item(a, b, new Product(c, d, f), e, h));
			}
			
			items = lst.toArray(new Item [0]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// return ImageIcon given a file name
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
	
	// return Image given a file name
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