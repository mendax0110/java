package tryout;
import java.util.Scanner;
import java.lang.reflect.Array;
import java.util.InputMismatchException;

public class tryoutMethods 
{
    static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) 
    {
        System.out.print("Enter Number1: ");
        double numb1 = scanner.nextDouble();
                
        System.out.print("Enter Number2: ");
        double numb2 = scanner.nextDouble();
                
        System.out.println("First Number: " + numb1);
        System.out.println("Second Number: " + numb2);

        double[] results = TestCalc(numb1, numb2);

        System.out.println("Addition: " + results[0]);
        System.out.println("Subtraction: " + results[1]);
        System.out.println("Division: " + results[2]);
        System.out.println("Multiplication: " + results[3]);
    }
	
	public static double[] TestCalc(double numb1, double numb2)
    {
    	double[] results = new double[4];
		
    	try
    	{
            results[0] = numb1 + numb2;
            results[1] = numb1 - numb2;
            results[2] = numb1 / numb2;
            results[3] = numb1 * numb2;
    	}
    	catch(InputMismatchException e)
    	{
    		System.out.println("Invalid User input");
    	}
    	
    	return results;
    }
}
