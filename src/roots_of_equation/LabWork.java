package roots_of_equation;

import java.util.Scanner;

//This is the solve of Lab Experiment 1-b
public class LabWork {
	public static final double E_S = 0.02; //(2 % = 2/100 = 0.02)
	public static void main(String[] args) {
		double xL = 0;
		double xU = 0.5;
		double xR;
		double i=0;
		double f;
		int count = 0;
		double e = 1;
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
		while(e > E_S)
		{
			xR = (xL + xU) / 2;
			f = xR;
			count++;
			if(function(xR) == 0)
			{
				System.out.println("Root of the equation at "+count+" iteration = "+xR);
			}
			else if(function(xL)*function(xR) > 0)
			{
				xL =xR;
				System.out.println("Root of the equation at "+count+" iteration = "+xR);
			}
			else if(function(xL)*function(xR) < 0)
			{
				xU = xR;
				System.out.println("Root of the equation at "+count+" iteration = "+xR);
			}
			e = error(i,f);
			System.out.println("Error at "+count+" iteration = "+(error(i,f)*100)+" %");
			i = xR;
		}
		
	}
	public static double function(double x)
	{
		return Math.cos(x) - Math.sin(3*x);
	}
	public static double error(double i, double f)
	{
		return Math.abs(i-f)/f;
	}

}
