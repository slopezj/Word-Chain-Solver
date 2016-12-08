import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordChainSolver {	
	static String firstWord;
	static String lastWord;
	static int wordLength;
	static boolean foundIt = false;
	
	public void GetWords() 
	{	
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
				
		try 
		{			
			 EnterWords(br);
			 while (firstWord.length() != lastWord.length())
			 {
				 EnterWords(br);					
			 }	
			 
			 wordLength = firstWord.length();
		} 
		catch (IOException e) 
		{			
			e.printStackTrace();
		}
	}
	
	public void EnterWords(BufferedReader br) throws IOException
	{
		System.out.println("Enter the first word:");
		firstWord = br.readLine();
		System.out.println("Enter the last word:");
		lastWord = br.readLine();	
	}	
	
	public List<String> ReadMyFile() throws IOException
	{		
	    List<String> content = null;
	    FileReader reader = null;
	    
	    try 
	    {
	    	URL url = getClass().getResource("Dictionary.txt");
	    	File file = new File(url.getPath());
				    
	        reader = new FileReader(file);
	        char[] wordsAsChars = new char[(int) file.length()];
	      	        
	        reader.read(wordsAsChars);
	        reader.close();
	        	        
	        String wordsAsString = String.valueOf(wordsAsChars);
	        String[] wordsAsList = wordsAsString.split(" ");
	        //The program will work with a List because it will give us freedom to delete elements easily
	        content = new LinkedList<String>(Arrays.asList(wordsAsList));
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    } 
	    finally 
	    {
	        if(reader !=null)
	        {reader.close();}
	    }
	    return content;
	}	
	
	public List<String> NavigateAllList(List<String> listAllWords, List<String> listPartialWords)
	{
		List<String> availableWordsToUse =	new ArrayList<String>(listAllWords);
				
		int numWords = listAllWords.size();
		String tempWord = "";
		
		for (int i = 0; i < numWords; i++)
		{			
			switch (ValidateWord(listAllWords.get(i),listPartialWords.get(listPartialWords.size() - 1)))
			{
				case "Repeated":
					//I have decided that if the current word in the final list is repeated in the dictionary it will not be added in the final list
					//listPartialWords.add(listAllWords.get(i));
					break;
				case "Not yet":
					listPartialWords.add(listAllWords.get(i));
					tempWord = ValidateWord(lastWord, listAllWords.get(i));
					if ((tempWord.equals("Repeated")) || (tempWord.equals("Not yet")))
					{
						foundIt = true;
						listPartialWords.add(lastWord);
						return listPartialWords;
					}
					availableWordsToUse.removeAll(listPartialWords); //It is removing also the duplicates from listPartialWords 
					NavigateAllList(availableWordsToUse, listPartialWords);
					if (foundIt) return listPartialWords;
					availableWordsToUse = new ArrayList<String>(listAllWords);
					listPartialWords.remove(listAllWords.get(i));
					break;
				default:
					//availableWordsToUseBackUp.remove(listAllWords.get(i));					
			}			
		} 
		
		availableWordsToUse.clear();
		
		return availableWordsToUse;
	}
	
	public String ValidateWord(String nextWordInList, String previousWordInList)
	{ 
		int numLetters = 0;
	 	  
		for (int i = 0; i < wordLength; i++)
		{
			if (previousWordInList.charAt(i) == nextWordInList.charAt(i))
			{
				numLetters++;
			}
		}
		
		if (numLetters == wordLength) return "Repeated";
		else if (numLetters == wordLength - 1) return "Not yet";
		else return "Not valid word";
	}	
	
	public void Printing (List<String> myFinalList)
	{
		if (myFinalList.isEmpty()) 
		{
			System.out.println("There is no valid path");
		}
		
		else System.out.println(myFinalList);
	}
	
	//If the dictionary is very big this approach might take a lot of time
	public List<String> RemoveWordsDifferentSize (List<String> listAllWords)
	{
		List<String> listToBeRemoved = new ArrayList<String>();
	
	  	int numWords = listAllWords.size();
	
		for (int i = 0;i < numWords; i++)
		{
			if (listAllWords.get(i).length() != wordLength )
			{
				listToBeRemoved.add(listAllWords.get(i));
			}
		}
		
		listAllWords.removeAll(listToBeRemoved);
		
		return listAllWords;
	}
	
}

