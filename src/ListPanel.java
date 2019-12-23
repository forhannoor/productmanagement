// Represents UI and logic for ListPanel tab.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class ListPanel extends JPanel implements ActionListener{
	private Database db;
	private JTextField searchText;
	private JButton searchBtn;
	private JLabel img;
	private JTable table;
	private final int WIDTH = 120;
	private final int SKU_LENGTH = 20;
	private final int TITLE_LENGTH = 50;
	private final int STOCK_LENGTH = 8;
	private final int UP_LENGTH = 8;
	private final int RP_LENGTH = 8;
	private final String [] TABLE_COLUMNS = new String [] {"SKU", "Product", "Description", "Stock", "Price", "MSRP", 
			"Product Line", "Product Type"};
	
	public ListPanel(Database db) {
		this.db = db;
		initializeUi();
		initializeTable();
	}
	
	// Initializes UI for displaying items.
	public void initializeUi(){
		setLayout(new MigLayout("wrap 3"));
		searchText = new JTextField(WIDTH);
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(this);
		int columnCount = TABLE_COLUMNS.length;
		table = new JTable(500, columnCount);
		TableColumnModel columnModel = table.getTableHeader().getColumnModel();

		// Set headers for table columns.
		for(int i = 0; i < columnCount; ++i){
			columnModel.getColumn(i).setHeaderValue(TABLE_COLUMNS[i]);
		}

		// Define preferred width for columns.
		columnModel.getColumn(0).setPreferredWidth(SKU_LENGTH * 6);
		columnModel.getColumn(1).setPreferredWidth(TITLE_LENGTH * 6);
		columnModel.getColumn(2).setPreferredWidth(TITLE_LENGTH * 3);
		columnModel.getColumn(3).setPreferredWidth(STOCK_LENGTH * 6);
		columnModel.getColumn(4).setPreferredWidth(UP_LENGTH * 8);
		columnModel.getColumn(5).setPreferredWidth(RP_LENGTH * 8);
		columnModel.getColumn(6).setPreferredWidth(TITLE_LENGTH * 4);
		columnModel.getColumn(7).setPreferredWidth(TITLE_LENGTH * 4);
		
		var pane = new JScrollPane(table);
		// Make vertical scroll-bar visible.
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Make horizontal scroll-bar visible.
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		String paneProperty = "span 3, w " + WIDTH * 6.4 + ":" + WIDTH * 6.4 + ":" + WIDTH * 6.4  + ", h " 
				+ WIDTH * 4 + ":" + WIDTH * 4 + ":" + WIDTH * 5;
		
		add(pane, paneProperty);
		add(searchText);
		add(searchBtn, "wrap");
	}
	
	// Populates table with item information.
	public void initializeTable() {
		var items = db.getItems();
		int itemCount = (items == null) ? 0 : items.size();
		Item temp;
		Product p;
		var tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(itemCount);

		for(int i = 0; i < itemCount; ++i){
			temp = items.get(i);
			p = temp.getProduct();
//			table.setValueAt(temp.getSku(), i, 0);
//			table.setValueAt(p.getTitle(), i, 1);
//			table.setValueAt(p.getDescription(), i, 2);
//			table.setValueAt(temp.getStock(), i, 3);
//			table.setValueAt(p.getUnitPrice(), i, 4);
//			table.setValueAt(temp.getRetailPrice(), i, 5);
//			table.setValueAt(temp.getProductLine(), i, 6);
//			table.setValueAt(temp.getProductType(), i, 7);
		}

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		// search button clicked event
		if(e.getSource() == searchBtn){
			
		}
	}

	public void setTableFont(Font f){
		table.setFont(f);
	}
}