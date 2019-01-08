import java.awt.Image;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListPanel extends JPanel implements ListSelectionListener{
	private JTextField name;
	private JTextField category;
	private JTextField brand;
	private JTextField price;
	private JTextField unit;
	private JLabel img;
	private JList<String> list;
	private Database db;
	
	public ListPanel(Database db) {
		this.db = db;
		initList();
		initGui();
	}
	
	public void initGui(){
		setLayout(null);
		
		list.addListSelectionListener(this);
		JScrollPane pane = new JScrollPane(list);
		pane.setBounds(5, 5, 170, 200);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		add(pane);
		
		JLabel j1 = new JLabel("Name");
		j1.setBounds(220, 5, 100, 20);
		
		name = new JTextField();
		name.setBounds(320, 5, 250, 20);
		
		JLabel j2 = new JLabel("Category");
		j2.setBounds(220, 35, 100, 20);
		
		category = new JTextField();
		category.setBounds(320, 35, 250, 20);
		
		JLabel j3 = new JLabel("Brand");
		j3.setBounds(220, 65, 100, 20);
		
		brand = new JTextField();
		brand.setBounds(320, 65, 250, 20);
		
		JLabel j4 = new JLabel("Price");
		j4.setBounds(220, 95, 100, 20);
		
		price = new JTextField();
		price.setBounds(320, 95, 250, 20);
		
		JLabel j5 = new JLabel("Unit");
		j5.setBounds(220, 125, 100, 20);
		
		unit = new JTextField();
		unit.setBounds(320, 125, 250, 20);
		
		img = new JLabel();
		img.setBounds(320, 155, 250, 250);
		Image i = db.getDefaultImage(img.getWidth(), img.getHeight());
		img.setIcon(new ImageIcon(i));
		
		add(j1);
		add(name);
		add(j2);
		add(category);
		add(j3);
		add(brand);
		add(j4);
		add(price);
		add(j5);
		add(unit);
		add(img);
	}
	
	// prepare list of items to display
	public void initList() {
		Item [] temp = db.getItems();
		int limit = temp.length;
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for(int i = 0; i < limit; i++) {
			model.addElement(temp[i].toString());
		}
		
		list = new JList<String>(model);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(! e.getValueIsAdjusting()) {
			// String selection = list.getSelectedValue();
			// String [] tokens = selection.split(" ");
			// int id = Integer.parseInt(tokens[0]);
			// Product p = db.getProduct(id);
			
			// name.setText(p.getName());
			// category.setText(p.getCategory() + "");
			// brand.setText(p.getBrand());
			// price.setText(p.getPrice() + "");
			// unit.setText(p.getUnit());
			// Image i = db.getImage(p.getImg(), img.getWidth(), img.getHeight());
			// img.setIcon(new ImageIcon(i));
		}
	}
}