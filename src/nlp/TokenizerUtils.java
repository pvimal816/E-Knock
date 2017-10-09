package nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class TokenizerUtils {
	TokenizerModel model = null;
	Tokenizer learnableTokenizer = null;
	
	public TokenizerUtils(String modelFile){
		initTokenizer(modelFile);
		learnableTokenizer = new TokenizerME(model);
	}
	
	private void initTokenizer(String modelFile) {
		InputStream modelIn = null;
		
		try {
			modelIn = new FileInputStream(modelFile);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		try{
			model = new TokenizerModel(modelIn);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(model != null){
				try {
					modelIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Tokenizer getLearnableTokenizer(){
		return learnableTokenizer;
	}
	
	public Tokenizer getSimpleTokenizer(){
		return WhitespaceTokenizer.INSTANCE;
	}
	
	public String[] tokenizeFileUsingLearnableTokenizer(String fileName){
		return learnableTokenizer.tokenize(FileUtils.getFileAsString(fileName));
	}
	public String[] tokenizeStringUsingLearnableTokenizer(String s){
		return learnableTokenizer.tokenize(s);
	}
	public String[] tokenizeFileUsingWhiteSpaceTokenizer(String fileName){
		return getSimpleTokenizer().tokenize(FileUtils.getFileAsString(fileName));
	}
	public String[] tokenizeStringUsingWhiteSpaceTokenizer(String s){
		return getSimpleTokenizer().tokenize(s);
	}
}