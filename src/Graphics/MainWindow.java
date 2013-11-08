package Graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Evaluation.Evalulator;

import mir1.IndexItem;
import mir1.IndexMaker;
import mir1.MasterClass;

public class MainWindow extends JFrame implements ActionListener{
	
	private JRadioButton tokenizer ; 
	private JRadioButton  addToIndex;
	private JTextField addToIndex_directory ; 
	private JTextField addToIndex_indexfile ; 
	
	private JRadioButton  indexer;
	private JTextField indexer_directory ; 
	private JTextField indexer_indexfilename ;
	
	private JRadioButton  getDictionary;
	private JRadioButton  retrieval;
	private ButtonGroup group ; 
	private JTextField tokenizer_inputFilePath ; 
	private JTextField getDictionary_inputFilePath ; 
	private JButton go ; 
	private JTextArea text_area ; 
	private JCheckBox biWord ; 
	private JCheckBox stopWord ;
	private JTextField biword_count ;
	private JComboBox retrieval_options ; 
	private JTextField retrieval_max ; 
	private JComboBox retrieval_model ; 
	private JTextField retrieval_filePath; 
	private JTextField retrieval_query; 
	private JTextField getPostinglist_term ; 
	private JRadioButton getPostinglist ; 
	private JRadioButton evaluation ; 
	private JTextField evaluation_docsPrefix ; 
	private JTextField evaluation_querysPrefix ; 
	private JTextField evaluation_answerAddress ; 
	private JRadioButton frequentBiwords ; 
	private JTextField frequentBiwords_maxnum ; 
	private JTextField frequentBiwords_directory ; 
	
	
	private MasterClass master ; 
	public MainWindow(){
		master = new MasterClass() ;
		initialize() ; 
	}

