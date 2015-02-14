package no.la1k.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
	
	
	public static void writeFile(String path, String msg) throws IOException{
		  Files.write(Paths.get(path), msg.getBytes());
	}
	
	public static String readFile(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
