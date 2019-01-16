import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Program {
	private JFrame frame;
	
	private JPanel addPanel;
	private JPanel editPanel;
	private JPanel listPanel;
	
	private Database db;
	
	private final String PRODUCT_DB = "resources\\db\\inventory.csv";
	private final String IMAGE_PATH = "resources\\img\\";
	private final String ADD_ICON = "plus.png";
	private final String EDIT_ICON = "pencil.png";
	private final String LIST_ICON = "list.png";
	private final String FRAME_TITLE = "Product Management";
	private final String FRAME_ICON = "badge-4x.png";
	private final String SAMPLE_IMG = "pexels-photo-1549702.jpeg";
	private final int FRAME_LOCATION_OFFSET = 200;
	private final int FRAME_SIZE = 800;
	private final Font FONT = new Font("SansSerif", Font.PLAIN, 16);
	private final Font TABLE_FONT = new Font("monospaced", Font.PLAIN, 14);
	
	public Program() {
		db = new Database(PRODUCT_DB, IMAGE_PATH);
		db.loadInventory(); // initialize list of items
		db.generateSku(); // generate SKU for each item
		db.setSamplePhoto(IMAGE_PATH + SAMPLE_IMG);
		
		frame = new JFrame();
		
		JTabbedPane pane = new JTabbedPane();
		frame.getContentPane().add(pane);
		
		addPanel = new AddPanel(this);
		listPanel = new ListPanel(this);
		editPanel = new JPanel();
		
		pane.addTab("ADD", new ImageIcon(db.getImage(IMAGE_PATH + ADD_ICON)), addPanel);
		pane.addTab("EDIT", new ImageIcon(db.getImage(IMAGE_PATH + EDIT_ICON)), editPanel);
		pane.addTab("LIST", new ImageIcon(db.getImage(IMAGE_PATH + LIST_ICON)), listPanel);
		
		frame.setTitle(FRAME_TITLE);
		frame.setIconImage(db.getImage(IMAGE_PATH + FRAME_ICON));
		frame.setSize(FRAME_SIZE, FRAME_SIZE);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(FRAME_LOCATION_OFFSET, FRAME_LOCATION_OFFSET);
		frame.setVisible(true);
		changeFont(frame, FONT);
		((ListPanel) listPanel).setTableFont(TABLE_FONT);
	}

	// change font for parent and child components
	public void changeFont(Component component, Font font){
	    component.setFont (font);
	    
	    if (component instanceof Container){
	        for(Component child : ((Container)component).getComponents()){
	            changeFont(child, font);
	        }
	    }
	}

	public Database getDB(){ return db; }
}