
// Handles database operations, i.e. reading from and/or writing to a file.

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Database {
	// File to perform read/write operations.
	// List of items in the inventory.
	private ArrayList<Item> items;
	private Scanner scanner;
	private final int INI_SIZE = 50;

	// Initializes list of items from file.
	public void initializeItems(String fileName) {
		try {
			var file = new File(fileName);
			items = new ArrayList<Item>(INI_SIZE);
			byte[] content = Files.readAllBytes(file.toPath());

			for (byte b : content) {
				System.out.print(b);
			}
//			scanner = new Scanner(file);
//			String a, b, c, d;
//			int e;
//			double f, g, h;
//			
//			while(scanner.hasNext()){
//				a = scanner.next(); // product line
//				b = scanner.next(); // product type
//				c = scanner.next(); // product title
//				d = scanner.next(); // product description
//				e = Integer.parseInt(scanner.next()); // stock 
//				f = Double.parseDouble(scanner.next()); // unit price
//				g = Double.parseDouble(scanner.next()); // retail price
//				h = Double.parseDouble(scanner.next()); // profit margin
////				lst.add(new Item(a, b, new Product(c, d, f), e, g, h));
//			}
//			
//			items = lst.toArray(new Item [0]);
//			scanner.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	// Finds an item with matching ID.
	public Item findById(byte[] id) {
		Item item = null;
		int itemCount = items.size();

		for (int i = 0; item == null && i < itemCount; ++i) {
			if (Arrays.equals(id, items.get(i).getId())) {
				item = items.get(i);
			}
		}

		return item;
	}

	// Returns an image given filename.
	public Image getImage(String fileName) {
		Image img = null;

		try {
			img = ImageIO.read(new File(fileName));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return img;
	}

	// Returns an image given filename and dimension.
	public Image getImage(String name, int width, int height) {
		return getImage(name).getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	// Insert a new item or update an existing one in the items list.
	public void SaveItem(Item item) {
		byte [] id = item.getId();
		int itemCount = items.size();
		boolean isNew = true;

		for (int i = 0; i < itemCount && isNew; ++i) {
			// If existing item.
			if (Arrays.equals(id, items.get(i).getId())) {
				// Update item.
				items.set(i, item);
				isNew = false;
			}
		}
		
		// If new item.
		if(isNew) {
			items.add(item);
		}
	}
}