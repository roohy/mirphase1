package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mir1.MasterClass;

public class MainWindow extends JFrame implements ActionListener{
	
	private JRadioButton tokenizer ; 
	private JRadioButton  addToIndex; 
	private JRadioButton  indexer;
	private JRadioButton  getDictionary;
	private ButtonGroup group ; 
	private JTextField tokenizer_inputFilePath ; 
	private JButton go ; 
	private JTextArea text_area ; 
	
	private MasterClass master ; 
	public MainWindow(){
		master = new MasterClass() ;
		initialize() ; 
	}

	private void initialize() {
		
		this.setTitle("SeachEngine!!!");
		this.setLayout(null);
		this.setLocation(400, 100);
		this.setSize(800, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		tokenizer = new JRadioButton("Tokenizer");
		tokenizer.setLocation(30, 40);
		tokenizer.setSize(100, 30);
		tokenizer.addActionListener(this);
		tokenizer.setSelected(true);
		this.add(tokenizer);
		
		addToIndex = new JRadioButton("Add To Index");
		addToIndex.setLocation(30, 100);
		addToIndex.setSize(100, 30); 
		addToIndex.addActionListener(this) ;
		this.add(addToIndex);
		
		indexer = new JRadioButton("Make Index");
		indexer.setSize(100, 30);
		indexer.setLocation(30, 160);
		indexer.addActionListener(this) ;
		this.add(indexer);
	
		getDictionary = new JRadioButton("getDictionary");
		getDictionary.setSize(100, 30);
		getDictionary.setLocation(30, 220);
		getDictionary.addActionListener(this) ;
		this.add(getDictionary);
	
		group = new ButtonGroup();
	    group.add(tokenizer);
		group.add(addToIndex);
		group.add(indexer);
		group.add(getDictionary);
		
		tokenizer_inputFilePath = new JTextField(); 
		tokenizer_inputFilePath.setSize(150, 25);
		tokenizer_inputFilePath.setLocation(140, 40);
		tokenizer_inputFilePath.setLayout(null);
		tokenizer_inputFilePath.setVisible(true);
		tokenizer_inputFilePath.setText("Data/Docs/");
		this.getLayeredPane(). add(tokenizer_inputFilePath);
		
		go = new JButton("Go!"); 
		go.setSize(130 , 55 ); 
		go.setLocation(350, 530); 
		go.addActionListener(this);
		this.add(go); 
		
		text_area = new JTextArea() ; 
		text_area.setSize(300, 650); 
		text_area.setLocation(450, 40) ;
		text_area.setVisible(true);
	//TODO	text_area.setBorder()
		text_area.setText("slkfdasldkfjas;fl");
		this.getLayeredPane().add(text_area);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == go){
			System.out.println("dasf");
			if ( tokenizer.isSelected()){
				
			}
			
			return ; 
		}
	}
	
	
}
