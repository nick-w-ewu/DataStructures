import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class searchPrefix
{
	public static void main(String[] args)
	{
		WordItem words[] = readDictonary();
//		words[0] = new WordItem("a", 7300);
//		words[1] = new WordItem("aa", 3);
//		words[2] = new WordItem("aachen", 9);
//		words[3] = new WordItem("aalto", 2);
//		words[4] = new WordItem("ab", 4);
//		words[5] = new WordItem("account", 8);
//		words[6] = new WordItem("be", 5);
		for(WordItem word : words)
		{
			System.out.println(word);
		}
		
		Trie2 dictonary = new Trie2(words);
		dictonary.insertDictonary();
		System.out.println(dictonary.findWord("a"));
//		System.out.println(dictonary.findWord("aa"));
//		System.out.println(dictonary.findWord("aac"));
//		System.out.println(dictonary.findWord("aach"));
//		System.out.println(dictonary.findWord("b"));
		
//		int first = findPrefix(words, "ac");
//		int last = findLast(words, "ad");
//		ArrayList<WordItem> subwords = subArray(words, first, last);
//		Collections.sort(subwords, new WordItem.WordComparator(true));
//		for(WordItem word : subwords)
//		{
//			System.out.println(word);
//		}
		
	}
	
	public static WordItem[] readDictonary()
	{
		Scanner fin = openInputFile("files/dictSmall.txt");
		WordItem dict[] = new WordItem[30];
		String word = "";
		int frequency = 0, i = 0;
		while(fin.hasNext())
		{
			word = fin.next();
			frequency = Integer.valueOf(fin.next());
			dict[i] = new WordItem(word, frequency);
			i++;
		}
		return dict;
	}
	
	public static Scanner openInputFile(String fileName)
	{
		Scanner fileScanner = null;
		File fileHandle;

		try
		{
			fileHandle = new File(fileName);

			fileScanner = new Scanner(fileHandle).useDelimiter("\\,|\n");
		}
		catch(Exception e)
		{
			System.out.println("File " + fileName + " was not found.");
		}

		return fileScanner;
	}
	
//	public static int findPrefix(WordItem[] ara, String target)
//	{
//		int high = ara.length-1;
//		int mid = 0;
//		int low = 0;
//
//		while (low <= high)
//		{
//			mid = (low + high)/2;
//
//			if (target.compareTo(ara[mid].getWord()) < 0)
//			{
//				high = mid-1;
//				if(target.compareTo(ara[mid-1].getWord()) > 0)
//				{
//					return mid;
//				}
//			}
//			else if (target.compareTo(ara[mid].getWord()) > 0)
//			{
//				low = mid+1;
//			}
//			else if (target.compareTo(ara[mid].getWord()) == 0)
//			{
//				return mid;
//			}
//		}
//
//		return -1;
//	}
//	
//	public static int findLast(WordItem[] ara, String target)
//	{
//		int high = ara.length-1;
//		int mid = 0;
//		int low = 0;
//
//		while (low <= high)
//		{
//			mid = (low + high)/2;
//
//			if (target.compareTo(ara[mid].getWord()) < 0)
//			{
//				high = mid-1;
//			}
//			else if (target.compareTo(ara[mid].getWord()) > 0)
//			{
//				low = mid+1;
//				if(target.compareTo(ara[mid+1].getWord()) < 0)
//				{
//					return mid;
//				}
//			}
//			else if (target.compareTo(ara[mid].getWord()) == 0)
//			{
//				return mid;
//			}
//		}
//
//		return -1;
//	}
//	
//	public static ArrayList<WordItem> subArray(WordItem[] words, int low, int high)
//	{
//		int i;
//		ArrayList<WordItem> newList = new ArrayList<WordItem>();
//		for(i = low; i <= high; i++)
//		{
//			newList.add(words[i]);
//		}
//		return newList;
//	}
}
