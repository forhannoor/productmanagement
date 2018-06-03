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

public class AddPanel extends JPanel implements ActionListener{
	private JTextField name;
	private JTextField category;
	private JTextField brand;
	private JTextField price;
	private JTextField unit;
	private JButton add;
	private JButton upload;
	private JLabel img;
	private JLabel fileName;
	private Database db;
	
	public AddPanel(Database db) {
		this.db = db;
		initGui();
	}
	
	public void initGui(){
		setLayout(null);
		
		add = new JButton("ADD");
		add.setBounds(335, 450, 115, 30);
		add.addActionListener(this);
		
		upload = new JButton("Browse");
		upload.setBounds(200, 450, 115, 30);
		upload.addActionListener(this);
		
		JLabel j1 = new JLabel("Name");
		j1.setBounds(5, 5, 100, 20);
		
		name = new JTextField();
		name.setBounds(200, 5, 250, 20);
		
		JLabel j2 = new JLabel("Category");
		j2.setBounds(5, 35, 100, 20);
		
		category = new JTextField();
		category.setBounds(200, 35, 250, 20);
		
		JLabel j3 = new JLabel("Brand");
		j3.setBounds(5, 65, 100, 20);
		
		brand = new JTextField();
		brand.setBounds(200, 65, 250, 20);
		
		JLabel j4 = new JLabel("Price");
		j4.setBounds(5, 95, 100, 20);
		
		price = new JTextField();
		price.setBounds(200, 95, 250, 20);
		
		JLabel j5 = new JLabel("Unit");
		j5.setBounds(5, 125, 100, 20);
		
		unit = new JTextField();
		unit.setBounds(200, 125, 250, 20);
		
		fileName = new JLabel();
		fileName.setBounds(200, 160, 250, 20);
		
		img = new JLabel();
		img.setBounds(200, 190, 250, 250);
		Image i = db.getImage("sample.jpg", img.getWidth(), img.getHeight());
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
		add(add);
		add(upload);
		add(fileName);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == add) {
			
		}
		
		else if(e.getSource() == upload) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			
			try {
				File file = fc.getSelectedFile();
				String name = file.getName();
				fileName.setText(name);
				
				if(name.endsWith(".jpg") || name.endsWith(".png")){
					Image i = db.getImage(file, img.getWidth(), img.getHeight());
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