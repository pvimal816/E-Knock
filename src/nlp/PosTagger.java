package nlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class PosTagger{
	
	POSModel model = null;
	TokenizerUtils tokenizer = null;
	POSTaggerME tagger = null;

	public PosTagger(String modelPath,String tokenizerModelPath){
		init(modelPath);
		tokenizer = new TokenizerUtils(tokenizerModelPath);
		tagger = new POSTaggerME(model);
	}

	private void init(String modelPath) {
		InputStream modelIn = null;
		try{
			modelIn = new FileInputStream(modelPath);
			model= new POSModel(modelIn);
		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if(modelIn != null){
				try{
					modelIn.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public POSTaggerME getPOSTagger(){
		return tagger;
	}
	
	public String[] tagifyFile(String fileName){
		return tagger.tag(tokenizer.tokenizeFileUsingLearnableTokenizer(fileName));
	}
	
	public String[] tagifyString(String s){
		return tagger.tag(tokenizer.tokenizeStringUsingLearnableTokenizer(s));
	}

	public String[] tagifyString(String[] tokens) {
		return tagger.tag(tokens);
	}
}