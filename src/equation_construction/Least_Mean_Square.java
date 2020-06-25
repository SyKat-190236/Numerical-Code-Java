package equation_construction;
import java.util.*;
import gause_and_LU.GauseElimination;
public class Least_Mean_Square {
	static double a1 = 0;
	static double a0 = 0;
	static double syx = 0;
	static double r2 = 0;
	static double st = 0;
	static double sr = 0;
	static double r = 0;
	static double[][] a = new double[3][3];
	static double[] b = new double[3];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your option : ");
		System.out.println("1) For straight line ");
		System.out.println("2) For 2nd order polynomial curve ");
		System.out.println("3) For power equation ");
		int option = sc.nextInt();
		System.out.println("Enter the number of points : ");
		int n = sc.nextInt();
		double[] x = new double[n];
		double[] y = new double[n];
		System.out.println("Enter x points : ");
		for(int i=0; i<n; i++)
		{
			x[i] = sc.nextDouble();
		}
		System.out.println("Enter y points : ");
		for(int i=0; i<n; i++)
		{
			y[i] = sc.nextDouble();
		}
		
		switch(option)
		{
		case 1:
			linear(x,y,n);
			r = Math.sqrt(Math.abs(r2));
			System.out.println("Equation of the fitted curve : ");
			System.out.println("y = "+a0+" + "+a1+"x");
			System.out.println("Sum of squared errors Sr = "+sr);
			System.out.println("Sum of squared errors around the mean St = "+st);
			System.out.println("Coefficient of determination : "+r2);
			System.out.println("Correlation coefficient : "+r);
			break;
		case 2:
			polynomial(x,y,n);
			break;
		case 3:
			powerEquation(x,y,n);
			r = Math.sqrt(Math.abs(r2));
			System.out.println("Equation of the fitted curve : ");
			System.out.println("y = "+Math.pow(10, a0)+" + "+"x^"+a1);
			System.out.println("Sum of squared errors Sr = "+sr);
			System.out.println("Sum of squared errors around the mean St = "+st);
			System.out.println("Coefficient of determination : "+r2);
			System.out.println("Correlation coefficient : "+r);
			break;
		}
	}
	public static void linear(double[] x,double[] y,int n)
	{
		double sumx = 0;
		double sumy = 0;
		double sumxy = 0;
		double sumx2 = 0;
		for(int i=0; i<n; i++)
		{
			sumx = sumx + x[i];
			sumy = sumy + y[i];
			sumxy = sumxy + x[i]*y[i];
			sumx2 = sumx2 + x[i]*x[i];
		}
		double xm = sumx / n;
		double ym = sumy / n;
		a1 = (n * sumxy - sumx*sumy)/(n * sumx2 - sumx * sumx);
		a0 = ym - a1*xm;
		st = 0;
		sr = 0;
		for(int i=0; i<n; i++)
		{
			st = st + Math.pow((y[i] - ym), 2);
			sr = sr + Math.pow((y[i] - a1*x[i] - a0), 2);
		}
		syx = Math.pow((sr/(n-2)), 0.5);
		r2 = (st - sr) / st;
	}
	public static void powerEquation(double[] x,double[] y,int n)
	{
		double[] logx = new double[n];
		double[] logy = new double[n];
		for(int i=0; i<n; i++)
		{
			logx[i] = Math.log10(x[i]);
			logy[i] = Math.log10(y[i]);
		}
		linear(logx,logy,n);
	}
	public static void polynomial(double[] x,double[] y,int n)
	{
		double sumx = 0;
		double sumx2 = 0;
		double sumx3 = 0;
		double sumx4 = 0;
		double sumy = 0;
		double sumxy = 0;
		double sumx2y = 0;
		double ym = 0;
		double[] solve = new double[3];
		for(int i=0; i<n; i++)
		{
			sumx = sumx + x[i];
			sumx2 = sumx2 + Math.pow(x[i],2);
			sumx3 = sumx3 + Math.pow(x[i],3);
			sumx4 = sumx4 + Math.pow(x[i],4);
			sumy = sumy + y[i];
			sumxy = sumxy + (x[i]*y[i]);
			sumx2y = sumx2y + (x[i]*x[i]*y[i]);
		}
		a[0][0] = n;
		a[0][1] = sumx;
		a[0][2] = sumx2;
		a[1][0] = sumx;
		a[1][1] = sumx2;
		a[1][2] = sumx3;
		a[2][0] = sumx2;
		a[2][1] = sumx3;
		a[2][2] = sumx4;
		b[0] = sumy;
		b[1] = sumxy;
		b[2] = sumx2y;
		ym = sumy / n;
		solve = gause_and_LU.GauseElimination.eliminate(a, b, 3);
		for(int i=0; i<n; i++)
		{
			st = st + Math.pow((y[i] - ym),2);
			sr = sr + Math.pow((y[i] - solve[0] - solve[1]*x[i] - solve[2]*Math.pow(x[i],2)), 2);
		}
		r2 = (st - sr) / st;
		r = Math.sqrt(Math.abs(r2));
		
		System.out.println("Equation of the fitted curve : ");
		System.out.println("y = "+solve[0]+" + "+solve[1]+"x + "+solve[2]+"x^2");
		System.out.println("Sum of squared errors Sr = "+sr);
		System.out.println("Sum of squared errors around the mean St = "+st);
		System.out.println("Coefficient of determination : "+r2);
		System.out.println("Correlation coefficient : "+r);
	}
}
