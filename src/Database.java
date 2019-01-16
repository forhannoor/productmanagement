import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Database {
	private String productDB;
	private String imagePath;
	private Item [] items;
	private Image samplePhoto;
	private final String [] imageExtensions = {"jpg", "bmp", "png"};
	private final String PRODUCT_PHOTOS = "product_photos";
	
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

	// return image given filename
	public Image getImage(String fileName){
		Image img = null;

		try{
			img = ImageIO.read(new File(fileName));
		} catch(Exception e){
			System.out.println(e.getMessage());
		}

		return img;
	}
	
	// return image given filename and dimension
	public Image getImage(String name, int width, int height) {
		return getImage(name).getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	// read an image given file name and upload it into img directory
	public void saveImage(String img){
		try{
			String separator = "\\";
			String [] tokens = img.replaceAll(Pattern.quote(separator), "\\\\").split("\\\\");
			File f = new File(img);
			BufferedImage b = ImageIO.read(f);
			f = new File(imagePath + "\\" + PRODUCT_PHOTOS + "\\" + tokens[tokens.length - 1]);
			ImageIO.write(b, "png", f);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
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

	public void setSamplePhoto(String fileName){
		samplePhoto = getImage(fileName);
	}

	public Image getSamplePhoto(){ return samplePhoto; }

	public Image getSamplePhoto(int width, int height){ return samplePhoto.getScaledInstance(width, height, Image.SCALE_SMOOTH); }
}