import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

public class Program {
	private JFrame frame;
	
	private JPanel addPanel;
	private JPanel editPanel;
	private JPanel listPanel;
	
	private Database db;
	
	private final String PRODUCT_DB = "resources\\db\\inventory.csv";
	private final String IMAGE_PATH = "resources\\img";
	private final String ADD_ICON = "plus.png";
	private final String EDIT_ICON = "pencil.png";
	private final String LIST_ICON = "list.png";
	private final String FRAME_TITLE = "Product Management";
	private final String FRAME_ICON = "badge-4x.png";
	private final int FRAME_LOCATION = 200;
	private final int FRAME_SIZE = 800;
	
	public Program() {
		db = new Database(PRODUCT_DB, IMAGE_PATH);
		db.loadInventory(); // initialize list of items
		db.generateSku(); // generate SKU for each item
		
		frame = new JFrame();
		
		JTabbedPane pane = new JTabbedPane();
		frame.getContentPane().add(pane);
		
		addPanel = new AddPanel(db);
		listPanel = new ListPanel(db);
		editPanel = new JPanel();
		
		pane.addTab("ADD", new ImageIcon(db.getImage(false, ADD_ICON)), addPanel);
		pane.addTab("EDIT", new ImageIcon(db.getImage(false, EDIT_ICON)), editPanel);
		pane.addTab("LIST", new ImageIcon(db.getImage(false, LIST_ICON)), listPanel);
		
		frame.setTitle(FRAME_TITLE);
		frame.setIconImage(db.getImage(false, FRAME_ICON));
		frame.setSize(FRAME_SIZE, FRAME_SIZE);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(FRAME_LOCATION, FRAME_LOCATION);
		frame.setVisible(true);
	} 
}