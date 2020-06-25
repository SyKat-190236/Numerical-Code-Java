package roots_of_equation;
import java.util.*;
public class NewtonRapson_A {

	public static void main(String[] args) {
		double xi = 3.5;
		/*System.out.println("Enter initial value: ");
		Scanner input = new Scanner(System.in);
		xi = input.nextDouble();*/
		for(int i=0; i<3; i++)
		{
			xi = xi - (function(xi) / fPrime(xi));
			System.out.println("Root of the equation at "+(i+1)+" iteration = "+xi);
		}
	}
	public static double function(double x)
	{
		return Math.pow(x, 3)-6*Math.pow(x, 2)+11*x-6.1;
	}
	public static double fPrime(double x)
	{
		return 3*Math.pow(x, 2)-12*x+11;
	}

}
