import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Database {
	private String productDB;
	private String categoryDB;
	private String imageDB;
	private Product [] products;
	private Category [] categories;
	
	public Database(String productDB, String categoryDB, String imageDB) {
		this.productDB = productDB;
		this.categoryDB = categoryDB;
		this.imageDB = imageDB;
	}
	
	public void loadCategories()
	{
		try {
			File file = new File(categoryDB);
			Reader r = new BufferedReader(new FileReader(file));
			StreamTokenizer st = new StreamTokenizer(r);
			ArrayList<Category> list = new ArrayList<Category>();
			int currentToken = st.nextToken();
			int id = -1;
			String name = null;
			
			while(currentToken != StreamTokenizer.TT_EOF) {
				id = (int) st.nval;
				currentToken = st.nextToken();
				name = st.sval;
				currentToken = st.nextToken();
				list.add(new Category(id, name));
			}
			
			r.close();
			
			categories = new Category[list.size()];
			
			for(int i = 0; i < categories.length; i++){
				categories[i] = list.get(i);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadProducts()
	{
		try {
			File file = new File(productDB);
			Reader r = new BufferedReader(new FileReader(file));
			StreamTokenizer st = new StreamTokenizer(r);
			ArrayList<Product> list = new ArrayList<Product>();
			int currentToken = st.nextToken();
			int id = -1;
			String name = null;
			int category = -1;
			String brand = null;
			float price = 0f;
			String unit = null;
			String img = null;
			
			while(currentToken != StreamTokenizer.TT_EOF) {
				id = (int) st.nval;
				currentToken = st.nextToken();
				name = st.sval;
				currentToken = st.nextToken();
				category = (int) st.nval;
				currentToken = st.nextToken();
				brand = st.sval;
				currentToken = st.nextToken();
				price = (float) st.nval;
				currentToken = st.nextToken();
				unit = st.sval;
				currentToken = st.nextToken();
				img = st.sval;
				currentToken = st.nextToken();
				list.add(new Product(id, name, category, brand, price, unit, img));
			}
			
			r.close();
			
			products = new Product[list.size()];
			
			for(int i = 0; i < products.length; i++){
				products[i] = list.get(i);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Product getProduct(int id)
	{
		Product p = null;
		
		for(int i = 0; i < products.length; i++) {
			if(products[i] != null && products[i].getId() == id) {
				p = products[i];
				break;
			}
		}
		
		return p;
	}
	
	public Product getProduct(String name)
	{
		Product p = null;
		
		for(int i = 0; i < products.length; i++) {
			if(products[i] != null && products[i].getName().equals(name)) {
				p = products[i];
				break;
			}
		}
		
		return p;
	}
	
	public Product [] getProducts(){
		return products;
	}
	
	public Category [] getCategories() {
		return categories;
	}
	
	public ImageIcon getIcon(String name){
		ImageIcon icon = null;
		
		try {
			Image img = ImageIO.read(new File(imageDB + "/" + name));
			icon = new ImageIcon(img);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return icon;
	}
	
	public Image getImage(String name){
		Image img = null;
		
		try {
			img = ImageIO.read(new File(imageDB + "/" + name));
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