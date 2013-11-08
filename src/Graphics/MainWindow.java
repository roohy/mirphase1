package Graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mir1.MasterClass;
import mir1.StopWord;

public class MainWindow extends JFrame implements ActionListener{
	
	private JRadioButton tokenizer ; 
	private JRadioButton  addToIndex; 
	private JRadioButton  indexer;
	private JRadioButton  getDictionary;
	private ButtonGroup group ; 
	private JTextField tokenizer_inputFilePath ; 
	private JTextField getDictionary_inputFilePath ; 
	private JButton go ; 
	private JTextArea text_area ; 
	private JCheckBox biWord ; 
	private JCheckBox stopWord ;
	private JTextField biword_count ; 
	
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
		tokenizer.setLocation(30, 80);
		tokenizer.setSize(100, 30);
		tokenizer.addActionListener(this);
		tokenizer.setSelected(true);
		this.add(tokenizer);
		
		addToIndex = new JRadioButton("Add To Index");
		addToIndex.setLocation(30, 140);
		addToIndex.setSize(100, 30); 
		addToIndex.addActionListener(this) ;
		this.add(addToIndex);
		
		indexer = new JRadioButton("Make Index");
		indexer.setSize(100, 30);
		indexer.setLocation(30, 200);
		indexer.addActionListener(this) ;
		this.add(indexer);
	
		getDictionary = new JRadioButton("getDictionary");
		getDictionary.setSize(100, 30);
		getDictionary.setLocation(30, 260);
		getDictionary.addActionListener(this) ;
		this.add(getDictionary);
	
		getDictionary_inputFilePath = new JTextField(); 
		getDictionary_inputFilePath.setSize(150, 25);
		getDictionary_inputFilePath.setLocation(140, 260);
		getDictionary_inputFilePath.setLayout(null);
		getDictionary_inputFilePath.setVisible(true);
		getDictionary_inputFilePath.setText("Data/Docs");
		this.getLayeredPane(). add(getDictionary_inputFilePath);
		
		group = new ButtonGroup();
	    group.add(tokenizer);
		group.add(addToIndex);
		group.add(indexer);
		group.add(getDictionary);
		
		tokenizer_inputFilePath = new JTextField(); 
		tokenizer_inputFilePath.setSize(150, 25);
		tokenizer_inputFilePath.setLocation(140, 80);
		tokenizer_inputFilePath.setLayout(null);
		tokenizer_inputFilePath.setVisible(true);
		tokenizer_inputFilePath.setText("Data/Docs/doc1");
		this.getLayeredPane(). add(tokenizer_inputFilePath);
		
		go = new JButton("Go!"); 
		go.setSize(130 , 55 ); 
		go.setLocation(350, 530); 
		go.addActionListener(this);
		this.getLayeredPane().add(go); 
		
		text_area = new JTextArea() ; 
		text_area.setSize(300, 600); 
		text_area.setLocation(450, 40) ;
		text_area.setVisible(true);
	//TODO	text_area.setBorder()
		text_area.setText("slkfdasldkfjas;fl");
		this.getLayeredPane().add(text_area);
		
		JScrollPane jp = new JScrollPane(text_area);
		jp.setVisible(true);
		jp.setSize(330 , 600);
		jp.setLocation(450,  40) ;
        add(jp, BorderLayout.CENTER);
		
        stopWord = new JCheckBox("Stop Word");
        stopWord.setSize(100, 30);
        stopWord.setLocation(50, 30) ;
        this.getLayeredPane().add(stopWord);
     
        biWord = new JCheckBox("Biword");
        biWord.setSize(80, 30);
        biWord.setLocation(200, 30) ;
        biWord.addActionListener(this);
        this.getLayeredPane().add(biWord);
        
        biword_count = new JTextField(); 
		biword_count.setSize(50, 25);
		biword_count.setLocation(290, 30);
		biword_count.setLayout(null);
		biword_count.setVisible(true);
		biword_count.setText("1000");
		biword_count.setEnabled(false);
		this.getLayeredPane(). add(biword_count);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == go){
			System.out.println("dasf");
			if ( tokenizer.isSelected()){
				File file = new File(tokenizer_inputFilePath.getText());
				List<String> list = master.generateTokens(file) ; 
				String result = "" ; 
				for( String k : list) 
					result += k + "\n" ; 
				text_area.setText(result) ;
			}
			
			if ( getDictionary.isSelected()){
				
				
				
			}
		}
		if ( e.getSource() == biWord){
			biword_count.setEnabled(biWord.isSelected());
		}
	}
	
	
}
