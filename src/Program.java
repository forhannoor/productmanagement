
// Main program that functions as a delegate between other programs.

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Program implements WindowListener{
	private JFrame frame;
	private JPanel addPanel;
	private JPanel listPanel;
	private Database db;

	private final int FRAME_LOCATION_OFFSET = 200;
	private final int FRAME_SIZE = 800;
	private final String ITEMS_FILE = "resources\\db\\items.txt";
	private final String IMAGE_PATH = "resources\\img\\";
	private final String ADD_ICON = "plus.png";
	private final String LIST_ICON = "list.png";
	private final String FRAME_TITLE = "Product Management";
	private final String ADD_PANEL_TITLE = "Add Item";
	private final String LIST_PANEL_TITLE = "Inventory";
	private final String FRAME_ICON = "badge-4x.png";
	private final Font FONT = new Font("SansSerif", Font.PLAIN, 16);
	private final Font TABLE_FONT = new Font("monospaced", Font.PLAIN, 14);

	public Program() {
		db = new Database();
		db.initializeItems(ITEMS_FILE);
		frame = new JFrame();
		var pane = new JTabbedPane();
		frame.getContentPane().add(pane);
		addPanel = new AddPanel(db);
		listPanel = new ListPanel(db);
		pane.addTab(ADD_PANEL_TITLE, new ImageIcon(db.getImage(IMAGE_PATH + ADD_ICON)), addPanel);
		pane.addTab(LIST_PANEL_TITLE, new ImageIcon(db.getImage(IMAGE_PATH + LIST_ICON)), listPanel);
		frame.addWindowListener(this);
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

	// Changes font for parent and children components.
	public void changeFont(Component component, Font font) {
		component.setFont(font);

		if (component instanceof Container) {
			for (Component child : ((Container) component).getComponents()) {
				changeFont(child, font);
			}
		}
	}

	// Returns the database connection handler/link.
	public Database getDatabaseHandler() {
		return db;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {
		db.saveInventory(ITEMS_FILE);
	}
}