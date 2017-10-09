package nlp;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {
	public static String getFileAsString(String fileName){
		 File f = new File(fileName);
	        try {
	            byte[] bytes = Files.readAllBytes(f.toPath());
	            return new String(bytes,"UTF-8");
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	}
}