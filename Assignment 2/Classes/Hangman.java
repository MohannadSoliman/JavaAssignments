package eg.edu.alexu.csd.datastructure.hangman.cs23;

import java.lang.Math;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class Hangman implements IHangman{
	String dictionary[] = new String[1000];
	int counter=0;
	Integer maximum;
	String chosenWord;
	String showCorrect = "--------------------------------------------------------------------";
	int flag;
	
	public void readFile(String words[], String fileName){
		File file = new File(fileName);
		int i=0;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	    String line;
	    try {
			while ((line = reader.readLine()) != null)
			{
				words[i] = line;
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setDictionary(String words[])
	{
		this.dictionary = words;
		int i=0;
		while(i < dictionary.length && dictionary[i] != null)
		{
			counter++;
			i++;
		}
	}
	public String selectRandomSecretWord() {
		Random rand = new Random();
		this.chosenWord = dictionary[rand.nextInt(counter)];
		return chosenWord;
	}
	public void setMaxWrongGuesses(Integer max)
	{
		if(max == null)
		{
			this.maximum = 1;
		}else
		{
			this.maximum = max;
		}
	}
	public String guess(Character c) throws Exception
	{

		for(int i=0; i<chosenWord.length(); i++)
		{
			if(!Character.isAlphabetic(chosenWord.charAt(i)))
			{
				throw new Exception("Secret buggy word!");
			}
		}
		if(c.equals(null))
		{
			return showCorrect.substring(0, chosenWord.length());
		}
		flag=0;
		for(int i=0; i<chosenWord.length(); i++)
		{

			if(Character.toLowerCase(chosenWord.charAt(i)) == Character.toLowerCase(c))
			{
				flag = 1;
				char showCorrectGuess[] = showCorrect.toCharArray();
				showCorrectGuess[i] = chosenWord.charAt(i);
				showCorrect = String.valueOf(showCorrectGuess);
			}
		}
		if(flag == 0)
		{
			maximum--;
			System.out.println("Wrong guesses remaining: " + maximum);
		}
		if(maximum == 0 || chosenWord.equals(showCorrect.substring(0, chosenWord.length())))
		{
			return null;
		}
		return showCorrect.substring(0, chosenWord.length());
	}
}
