package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class MainWindow extends JFrame implements MouseListener, ActionListener{
	
	private JRadioButton tokenizer ; 
	private JRadioButton addToIndex ; 
	private JRadioButton indexer ; 
	private JRadioButton getDictionary ; 

	private ButtonGroup group ; 
	
	public MainWindow(){
		this.setTitle("SeachEngine!!!");
		this.setLayout(null);
		this.setLocation(400, 100);
		this.setSize(400, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		initialize() ; 
	}

	private void initialize() {
		
		tokenizer = new JRadioButton("Tokenizer");
		tokenizer.setLocation(100, 100);
		tokenizer.setVisible(true);
		tokenizer.addActionListener(this) ;
		this.add(tokenizer);
		
		addToIndex = new JRadioButton("addToIndex");
		addToIndex.setLocation(100, 120);
		addToIndex.addActionListener(this) ;
		this.add(addToIndex);
		
		indexer = new JRadioButton("indexer");
		indexer.setLocation(100, 140);
		indexer.addActionListener(this) ;
		this.add(indexer);
	
		getDictionary = new JRadioButton("getDictionary");
		getDictionary.setLocation(100, 160);
		getDictionary.addActionListener(this) ;
		this.add(getDictionary);
	
		ButtonGroup group = new ButtonGroup();
	    group.add(tokenizer);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
}
