package eg.edu.alexu.csd.datastructure.hangman;
import java.io.BufferedReader;
import java.util.Random;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Hangman implements IHangman {
	private String[] dictionary;
	private static String secretword;
	private int n1;
	private static int maxwrong=6;
	public  String[] ReadFile(String File , int size) throws IOException
	{ int i=0;
	 String[] arr2 = new String[size];
		try {
	    String arr1; 
		FileReader y =new FileReader(File);
		BufferedReader x = new BufferedReader(y);
		while((arr1=x.readLine()) != null)
		{
			arr2[i]=arr1;
			i++;
		}
		y.close();
		} 
		catch(IOException e )
		{
			System.out.println("No File");
		}
		return arr2;
	}
	public void setDictionary(String[] words)
	{
		dictionary = words;
	}
	 public String selectRandomSecretWord()
	{ 
		Random number = new Random();
		 n1 = number.nextInt(20001);
		 secretword = dictionary[n1] ;
		 int i ;
		 StringBuilder sbuild = new StringBuilder(secretword);
			for(i=0;i<secretword.length();i++)
			{
				sbuild.setCharAt(i,'-');
				secretword=sbuild.toString();
			}
		return dictionary[n1];
	}
	public String guess(Character c) 
	{ int j ;
	int found = 0;
	StringBuilder s2build = new StringBuilder(secretword);
	for(j=0;j<dictionary[n1].length();j++)
	{
		if ((dictionary[n1].charAt(j)) == c)
		{
			s2build.setCharAt(j,c);
			secretword=s2build.toString();
			found=1;
		}
	}
	if(found==1)
	{
		System.out.println("Good Guess");
		System.out.println(secretword);
		if(secretword.contains("-")== false)
		{
			System.out.println("You Win");
		}
		return secretword ;
	}
	else
	{
		maxwrong--;
		System.out.println("Wrong");
		System.out.println("Remaining trials = "+ maxwrong);
		if(maxwrong==0)
		{
			System.out.println("You Lose\nThe Word is "+dictionary[n1]);
			return null;
		}
		else {
			System.out.println(secretword);
			return secretword;
		}
	}
	}
	public void setMaxWrongGuesses(Integer max)
	{
			if(max>0)
			{
				maxwrong=max;
			}
			else
			{
				maxwrong=1;
			}
	}
	public static void main(String[] args) throws IOException 
	{
		int i ,flag=0;
		Hangman game = new Hangman();
		game.setDictionary(game.ReadFile("file.txt",20001 ));
		game.selectRandomSecretWord();
		game.setMaxWrongGuesses(maxwrong);
		System.out.println("Please Enter a Letter:");
		while (secretword.contains("-")== true )
		{
			flag=0;
		Scanner scan = new Scanner(System.in);
		char c = scan.next().charAt(0);
		for(i=0;i<secretword.length();i++)
		{
			if ((secretword.charAt(i)) == c)
			{
				System.out.println("You already entered this Letter ");
				flag=1;
				break;
			}
		}
	if (flag==0)
	{
		if(!Character.isLetter(c))
		{
			System.out.println("Wrong Input");
		}
		else
		{
			game.guess(c);
		}
		}
	}
	}
	
}