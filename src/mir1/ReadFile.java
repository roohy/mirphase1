package mir1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFile { 
	
	String directory_addr;
	
	public ReadFile(String addr){
		this.directory_addr = addr;
	}
	
	public List<BufferedReader> getDocsBuffers(){
		
		List<File> fileList = ReadFile.listf(this.directory_addr); //listing all files in directory
		return this.getBufferedFromList(fileList); //this line converts all files to buffered list and returns them
		
		
	}
	//this is a test method
	public BufferedReader getNextBuffer(){
		try{
			BufferedReader buffer = new BufferedReader(new FileReader("/home/roohy/workspace/data/doc1"));
			return buffer;
		}
		catch (IOException e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	//this function returns gets a file list and retrn buffered file reader for each one
	public List<BufferedReader> getBufferedFromList( List<File> input_files){
		List<BufferedReader> result = new ArrayList<BufferedReader>();
		for( File file: input_files){
			if(!file.isFile()){
				continue;
			}
			else{
				try{

					result.add( new BufferedReader(new FileReader(file)));
					
				}catch(IOException e){
					System.out.println("Got Stock in adding a file to buffered reader stack");
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
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        //System.out.println(fList);
        return resultList;
    } 
	
	
}
