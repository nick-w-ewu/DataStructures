import java.util.*;

public class Trie2
{
	
	private TrieNode root;
	WordItem dict[];
	
	private class TrieNode
	{
		Map<Character, TrieNode> children = new TreeMap<>();
		//TreeMap is java build-in structure, 
		//Basically it acts like a Hashtable or Hashmap, establishing a mapping between Key and Value
		//Unlike hash table, keys in TreeMap are sorted!
		boolean aword = false;
		ArrayList<String> mostUsed;
	}
	
	public Trie2(WordItem[] d)
	{
		this.root = new TrieNode();
		this.dict = d;
	}
	
	public void insertDictonary()
	{
		int i;
		for(i = 0; i < this.dict.length; i++)
		{
			insertString(dict[i].getWord());
		}
	}

	public void insertString(String s)
	{
		insertString(root, s);
	}
	
	private void insertString(TrieNode root, String s)
	{
		TrieNode cur = root;
		String prefix = "";
		for (char ch : s.toCharArray())
		{
			prefix += ch;
			TrieNode next = cur.children.get(ch);
			if (next == null)
			{
				cur.children.put(ch, next = new TrieNode());
				//insert most used words here, compute for each prefix
				cur.mostUsed = computeMostUsed(prefix);
			}
			cur = next;
		}
		cur.aword = true;
	}
	
	private ArrayList<String> computeMostUsed(String prefix)
	{
		if(prefix == null)
		{
			return null;
		}
		int low, high;
		low = findPrefix(prefix);
		
		String temp = prefix.substring(0, prefix.length()-1);
		char last = prefix.charAt(prefix.length()-1);
		last++;
		temp = temp + last;
		
		high = findLast(temp);
		
		ArrayList<String> ret = subArray(low, high);
		return ret;
	}
	
	private int findPrefix(String target)
	{
		int high = this.dict.length-1;
		int mid = 0;
		int low = 0;

		while (low <= high)
		{
			mid = (low + high)/2;

			if (target.compareTo(this.dict[mid].getWord()) < 0)
			{
				high = mid-1;
				if(target.compareTo(this.dict[mid-1].getWord()) > 0)
				{
					return mid;
				}
			}
			else if (target.compareTo(this.dict[mid].getWord()) > 0)
			{
				low = mid+1;
			}
			else if (target.compareTo(this.dict[mid].getWord()) == 0)
			{
				return mid;
			}
		}

		return -1;
	}
	
	public int findLast(String target)
	{
		int high = this.dict.length-1;
		int mid = 0;
		int low = 0;

		while (low <= high)
		{
			mid = (low + high)/2;

			if (target.compareTo(this.dict[mid].getWord()) < 0)
			{
				high = mid-1;
			}
			else if (target.compareTo(this.dict[mid].getWord()) > 0)
			{
				if(mid == this.dict.length-1)
				{
					return mid;
				}
				else if((target.compareTo(this.dict[mid+1].getWord()) < 0))
				{
					return mid;
				}
				low = mid+1;
			}
			else if (target.compareTo(this.dict[mid].getWord()) == 0)
			{
				return mid;
			}
		}

		return -1;
	}
	
	public ArrayList<String> subArray(int low, int high)
	{
		int i;
		ArrayList<WordItem> tempList = new ArrayList<WordItem>();
		ArrayList<String> newList = new ArrayList<String>();
		for(i = low; i <= high; i++)
		{
			tempList.add(this.dict[i]);
		}
		Collections.sort(tempList, new WordItem.WordComparator(true));
		i = 0;
		
		for(WordItem word : tempList)
		{
			newList.add(word.getWord());
			if(i > 9)
			{
				break;
			}
			i++;
		}
		return newList;
	}
	
	public void printSorted()
	{
		printSorted(root, "");
	}

	private void printSorted(TrieNode node, String s)
	{
		if (node.aword)
		{
			System.out.println(s);
			System.out.println(node.mostUsed);
		}
		for (Character ch : node.children.keySet())
		{
			printSorted(node.children.get(ch), s + ch);
		}
	}
	
	public ArrayList<String> findWord(String s)
	{
		return findWord(root, s);
	}
	
	private ArrayList<String> findWord(TrieNode node, String s)
	{
		if(s != null)
		{
			String rest = s.substring(1);              //rest is a substring of s, by excluding the first character in s
			char ch = s.charAt(0);                     //ch is the first letter of s
			TrieNode child = node.children.get(ch);	   //return the child that ch associated with. 
			if(s.length() == 1 && child != null)//if s contains only one letter, and current node has a child associated with that letter, we find the prefix in Trie!
			{
				return child.mostUsed;
			}	//base case
			if(child == null)
			{
				return null;
			}
			else
			{
				return findWord(child, rest);//recursive, In this way, we follow the path of the trie from root down towards leaf
			}
		}
		return null;
	}

}
