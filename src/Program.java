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
	private final String FRAME_TITLE = "Program Management";
	private final String FRAME_ICON = "badge-4x.png";
	private final int FRAME_LOCATION_X = 600;
	private final int FRAME_LOCATION_Y = 250;
	private final int FRAME_WIDTH = 600;
	private final int FRAME_HEIGHT = 600;
	
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
		
		pane.addTab("ADD", new ImageIcon(db.getImage(ADD_ICON)), addPanel);
		pane.addTab("EDIT", new ImageIcon(db.getImage(EDIT_ICON)), editPanel);
		pane.addTab("LIST", new ImageIcon(db.getImage(LIST_ICON)), listPanel);
		
		frame.setTitle(FRAME_TITLE);
		frame.setIconImage(db.getImage(FRAME_ICON));
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(FRAME_LOCATION_X, FRAME_LOCATION_Y);
		frame.setVisible(true);
	} 
}