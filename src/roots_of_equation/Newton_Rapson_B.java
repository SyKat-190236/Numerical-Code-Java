package roots_of_equation;

import java.util.Scanner;

public class Newton_Rapson_B {
	public static double E_S = 0.02;
	public static void main(String[] args) {
		double xi = 3;
		double i;
		double f;
		double e = 1;
		int j=1;
		System.out.println("Enter initial value: ");
		Scanner input = new Scanner(System.in);
		xi = input.nextDouble();
		while(e > E_S)
		{
			i = xi;
			xi = xi - (function(xi) / fPrime(xi));
			f = xi;
			System.out.println("Root of the equation at "+j+" iteration = "+xi);
			e = error(f,i);
			System.out.println("Error at "+j+" iteration = "+(e*100)+" %");
			j++;
		}
	}
	public static double function(double x)
	{
		return Math.pow(x, 3)-2*Math.pow(x, 2)-6*x+4;
	}
	public static double fPrime(double x)
	{
		return 3*Math.pow(x, 2)-4*x-6;
	}
	public static double error(double f,double i)
	{
		return Math.abs(f-i)/f;
	}
}
