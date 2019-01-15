import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
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
	private Program program;
	private String fileToUpload; // holds the file name for item photo
	private final int WIDTH = 120;
	private final int IMG_WIDTH = WIDTH * 3;
	private final int IMG_HEIGHT = WIDTH * 2;
	
	public AddPanel(Program p) {
		program = p;
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
		add.addActionListener(this);

		upload = new JButton("SELECT PHOTO");
		upload.addActionListener(this);

		img = new JLabel();
		img.setIcon(new ImageIcon(program.getDB().getSamplePhoto(IMG_WIDTH, IMG_HEIGHT)));

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
		add(img);
		add(new JLabel(""));
		add(upload, buttonWidth);
		add(new JLabel(""));
		add(add, buttonWidth);
	}

	public void actionPerformed(ActionEvent e){
		// add button action
		if(e.getSource() == add) {
			
		}
		
		// upload button action
		else if(e.getSource() == upload) {
			Database db = program.getDB();
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("Image File", "jpg", "png"));
			int returnVal = fc.showOpenDialog(null); // display file chooser dialog
			fileToUpload = null;

			if(returnVal == JFileChooser.APPROVE_OPTION) {
			   fileToUpload = fc.getSelectedFile().getAbsolutePath();
			}
			
			if(fileToUpload != null){ // file is selected
				if(db.hasValidExtension(fileToUpload)){ // file with valid extension
					img.setIcon(new ImageIcon(db.getImage(fileToUpload, IMG_WIDTH, IMG_HEIGHT)));
					db.saveImage(fileToUpload);
				}

				else{ // file with invalid extension
					JOptionPane.showMessageDialog(null, "Invalid file! Please select valid file.");
				}
			}
		}
	}
}