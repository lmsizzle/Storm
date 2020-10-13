package Storm;

//for java util
import java.util.*;

//for scanner
import java.util.Scanner;

//for io exception
import java.io.IOException;

//for file
import java.io.File;

//for output stream
import java.io.OutputStream;

//for file output stream
import java.io.FileOutputStream;


/**
 * storm class used to run 
 */
public class Storm 
{

	//creates dataset
	DataSet ds;
	
	//creates user input
	Scanner userInput;

	/**
	 * method for choosing the type
	 */
	void chooseType()
	{
		//prints out
		System.out.println("Pick the underlying data structure: ");
		System.out.println("(1). ArrayList");
		System.out.println("(2). TreeSet");
		System.out.println("(3). TreeMap");
		System.out.println("(4). Heap");
		System.out.println("(5). HashSet");
		System.out.println("(6). HashMap");
		System.out.println("(7). AVLTree");
		
		//string for decision from user input
		String decision = userInput.nextLine();

		//if user chooses 1
		if(decision.equals("1"))
			
			//sets data set as data array list
			ds = new DataArrayList();
		
		//or if user chooses 2
		else if(decision.equals("2"))
			
			//sets data set to data tree set
			ds = new DataTreeSet();
		
		//or if user chooses 3
		else if(decision.equals("3"))
			
			//sets data set to data tree map
			ds = new DataTreeMap();
		
		//or if user chooses 4
		else if(decision.equals("4"))
			
			//sets data set to data heap
			ds = new DataHeap();
		
		//or if user chooses 5
		else if(decision.equals("5"))
			
			//sets data set to data hash set
			ds = new DataHashSet();
		
		//or if user chooses 6
		else if(decision.equals("6"))
			
			//sets data set to data hash map
			ds = new DataHashMap();
		
		//or if user chooses 7
		else if(decision.equals("7"))
			
			//sets data set to avl data
			ds = new DataAVL();
		//or
		else
		{
			//prints out not an option
			System.out.println("Not an Option");
			
			//exits
			System.exit(0);
		}
	}

	/**
	 * decide method
	 */
	void decide()
	{
		//prints out asking for user to submit u or nu
		System.out.println("G(u)i OR (nu) Gui");
		
		//creates user input
		userInput = new Scanner(System.in);
		
		//decision from user input next line
		String decision = userInput.nextLine();

		//if user selects u
		if(decision.equals("u")) 
		{
			//gui main
			StormGUI.main(new String[1]);
			
			//exits
			System.exit(0);
		}
		
		//or if user chooses nu
		else if(decision.equals("nu"))
		{
			//calls choose type
			chooseType();
			
			//returns
			return;
		}
		
		//or
		else
		{
			//print out message
			System.out.println("Not a valid option");
		}
	}

	/**
	 * begin method
	 */
	void begin()
	{
		
		//prints out message
		System.out.print("Enter data folder name: ");

		//gets file name from user
		String filename = userInput.nextLine();

		//if file name equals /
		if(filename.charAt(filename.length()-1) == '/')
			
			//sets substring
			filename = filename.substring(0, filename.length()-1);
		//try
		try
		{
			//folder as filename
			File folder = new File(filename);
			
			//file array for all files in folder
			File[] allfiles = folder.listFiles();

			//for all files
			for(File file : allfiles)
			{
				//filename equals get name
				String fn = file.getName();
				
				//if equals csv
				if(fn.length() > 3 && fn.substring(fn.length()-4, fn.length()).equals(".csv"))
				{
					//dataset equals input reader process
					ds = InputReader.process(filename + "/" + fn, ds);
				}
			}
		}
		//catches exception
		catch(Exception e)
		{
			//print stack trace
			e.printStackTrace();
			
			//prints out messae
			System.out.println("Error");
			
			//exits
			System.exit(0);
		}

	}

