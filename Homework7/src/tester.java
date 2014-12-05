import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class tester
{
	public static void main(String[] args)
	{
		Scanner fin = openInputFile("files/dictSmall.txt");

		String word = "";
		int frequency = 0;
		while(fin.hasNext())
		{
			word = fin.next();
			frequency = Integer.valueOf(fin.next());
			System.out.println(word + " " + frequency);
		}
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

}
