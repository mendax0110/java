package basicJavaCourse;
import java.util.Scanner;
import java.lang.reflect.Array;
import java.util.InputMismatchException;

public class TestProject 
{
	public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Boolean restartProgram = true;

        while (restartProgram) 
        {
            try 
            {
                System.out.print("Enter Number1: ");
                double numb1 = scanner.nextDouble();
                
                System.out.print("Enter Number2: ");
                double numb2 = scanner.nextDouble();
                
                System.out.println("First Number: " + numb1);
                System.out.println("Second Number: " + numb2);
                
                System.out.print("Enter operation ( +, -, /, * ): ");
                char operation = scanner.next().charAt(0);
                double result = 0.0;
                
                switch(operation)
                {
	                case '+':
	                	result = numb1 + numb2;
	                	break;
	                case '-':
	                	result = numb1 - numb2;
	                	break;
	                case '/':
	                	if(numb2 != 0)
	                	{
	                		result = numb1 / numb2;
	                	}
	                	else
	                	{
	                		System.out.println("Division by zero is not allowed!");
	                		continue;
	                	}
	                	break;
	                case '*':
	                	result = numb1 * numb2;
	                	break;
	                default:
	                	System.out.println("Invalid operation");
	                	continue;
                }
                
                System.out.println("Result: " + result);
                
                System.out.print("Do you want to calculate any other numbers? (y/n)");
                
                String decision = scanner.next();
                
                restartProgram = decision.equalsIgnoreCase("y");
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Invalid User input. Please enter only valid data!");
                // Consume the invalid input
                scanner.nextLine();
            }
            
            forLoops();
        }
        
        // Close the Scanner after exiting the loop
        scanner.close();
        System.out.println("Program has ended");
    }
    
    public static void forLoops()
    {
    	for(int i = 0; i < 10; i++)
    	{
    		System.out.println(i);
    	}
    }
}

