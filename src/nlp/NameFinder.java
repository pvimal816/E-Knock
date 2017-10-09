package nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class NameFinder {
	TokenNameFinderModel nameFinderModel = null;
	NameFinderME nameFinder = null;
	TokenizerUtils tokenizer = null;
	
	public NameFinder(String nameFinderModelPath, String tokenizerModelPath){
		init(nameFinderModelPath);
		tokenizer = new TokenizerUtils(tokenizerModelPath);
		nameFinder = new NameFinderME(nameFinderModel);
	}
	
	private void init(String nameFinderModelPath){
		InputStream nameFinderModelIn = null;
		try {
			nameFinderModelIn = new FileInputStream(nameFinderModelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			nameFinderModel = new TokenNameFinderModel(nameFinderModelIn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(nameFinderModelIn != null){
				try{
					nameFinderModelIn.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}	
	}
	
	public NameFinderME getNameFinder(){
		return nameFinder;
	}
	
	public String[] findNameFromString(String str){
		String s[], tokens[];
		tokens = tokenizer.tokenizeStringUsingLearnableTokenizer(str);
		Span nameSpan[] =  nameFinder.find(tokens);
		
		List<String> strList = new ArrayList<>(); 
		for(Span span : nameSpan){
			int start = span.getStart();
			int end = span.getEnd();
			
			String temp = "";
			for(int i=start;i<end;i++){
				temp = temp + tokens[i];
			}
			strList.add(temp);
		}
		s = new String[strList.size()];
		
		
		return strList.toArray(s);
	}
	
	public String[] findNameFromFile(String fileName){
		return findNameFromString(FileUtils.getFileAsString(fileName));
	}
}
