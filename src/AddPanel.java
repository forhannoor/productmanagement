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
	private JTextField profitMargin;
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
		profitMargin = new JTextField(WIDTH);

		add = new JButton("Save");
		add.addActionListener(this);

		String buttonWidth = "width " + WIDTH + ":" + WIDTH * 3 / 2 + ":" + WIDTH * 2;
		
		add(new JLabel("Product Line"));
		add(productLine);
		add(new JLabel("Product Type"));
		add(productType);
		add(new JLabel("Title"));
		add(title);
		add(new JLabel("Description"));
		add(description);
		add(new JLabel("Unit Price"));
		add(unitPrice);
		add(new JLabel("Stock"));
		add(stock);
		add(new JLabel("Retail Price"));
		add(retailPrice);
		add(new JLabel("Profit Margin"));
		add(profitMargin);
		add(new JLabel(""));
		add(add, buttonWidth);
	}

	public void actionPerformed(ActionEvent e){
		// add button clicked event
		if(e.getSource() == add) {
			
		}
	}
}