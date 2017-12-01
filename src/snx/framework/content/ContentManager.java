package snx.framework.content;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Handles loading content
 */
public class ContentManager {
	
	/**
	 * Loads an image
	 * @param pathName The path of the file to be loaded
	 * @return The image to be loaded
	 */
	public BufferedImage loadImage(String pathName){
		try{
			return ImageIO.read(new File(pathName));
		} catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Parses a text file of specified path and returns a list of each line in the text file
	 * @param pathName The path of the text file to be read
	 * @return A list of all the lines of text in the file
	 */
	public ArrayList<String> loadText(String pathName){
		Scanner reader = null;
		try{
			ArrayList<String> toReturn = new ArrayList<String>();
			reader = new Scanner(new BufferedReader(new FileReader(pathName)));
			while(reader.hasNext()){
				toReturn.add(reader.nextLine());
			}
			return toReturn;
		} catch (IOException e){
			throw new RuntimeException(e);
		} finally {
			if (reader != null){
				reader.close();
			}
		}
	}
	
	
}
