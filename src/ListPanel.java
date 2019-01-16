import java.awt.Font;
import java.awt.Image;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.miginfocom.swing.MigLayout;

public class ListPanel extends JPanel implements ListSelectionListener, ActionListener{
	private JTextField searchText;
	private JButton searchBtn;
	private JLabel img;
	private JList<String> list;
	private Program program;
	private final int WIDTH = 120;
	private final int IMG_WIDTH = WIDTH * 3;
	private final int IMG_HEIGHT = WIDTH * 2;
	
	public ListPanel(Program p) {
		program = p;
		initList();
		initGui();
	}
	
	public void initGui(){
		setLayout(new MigLayout("wrap 3"));
		
		searchText = new JTextField(WIDTH);

		searchBtn = new JButton("SEARCH");
		searchBtn.addActionListener(this);

		list.addListSelectionListener(this);

		JScrollPane pane = new JScrollPane(list);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		String paneProperty = "span 3, h " + WIDTH * 4 + ":" + WIDTH * 4 + ":" + WIDTH * 5;
		
		img = new JLabel();
		img.setIcon(new ImageIcon(program.getDB().getSamplePhoto(IMG_WIDTH, IMG_HEIGHT)));
		
		add(pane, paneProperty);
		add(searchText);
		add(searchBtn, "wrap");
		add(img);
	}
	
	// prepare list of items to display
	public void initList() {
		Item [] items = program.getDB().getItems();
		int itemCount = items.length;
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for(int i = 0; i < itemCount; i++) {
			model.addElement(items[i].toString());
		}
		
		list = new JList<String>(model);
	}

	public void changeFont(Font f){
		list.setFont(f);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(! e.getValueIsAdjusting()) {
			String selection = list.getSelectedValue();
			String [] tokens = selection.split(" ");
			System.out.println(tokens[0]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == searchBtn){
			String key = searchText.getText();
		}
	}
}