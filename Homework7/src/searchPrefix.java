
public class searchPrefix
{
	public static void main(String[] args)
	{
		WordItem words[] = new WordItem[6];
		words[0] = new WordItem("a", 7300);
		words[1] = new WordItem("aa", 3);
		words[2] = new WordItem("aachen", 9);
		words[3] = new WordItem("aalto", 2);
		words[4] = new WordItem("ab", 4);
		words[5] = new WordItem("be", 5);
		
		int first = findPrefix(words, "a");
		
		
	}
	public static int findPrefix(WordItem[] ara, String target)
	{
		int high = ara.length-1;
		int mid = 0;
		int low = 0;

		while (low <= high)
		{
			mid = (low + high)/2;

			if (target.compareTo(ara[mid].getWord()) > 0)
			{
				high = mid;
				if(target.compareTo(ara[mid].getWord()) < 0)
				{
					return mid;
				}
			}
			else if (target.compareTo(ara[mid].getWord()) < 0)
			{
				low = mid+1;
			}
			else
			{
				return mid;
			}
		}

		return -1;
	}
}
