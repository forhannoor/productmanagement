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
	private final String [] imageExtensions = {"jpg", "bmp", "png"};
	private final String DEFAULT_IMG = "pexels-photo-1549702.jpeg";
	
	public Database(String productDB, String imagePath) {
		this.productDB = productDB;
		this.imagePath = imagePath;
	}

	public boolean hasValidExtension(String fileName){
		boolean r = false;
		int len = imageExtensions.length;

		for(int i = 0; i < len; i++){
			if(fileName.endsWith(imageExtensions[i]) || fileName.endsWith(imageExtensions[i].toUpperCase())){
				r = true;
				break;
			}
		}

		return r;
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
				lst.add(new Item(a, b, new Product(c, d, f), e, g, h));
			}
			
			items = lst.toArray(new Item [0]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// generate SKU for each items
	public void generateSku(){
		int l = items.length;
		
		for(int i = 0; i< l; i++){ items[i].generateSku(); }
	}
	
	// read and return image given filename
	public Image getImage(boolean isNew, String name){
		Image img = null;

		if(! isNew){
			name = imagePath + "\\" + name;
		}
		
		try {
			img = ImageIO.read(new File(name));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return img;
	}
	
	// read and return image given filename and dimension
	public Image getImage(boolean isNew, String name, int width, int height) {
		return getImage(isNew, name).getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	public Item [] getItems(){ return items; }
	
	// find and return item given SKU
	public Item getItem(String s){
		Item item = null;
		int limit = items.length;
		
		for(int i = 0; i < limit; i++){
			if(items[i].getSku().equals(s)){
				item = items[i];
				break;
			}
		}
			
		return item;
	}
	
	// return default image
	public Image loadDefaultImage(){
		if(defaultImage == null){
			defaultImage = getImage(false, DEFAULT_IMG);
		}
		
		return defaultImage;
	}
	
	// return default image of specified dimension
	public Image loadDefaultImage(int width, int height){
		if(defaultImage == null){
			defaultImage = loadDefaultImage();
		}
		
		return defaultImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
}