import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Image;
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
	private JTextField searchText;
	private JButton searchBtn;
	private JLabel img;
	private JTable table;
	private Program program;
	private final int WIDTH = 120;
	private final int IMG_WIDTH = WIDTH * 3;
	private final int IMG_HEIGHT = WIDTH * 2;
	private final int SKU_LENGTH = 20;
	private final int TITLE_LENGTH = 50;
	private final int STOCK_LENGTH = 8;
	private final int UP_LENGTH = 8;
	private final int RP_LENGTH = 8;
	private final int PM_LENGTH = 8;
	
	public ListPanel(Program p) {
		program = p;
		initGui();
		initTable();
	}
	
	public void initGui(){
		setLayout(new MigLayout("wrap 3"));
		
		searchText = new JTextField(WIDTH);

		searchBtn = new JButton("SEARCH");
		searchBtn.addActionListener(this);

		String [] columns = {"SKU", "Product", "Description", "Stock", "Price", "MRP", "Product Line", "Product Type"};
		int colCount = columns.length;
		table = new JTable(500, colCount);
		TableColumnModel cm = table.getTableHeader().getColumnModel();

		for(int i = 0; i < colCount; i++){
			cm.getColumn(i).setHeaderValue(columns[i]);
		}

		cm.getColumn(0).setPreferredWidth(SKU_LENGTH * 6);
		cm.getColumn(1).setPreferredWidth(TITLE_LENGTH * 6);
		cm.getColumn(2).setPreferredWidth(TITLE_LENGTH * 3);
		cm.getColumn(3).setPreferredWidth(STOCK_LENGTH * 6);
		cm.getColumn(4).setPreferredWidth(UP_LENGTH * 8);
		cm.getColumn(5).setPreferredWidth(RP_LENGTH * 8);
		cm.getColumn(6).setPreferredWidth(TITLE_LENGTH * 4);
		cm.getColumn(7).setPreferredWidth(TITLE_LENGTH * 4);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		String paneProperty = "span 3, w " + WIDTH * 6.4 + ":" + WIDTH * 6.4 + ":" + WIDTH * 6.4  + ", h " + WIDTH * 4 + ":" + WIDTH * 4 + ":" + WIDTH * 5;
		
		img = new JLabel();
		img.setIcon(new ImageIcon(program.getDB().getSamplePhoto(IMG_WIDTH, IMG_HEIGHT)));
		
		add(pane, paneProperty);
		add(searchText);
		add(searchBtn, "wrap");
		add(img);
	}
	
	// prepare list of items to display
	public void initTable() {
		Item [] items = program.getDB().getItems();
		int rowCount = items.length;
		Item temp;
		Product p;
		DefaultTableModel d = (DefaultTableModel) table.getModel();
		d.setRowCount(rowCount);

		for(int i = 0; i < rowCount; i++){
			temp = items[i];
			p = temp.getProduct();
			table.setValueAt(temp.getSku(), i, 0);
			table.setValueAt(p.getTitle(), i, 1);
			table.setValueAt(p.getDescription(), i, 2);
			table.setValueAt(temp.getStock(), i, 3);
			table.setValueAt(p.getUnitPrice(), i, 4);
			table.setValueAt(temp.getRetailPrice(), i, 5);
			table.setValueAt(temp.getProductLine(), i, 6);
			table.setValueAt(temp.getProductType(), i, 7);
		}

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == searchBtn){
			String key = searchText.getText();
		}
	}

	public void setTableFont(Font f){
		table.setFont(f);
	}
}