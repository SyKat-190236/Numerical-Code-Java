package gause_and_LU;

import java.util.*;
import java.io.*;

public class Error {
public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);
        
        double [][]mat = new double[3][3];
        double[] x = new double[3];
        double[] b = new double[3];
        double[] f = new double[3];
        System.out.println("Enter the co-efficient matrix : ");
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                mat[i][j] = sc.nextDouble();
        System.out.println("Enter the vector of the equation : ");
		for(int i=0; i<3; i++)
		{
			b[i] = sc.nextDouble();
		}
		
		double [][]l = new double[3][3];
        l[0][0] = l[1][1] = l[2][2] = 1;
        l[0][1] = l[0][2] = l[1][2] = 0;

        double [][]u = new double[3][3];
        u[1][0] = u[2][0] = u[2][1] = 0;

        u[0][0] = mat[0][0];
        u[0][1] = mat[0][1];
        u[0][2] = mat[0][2];

        l[1][0] = mat[1][0]/mat[0][0];
        u[1][1] = mat[1][1] - (l[1][0]*u[0][1]);   //mat[2][2]-(mat[2][1]*mat[1][2]/mat[1][1]);
        u[1][2] = mat[1][2] - (l[1][0]*u[0][2]);

        l[2][0] = mat[2][0]/u[0][0];
        l[2][1] = (mat[2][1] - l[2][1]*u[0][1])/u[1][1];
        u[2][2] = mat[2][2] - (l[2][0]*u[0][2]) - (l[2][1]*u[1][2]);

        System.out.println("The L Component is:");
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
                System.out.print(" "+l[i][j]);
            System.out.println();
        }
        System.out.println("The U Component is:");
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
                System.out.print(" "+u[i][j]);
            System.out.println();
        }
        
        //Solving Equation part
        double[] d = new double[3];
        d = solve(l,b);
        
        x = solve(u,d);
        System.out.println("Solution of the equation : ");
        for(int i=0; i<3; i++)
        {
        	System.out.println("x"+(i+1)+" = "+x[i]);
        }
        // Inverse part
        double[] d1 = new double[3];
        double[] d2 = new double[3];
        double[] d3 = new double[3];
        double[][] inverse = new double[3][3];
        double[] temp = new double[3];
        temp[0] = 1;
        temp[1] = 0;
        temp[2] = 0;
        d1 = solve(l,temp);
        System.out.println("Sub-problem 1 : ");
        for(int i=0; i<3; i++)
        {
        	System.out.println("d"+(i+1)+" = "+d1[i]);
        }
        x = solve(u,d1);
        for(int i=0; i<3; i++)
        {
        	inverse[i][0] = x[i];
        }
        temp[0] = 0;
        temp[1] = 1;
        temp[2] = 0;
        d2 = solve(l,temp);
        System.out.println("Sub-problem 2 : ");
        for(int i=0; i<3; i++)
        {
        	System.out.println("d"+(i+1)+" = "+d2[i]);
        }
        x = solve(u,d2);
        for(int i=0; i<3; i++)
        {
        	inverse[i][1] = x[i];
        }
        temp[0] = 0;
        temp[1] = 0;
        temp[2] = 1;
        d3 = solve(l,temp);
        System.out.println("Sub-problem 3 : ");
        for(int i=0; i<3; i++)
        {
        	System.out.println("d"+(i+1)+" = "+d3[i]);
        }
        x = solve(u,d3);
        for(int i=0; i<3; i++)
        {
        	inverse[i][2] = x[i];
        }
        System.out.println("Inverse matrix is : ");
        for(int i=0; i<3; i++)
        {
        	for(int j=0; j<3; j++)
        	{
        		System.out.print("\t"+inverse[i][j]);
        	}
        	System.out.println();
        }
        sc.close();
	}
	public static double[] solve(double[][] a, double[] b)
	{
		int i,j,k;
		double factor=0;
		for(k=0; k<3-1; k++)
		{
			for(i=k+1; i<3; i++)
			{
				try
				{
					factor = a[i][k]/a[k][k];
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				for(j=k+1; j<3; j++)
				{
					a[i][j] = a[i][j] - (factor * a[k][j]);
				}
				b[i] = b[i] - (factor * b[k]);
			}
		}
		double[] x = new double[3];
		double sum;
		try
		{
			x[3-1] = b[3-1] / a[3-1][3-1];
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		for(i=3-2; i>=0; i--)
		{
			sum = b[i];
			for(j=i+1; j<3; j++)
			{
				sum = sum - (a[i][j] * x[j]);
			}
			try
			{
				x[i] = sum / a[i][i];
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		return x;
	}

}