	private void initialize() {
		
		this.setTitle("SeachEngine!!!");
		this.setLayout(null);
		this.setLocation(200, 100);
		this.setSize(1200, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		tokenizer = new JRadioButton("Tokenizer");
		tokenizer.setLocation(30, 80);
		tokenizer.setSize(100, 30);
		tokenizer.addActionListener(this);
		tokenizer.setSelected(true);
		this.getLayeredPane().add(tokenizer);
		
		tokenizer_inputFilePath = new JTextField(); 
		tokenizer_inputFilePath.setSize(200, 25);
		tokenizer_inputFilePath.setLocation(140, 80);
		tokenizer_inputFilePath.setLayout(null);
		tokenizer_inputFilePath.setVisible(true);
		tokenizer_inputFilePath.setText("Data/Docs/doc1");
		this.getLayeredPane(). add(tokenizer_inputFilePath);
		
		addToIndex = new JRadioButton("Add To Index");
		addToIndex.setLocation(30, 140);
		addToIndex.setSize(100, 30); 
		addToIndex.addActionListener(this) ;
		this.getLayeredPane().add(addToIndex);
		
		addToIndex_directory = new JTextField(); 
		addToIndex_directory.setSize(200, 25);
		addToIndex_directory.setLocation(140, 140);
		addToIndex_directory.setLayout(null);
		addToIndex_directory.setVisible(true);
		addToIndex_directory.setText("Data/Docs");
		this.getLayeredPane(). add(addToIndex_directory);
		
		addToIndex_indexfile = new JTextField(); 
		addToIndex_indexfile.setSize(200, 25);
		addToIndex_indexfile.setLocation(140, 170);
		addToIndex_indexfile.setLayout(null);
		addToIndex_indexfile.setVisible(true);
		addToIndex_indexfile.setText("MyIndexes/index1");
		this.getLayeredPane(). add(addToIndex_indexfile);
		
		
		indexer = new JRadioButton("Make Index");
		indexer.setSize(100, 30);
		indexer.setLocation(30, 200);
		indexer.addActionListener(this) ;
		this.getLayeredPane().add(indexer);
		
		indexer_directory = new JTextField(); 
		indexer_directory.setSize(200, 25);
		indexer_directory.setLocation(140, 200);
		indexer_directory.setLayout(null);
		indexer_directory.setVisible(true);
		indexer_directory.setText("Data/Docs");
		this.getLayeredPane(). add(indexer_directory);
		
		indexer_indexfilename = new JTextField(); 
		indexer_indexfilename.setSize(200, 25);
		indexer_indexfilename.setLocation(140, 230);
		indexer_indexfilename.setLayout(null);
		indexer_indexfilename.setVisible(true);
		indexer_indexfilename.setText("MyIndexes/index1");
		this.getLayeredPane(). add(indexer_indexfilename);
		
		
		
		
	
		getDictionary = new JRadioButton("getDictionary");
		getDictionary.setSize(100, 30);
		getDictionary.setLocation(30, 260);
		getDictionary.addActionListener(this) ;
		this.getLayeredPane().add(getDictionary);
	
		getDictionary_inputFilePath = new JTextField(); 
		getDictionary_inputFilePath.setSize(200, 25);
		getDictionary_inputFilePath.setLocation(140, 260);
		getDictionary_inputFilePath.setLayout(null);
		getDictionary_inputFilePath.setVisible(true);
		getDictionary_inputFilePath.setText("Data/Docs");
		this.getLayeredPane(). add(getDictionary_inputFilePath);
		
		retrieval = new JRadioButton("Retrieval");
		retrieval.setSize(100, 30);
		retrieval.setLocation(30, 320);
		retrieval.addActionListener(this) ;
		this.getLayeredPane().add(retrieval);
		
		String [] ops = {"Index" , "Directory" } ; 
		retrieval_options = new JComboBox(ops);
		retrieval_options.setSelectedIndex(0) ; 
		retrieval_options.setSize(60 , 20) ; 
		retrieval_options.setLocation(140, 320);
		this.getLayeredPane().add(retrieval_options);
		
		retrieval_max = new JTextField(); 
		retrieval_max.setSize(50, 25);
		retrieval_max.setLocation(330, 320);
		retrieval_max.setLayout(null);
		retrieval_max.setVisible(true);
		retrieval_max.setText("20");
		this.getLayeredPane(). add(retrieval_max);
		
		String [] ops2 = {"Boolean" , "WordCount" , "Total Word Count" , "Inner Product" , "Cosine similarity" } ; 
		retrieval_model = new JComboBox(ops2);
		retrieval_model.setSelectedIndex(0) ; 
		retrieval_model.setSize(105 , 20) ; 
		retrieval_model.setLocation(220, 320);
		this.getLayeredPane().add(retrieval_model);
		
		retrieval_filePath = new JTextField(); 
		retrieval_filePath.setSize(200, 25);
		retrieval_filePath.setLocation(140, 350);
		retrieval_filePath.setLayout(null);
		retrieval_filePath.setVisible(true);
		retrieval_filePath.setText("Data/Docs");
		this.getLayeredPane(). add(retrieval_filePath);
		
		retrieval_query = new JTextField(); 
		retrieval_query.setSize(200, 25);
		retrieval_query.setLocation(140, 380);
		retrieval_query.setLayout(null);
		retrieval_query.setVisible(true);
		retrieval_query.setText("your query here");
		this.getLayeredPane(). add(retrieval_query);
		
		getPostinglist = new JRadioButton("PostingList");
		getPostinglist.setSize(100, 30);
		getPostinglist.setLocation(30, 420);
		getPostinglist.addActionListener(this) ;
		this.getLayeredPane().add(getPostinglist);
		
		getPostinglist_term = new JTextField(); 
		getPostinglist_term.setSize(200, 25);
		getPostinglist_term.setLocation(140, 420);
		getPostinglist_term.setLayout(null);
		getPostinglist_term.setVisible(true);
		getPostinglist_term.setText("your term here!");
		this.getLayeredPane(). add(getPostinglist_term);
		
		evaluation = new JRadioButton("Evaluation");
		evaluation.setSize(100, 30);
		evaluation.setLocation(400, 80);
		evaluation.addActionListener(this) ;
		this.getLayeredPane().add(evaluation);
		
		evaluation_docsPrefix = new JTextField(); 
		evaluation_docsPrefix.setSize(200, 25);
		evaluation_docsPrefix.setLocation(510, 80);
		evaluation_docsPrefix.setLayout(null);
		evaluation_docsPrefix.setVisible(true);
		evaluation_docsPrefix.setText("Data/Docs");
		this.getLayeredPane(). add(evaluation_docsPrefix);
		
		evaluation_querysPrefix = new JTextField(); 
		evaluation_querysPrefix.setSize(200, 25);
		evaluation_querysPrefix.setLocation(510, 110);
		evaluation_querysPrefix.setLayout(null);
		evaluation_querysPrefix.setVisible(true);
		evaluation_querysPrefix.setText("Data/Queries");
		this.getLayeredPane(). add(evaluation_querysPrefix);
		
		evaluation_answerAddress = new JTextField(); 
		evaluation_answerAddress.setSize(200, 25);
		evaluation_answerAddress.setLocation(510, 140);
		evaluation_answerAddress.setLayout(null);
		evaluation_answerAddress.setVisible(true);
		evaluation_answerAddress.setText("Data/Relevancy/relevance");
		this.getLayeredPane(). add(evaluation_answerAddress);
		
		frequentBiwords = new JRadioButton("Freq. Biwords");
		frequentBiwords.setSize(110, 30);
		frequentBiwords.setLocation(400, 170);
		frequentBiwords.addActionListener(this) ;
		this.getLayeredPane().add(frequentBiwords);
		
		frequentBiwords_directory = new JTextField(); 
		frequentBiwords_directory.setSize(200, 25);
		frequentBiwords_directory.setLocation(510, 170);
		frequentBiwords_directory.setLayout(null);
		frequentBiwords_directory.setVisible(true);
		frequentBiwords_directory.setText("Data/Docs");
		this.getLayeredPane(). add(frequentBiwords_directory);
		
		
		group = new ButtonGroup();
	    group.add(tokenizer);
		group.add(addToIndex);
		group.add(indexer);
		group.add(getDictionary);
		group.add(retrieval);
		group.add(getPostinglist) ;
		group.add(evaluation) ;

		go = new JButton("Go!"); 
		go.setSize(130 , 55 ); 
		go.setLocation(350, 530); 
		go.addActionListener(this);
		this.getLayeredPane().add(go); 
		
		text_area = new JTextArea() ; 
		text_area.setSize(300, 600); 
		text_area.setLocation(850, 40) ;
		text_area.setVisible(true);
	//TODO	text_area.setBorder()
		text_area.setText("slkfdasldkfjas;fl");
		this.getLayeredPane().add(text_area);
		
		JScrollPane jp = new JScrollPane(text_area);
		jp.setVisible(true);
		jp.setSize(330 , 600);
		jp.setLocation(850,  40) ;
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
			if ( tokenizer.isSelected()){
				File file = new File(tokenizer_inputFilePath.getText());
				List<String> list = master.generateTokens(file) ; 
				String result = "" ; 
				for( String k : list) 
					result += k + "\n" ; 
				text_area.setText(result) ;
			}
			
			else if ( addToIndex.isSelected()){
				master.addToIndex(addToIndex_indexfile.getText(), addToIndex_directory.getText()) ; 
			}
			else if ( indexer.isSelected()){
				//public IndexMaker makeIndex(String docAddr, boolean stopWord 
				//,boolean biwordSupport, int maxBiwordCount){
				IndexMaker a = master.makeIndex(indexer_directory.getText(), stopWord.isSelected(), 
						biWord.isSelected(), Integer.parseInt(biword_count.getText()));
				master.saveIndex(indexer_indexfilename.getText());
			}
			
			else if (getDictionary.isSelected()){
				List<String> words = master.generateDictionary(getDictionary_inputFilePath.getText());
				Collections.sort(words); 
				String result = "" ;
				for ( String word : words)
					result += word + "\n" ; 
				text_area.setText(result) ;
			}
			else if (retrieval.isSelected()){
				//public List<Integer> retrievalFromFile(String addr ,String query, int maxCount,int searchType ){
				//public List<Integer> retrievalFromDocs(String docAddr, boolean stopWord
				List<Integer> docs ; 
				if ( retrieval_options.getSelectedIndex() == 0){
					docs = master.retrievalFromFile(retrieval_filePath.getText(), retrieval_query.getText(),
							Integer.parseInt(retrieval_max.getText()), retrieval_model.getSelectedIndex());
				}
				else{
					docs = master.retrievalFromDocs(retrieval_filePath.getText(), stopWord.isSelected(), 
							biWord.isSelected(), Integer.parseInt(biword_count.getText()), retrieval_query.getText(),
							Integer.parseInt(retrieval_max.getText()), retrieval_model.getSelectedIndex());
				}
				String result = "" ; 
				for ( Integer doc: docs){
					result += (" " +doc + "," ) ; 
				}
//				if ( docs.size()>0)
//					result = result.substring(result.length()-2);
				text_area.setText(result) ; 
				
			}
			else if (getPostinglist.isSelected()){
				IndexItem item = master.getTermPostingList(getPostinglist_term.getText()) ; 
				text_area.setText(item.toString());
			}
			
			else if ( evaluation.isSelected()){
				Evalulator evalulator = new Evalulator(evaluation_querysPrefix.getText(), 
						evaluation_docsPrefix.getText(), evaluation_answerAddress.getText()) ;
				try {
					text_area.setText(evalulator.eval()) ;
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} 
			}
			else if (frequentBiwords.isSelected()){
				
			}
		}
		else if ( e.getSource() == biWord){
			biword_count.setEnabled(biWord.isSelected());
		}
		else if ( e.getSource() == frequentBiwords){
			biWord.setSelected(true);
			biword_count.setEnabled(true) ;
		}
	}
	
	
}