	/**
	 * loop method
	 * @return
	 */
	boolean loop()
	{
		//prints out telling the user the options
		System.out.print("Enter (o)utput, (sum)mary, (d)elete, (s)ort, (f)ind, or (q)uit: ");
		
		//lets user put choice
		String choice = userInput.nextLine();

		//if o
		if(choice.equals("o"))
		{
			
			//asks for file name
			System.out.print("Enter output file name: ");
			
			//choice from user
			choice = userInput.nextLine();

			//output stream equals null
			OutputStream os = null;
			
			//if nothing
			if(choice.equals(""))
			{
				//prints out output stream
				os = System.out;
			}
			
			//or
			else
			{
				//try
				try 
				{
					//output stream equals file choice
					os = new FileOutputStream(new File(choice));
				}
				//catches exception
				catch (IOException e)
				{
					//prints out inaccessible
					System.out.println("File inaccessible.");
					
					//prints out output stream
					os = System.out;
				}
			}

			//output equals data set output
			String output = ds.output();

			//try
			try 
			{
				//output stream bytes and length
				os.write(output.getBytes(), 0, output.getBytes().length);
			}
			
			//catches exception
			catch (IOException e)
			{
				//prints out error, try again
				System.out.println("Error, please try again. ");
				
				//print stack trace
				e.printStackTrace();
				
				//returns true
				return true;
			}
		}
		
		//or if sum
		else if(choice.equals("sum"))
		{
			
			//prints out asking for summary year
			System.out.print("Enter summary year: ");
			
			//sets integer year from user
			Integer year = userInput.nextInt();
			
			//sets dummy string from user
			String dummy = userInput.nextLine();
			
			//if less than 0 or greater than 9999
			if(year < 0 || year > 9999)
			{
				//prints out saying year is invalid
				System.out.println("Invalid year, please try again. ");
				
				//returns true
				return true;
			}
			//prints out data set summary year
			System.out.println(ds.summary((int)year));

		}
		
		//or if f
		else if(choice.equals("f"))
		{
			//prints out
			System.out.println("Enter search field (0-9): ");
			
			//sets index as user input
			Integer index = userInput.nextInt();
			
			//string for dumy line from user input
			String dummy = userInput.nextLine();

			//if less than 0 or greater than 10
			if(0 > index || index >= 10)
			{
				//returns true
				return true;
			}
			
			//prints out
			System.out.println("Enter field value: ");
			
			//search is user input
			String search = userInput.nextLine();


			//prints out data set find index
			System.out.println(ds.find(index, search));
		}

		//or if
		else if(choice.equals("d"))
		{
			//prints out
			System.out.print("Enter EVENT_ID: ");

			//if event id is null
			Integer eventID = null;
			
			//while true
			while(true)
			{
				//try
				try 
				{
					//event id string equals user input
					String eventIDStr = userInput.nextLine();
					
					//event is equals parse int event id string
					eventID = Integer.parseInt(eventIDStr);
				} 
				//catches exception
				catch(Exception e)
				{
					//prints out
					System.out.println("Not a valid id!");
					System.out.print("Enter EVENT_ID: ");
					
					//continues
					continue;
				}
				
				//breaks
				break;
			}

			//sets how many as data set assess id
			int howMany = ds.assess(eventID);

			//prints out
			System.out.println(howMany + " matching values found!");
			System.out.print("Should they be removed?(y/N)");

			//sets answer as user input from next line
			String answer = userInput.nextLine();

			//if y
			if(answer.equals("y") || answer.equals("Y"))
				
				//data set delete entries from event id
				ds.deleteEntries(eventID);
			
			//or
			else
				
				//quits without removing
				System.out.println("Quitting without removal");
		}

		//or if equals s
		else if(choice.equals("s"))
		{
			//prints out
			System.out.println("Enter sorting options (1-10): ");

			//sort type equals null
			Integer sortType = null;
			
			//while true
			while(true)
			{
				
				//try
				try 
				{
					//sort type string equals user input next line
					String sortTypeStr = userInput.nextLine();
					
					//sort typer equals parse integer sort type string
					sortType = Integer.parseInt(sortTypeStr);

				}
				//catches exception
				catch(Exception e)
				{
					
					//prints out
					System.out.println("Not a valid id!");
					System.out.print("Enter EVENT_ID: ");
					
					//continues
					continue;
				}
				
				//if 1 is less than or equal to sort type int, sort type and greater than or equal to 10
				if(1 <= (int) sortType && (int) sortType <= 10)
					
					//breaks
					break;
				
				//prints out
				System.out.println("Not a valid id");
				
				//prints out
				System.out.print("Enter EVENT_ID: ");
			}

			//data set sorting by sort type
			ds.sortBy(sortType);
		}
		
		//or if choice equals q
		else if(choice.equals("q"))
		{
			//returns false
			return false;
		}
		
		//returns true
		return true;
	}

	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args)
	{
		//storm app
		Storm app = new Storm();

		//decide method
		app.decide();
		
		//begin method
		app.begin();
		
		//while app
		while(app.loop())
		{

		}
		
		//prints out message and quits
		System.out.println("Thanks for using storm.");
	}
}
