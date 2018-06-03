import javax.swing.*;

public class Program {
	private JFrame frame;
	
	private JPanel addPanel;
	private JPanel editPanel;
	private JPanel listPanel;
	
	private Database db;
	
	public Program() {
		db = new Database("resources/db/products.db", "resources/db/categories.db", "resources/img");
		db.loadCategories();
		db.loadProducts();
		
		frame = new JFrame();
		
		JTabbedPane pane = new JTabbedPane();
		frame.getContentPane().add(pane);
		
		addPanel = new AddPanel(db);
		listPanel = new ListPanel(db);
		editPanel = new JPanel();
		
		pane.addTab("ADD", db.getIcon("plus.png"), addPanel);
		pane.addTab("EDIT", db.getIcon("pencil.png"), editPanel);
		pane.addTab("LIST", db.getIcon("list.png"), listPanel);
		
		frame.setTitle("Product Management");
		frame.setIconImage(db.getImage("badge-4x.png"));
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(600, 250);
		frame.setVisible(true);
	} 
}