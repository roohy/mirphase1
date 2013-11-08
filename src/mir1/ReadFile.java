package mir1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadFile { 
	
	String directory_addr;
	
	public ReadFile(String addr){
		System.out.println("New File Reader made to read from directory:"+addr);
		this.directory_addr = addr;
	}
	
	public List<Doc> getDocsBuffers(){
		System.out.println("Listing Files in Directory...");
		List<File> fileList = ReadFile.listf(this.directory_addr); //listing all files in directory
		return this.getBufferedFromList(fileList); //this line converts all files to buffered list and returns them
		
		
	}
	
	
	//this function returns gets a file list and retrn buffered file reader for each one
	public List<Doc> getBufferedFromList( List<File> input_files){
		List<Doc> result = new ArrayList<Doc>();
		for( File file: input_files){
			if(!file.isFile()){
				continue;
			}
			else{
				try{
					Scanner sc = new Scanner(file.getAbsolutePath()).useDelimiter(".+doc");
					int fileID = sc.nextInt();
					System.out.println(" Reading file: "+fileID);
					result.add( new Doc (new BufferedReader(new FileReader(file)),file.getAbsolutePath(),fileID));
					
				}catch(IOException e){
					System.out.println("Got Stock in adding a file to buffered reader stack!!");
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	/*this file gets a directory name as a string and return all files in that
	//the result of this function is passed to getAllDocs function to get
	//buffered reader.*/
    public static List<File> listf(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        
        //uncomment the code below in case you wanted to list all - it is not recommended nor encouraged ask roohy:D
        //resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
                resultList.add(file);
            } else if (file.isDirectory()) {
                System.out.println("This is a Directory not a File, Uncomment the code below to read inside the directories eighter");
            	//resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        System.out.println("Returning Results to Master");
        return resultList;
    } 
	
	
}
