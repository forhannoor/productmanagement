import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class AddPanel extends JPanel implements ActionListener{
	private JTextField productLine;
	private JTextField productType;
	private JTextField title;
	private JTextField description;
	private JTextField unitPrice;
	private JTextField stock;
	private JTextField retailPrice;
	private JTextField profitMargin;
	private JButton add;
	private JButton upload;
	private JLabel img;
	private Database db;
	private String fileToUpload; // holds the file name for item photo
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

		add = new JButton("ADD ITEM");
		//add.setPreferredSize(new Dimension(WIDTH / 6, WIDTH / 2));
		add.addActionListener(this);

		upload = new JButton("SELECT PHOTO");
		//upload.setPreferredSize(new Dimension(WIDTH / 6, WIDTH / 2));
		upload.addActionListener(this);

		img = new JLabel();
		Image i = db.getDefaultImage();
		img.setIcon(new ImageIcon(i));
		
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
		add(img);
		add(new JLabel(""));
		add(upload, "width 120:150:180");
		add(new JLabel(""));
		add(add, "width 120:150:180");
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == add) {
			
		}
		
		else if(e.getSource() == upload) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			
			try {
				File file = fc.getSelectedFile();
				fileToUpload = file.getName();
				
				if(fileToUpload.endsWith(".jpg") || fileToUpload.endsWith(".png")){
					Image i = db.getImage(file.getName(), img.getWidth(), img.getHeight());
					img.setIcon(new ImageIcon(i));
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Invalid file. Please select a valid image file.");
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
}