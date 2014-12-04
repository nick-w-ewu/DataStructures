
public class searchPrefix
{
	public static int binarySearch(String[] ara, String target)
	{
		int high = ara.length-1;
		int mid = 0;
		int low = 0;

		while (low <= high)
		{
			mid = (low + high)/2;

			if (ara[mid].compareTo(target) > 0)
			{
				high = mid-1;
			}
			else if (ara[mid].compareTo(target) < 0)
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
