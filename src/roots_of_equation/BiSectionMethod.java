package roots_of_equation;
//This is the solve of Lab Experiment 1-a
import java.util.*;
public class BiSectionMethod {

	public static void main(String[] args) {
		double xU=0,xL=0.5,xR=0;
		Scanner input = new Scanner(System.in);

		System.out.println("Enter upper bound: ");
		xU = input.nextDouble();
		System.out.println("Enter lower bound: ");
		xL = input.nextDouble();
		if(function(xL)*function(xU) > 0)
		{
			System.out.println("No Root or Even Number of Root.");
			System.exit(0);
		}
		for(int i=0; i<5; i++)
		{
			xR = (xU + xL) / 2;
			if(function(xR) == 0)
			{
				System.out.println("Root at "+(i+1)+" iteration = "+xR);
				break;
			}
			else if(function(xL)*function(xR) > 0)
			{
				System.out.println("Root at "+(i+1)+" iteration = "+xR);
				xL = xR;
			}
			else if(function(xL)*function(xR) < 0)
			{
				System.out.println("Root at "+(i+1)+" iteration = "+xR);
				xU = xR;
			}
		}
	}
	public static double function(double x)
	{
		return Math.cos(x) - Math.sin(3*x);
	}

}
