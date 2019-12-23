import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class AddPanel extends JPanel implements ActionListener{
	private Database db;
	private JTextField productLine;
	private JTextField productType;
	private JTextField title;
	private JTextField description;
	private JTextField unitPrice;
	private JTextField stock;
	private JTextField retailPrice;
	private JButton add;
	private final int WIDTH = 120;
	
	public AddPanel(Database db) {
		this.db = db;
		initGui();
	}
	
	public void initGui(){
		setLayout(new MigLayout("wrap 2"));
		
		productLine = new JTextField(WIDTH);
		productType = new JTextField(WIDTH);
		title = new JTextField(WIDTH);
		description = new JTextField(WIDTH);
		unitPrice = new JTextField(WIDTH);
		stock = new JTextField(WIDTH);
		retailPrice = new JTextField(WIDTH);

		add = new JButton("Save");
		add.addActionListener(this);

		String buttonWidth = "width " + WIDTH + ":" + WIDTH * 3 / 2 + ":" + WIDTH * 2;
		var temp = new JLabel("Product Line");
		add(temp);
		add(productLine);
		temp = new JLabel("Product Type");
		add(temp);
		add(productType);
		temp = new JLabel("Title");
		add(temp);
		add(title);
		temp = new JLabel("Description");
		add(temp);
		add(description);
		temp = new JLabel("Unit Price");
		add(temp);
		add(unitPrice);
		temp = new JLabel("Stock");
		add(temp);
		add(stock);
		temp = new JLabel("Retail Price");
		add(temp);
		add(retailPrice);
		temp = new JLabel(""); 
		add(temp);
		add(add, buttonWidth);
	}

	public void actionPerformed(ActionEvent e){
		// Add button clicked action.
		if(e.getSource() == add) {
			
		}
	}
}