import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;

public class AddPanel extends JPanel implements ActionListener, FocusListener{
	private Database db;
	private JTextField id;
	private JTextField title;
	private JTextField description;
	private JSpinner vendorPrice;
	private JSpinner retailPrice;
	private JSpinner stock;
	private JButton add;
	private JLabel status;
	private final int WIDTH = 120;
	private final double SPINNER_MIN = 0;
	private final double SPINNER_MAX = 50000;
	private final double SPINNER_STEP = 0.01;
	private final String BLANK = "";
	
	public AddPanel(Database db) {
		this.db = db;
		initializeUi();
	}
	
	// Initialize UI.
	public void initializeUi(){
		setLayout(new MigLayout("wrap 2"));
		id = new JTextField(WIDTH);
		id.addFocusListener(this);
		title = new JTextField(WIDTH);
		description = new JTextField(WIDTH);
		var dimension = new Dimension(WIDTH * 2, id.getHeight());
		vendorPrice = new JSpinner(new SpinnerNumberModel(SPINNER_MIN, SPINNER_MIN, SPINNER_MAX, SPINNER_STEP));
		vendorPrice.setPreferredSize(dimension);
		stock = new JSpinner(new SpinnerNumberModel(SPINNER_MIN, SPINNER_MIN, SPINNER_MAX, SPINNER_STEP));
		stock.setPreferredSize(dimension);
		retailPrice = new JSpinner(new SpinnerNumberModel(SPINNER_MIN, SPINNER_MIN, SPINNER_MAX, SPINNER_STEP));
		retailPrice.setPreferredSize(dimension);
		add = new JButton("Save");
		add.addActionListener(this);
		String buttonWidth = "width " + WIDTH + ":" + WIDTH * 3 / 2 + ":" + WIDTH * 2;
		var temp = new JLabel("ID");
		add(temp);
		add(id);
		temp = new JLabel("Title");
		add(temp);
		add(title);
		temp = new JLabel("Description");
		add(temp);
		add(description);
		temp = new JLabel("Vendor Price");
		add(temp);
		add(vendorPrice);
		temp = new JLabel("Retail Price");
		add(temp);
		add(retailPrice);
		temp = new JLabel("Stock");
		add(temp);
		add(stock);
		temp = new JLabel(BLANK); 
		add(temp);
		add(add, buttonWidth);
		temp = new JLabel(BLANK);
		add(temp);
		status = new JLabel(BLANK);
		add(status);
	}

	// On button clicked event.
	public void actionPerformed(ActionEvent e){
		// Add button clicked event.
		if(e.getSource() == add) {
		    String i = id.getText();
			String t = title.getText();
			String d = description.getText();
			String v = vendorPrice.getValue().toString();
			String r = retailPrice.getValue().toString();
			String s = stock.getValue().toString();
			String message;
			
			// If user input is valid.
			if(isValidInput(t, d, v, r, s)) {
				var vPrice = Double.parseDouble(v);
				var rPrice = Double.parseDouble(r);
				var stk = Double.parseDouble(s);
				Item item;
				var product = new Product(t, d, vPrice, rPrice);
				
				// If ID is available.
				if(i.length() > 0) {
					var idInBytes = i.getBytes();
					item = new Item(idInBytes, product, stk);
					message = "Item updated!";
				}
				
				// If ID is not available.
				else {
					item = new Item(product, stk);
					message = "New item added!";
				}
				
				db.saveItem(item);
				statusText(message);
			}
			
			else {
				statusText("Invalid input!! Please check again.");
			}
		}
	}
	
	// Performs validation on user input for adding item.
	// Invoked when save button is clicked.
	private boolean isValidInput(String t, String d, String v, String r, String s) {
		boolean result = true;
		
		if(t.length() == 0 || d.length() == 0 ) {
			result = false;
		}
		
		try {
			var vPrice = Double.parseDouble(v);
			var rPrice = Double.parseDouble(r);
			var stk = Double.parseDouble(s);
		} catch (Exception exception) {
			result = false;
			exception.printStackTrace();
		}
		
		return result;
	}

	// On focus gained event.
	@Override
	public void focusGained(FocusEvent arg0) {
		
	}

	// On focus lost event.
	@Override
	public void focusLost(FocusEvent arg0) {
		// If ID lost focus.
		if(arg0.getSource() == id) {
			var i = id.getText();
			
			// If ID is provided.
			if(i.length() > 0) {
				var idInBytes = i.getBytes();
				var item = db.findById(idInBytes);
				
				// If item is found.
				if(item != null) {
					var product = item.getProduct();
					title.setText(product.getTitle());
					description.setText(product.getDescription());
					vendorPrice.setValue(product.getVendorPrice());
					retailPrice.setValue(product.getRetailPrice());
				}
				
				else {
					statusText("Product not found!! Please verify the ID.");
				}
			}
		}
	}
	
	private void statusText(String message) {
		status.setText(message);
	}
	
	private void statusText(double message) {
		status.setText(message + "");
	}
}