import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergio Lopez
 **/

public class CapitaExecise 
{	
	public static void main(String[] args) 
	{		
		WordChainSolver MyWordChainSolver = new WordChainSolver();
		
		MyWordChainSolver.GetWords();
		try 
		{
			List<String> listAllWords = MyWordChainSolver.ReadMyFile();
			List<String> listPartialWords = new ArrayList<String>();
			
			//It initialises the sublist with the first word
			listPartialWords.add(MyWordChainSolver.firstWord);
			
			//It removes all words with different length to our First word from the List
			listAllWords = MyWordChainSolver.RemoveWordsDifferentSize(listAllWords);
			
			//It verifies if from the starting word it can go to the ending word straight away 
			if ((MyWordChainSolver.ValidateWord(MyWordChainSolver.lastWord, listPartialWords.get(0)).equals("Repeated")) 
					|| (MyWordChainSolver.ValidateWord(MyWordChainSolver.lastWord, listPartialWords.get(0)).equals("Not yet")))
			{
				listPartialWords.add(MyWordChainSolver.lastWord);
				listAllWords = listPartialWords;
			}
			else
			{
				listAllWords = MyWordChainSolver.NavigateAllList(listAllWords, listPartialWords);
			}
			
			MyWordChainSolver.Printing(listAllWords); 
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
