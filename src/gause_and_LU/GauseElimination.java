package gause_and_LU;

public class GauseElimination {

	public static double[] eliminate(double[][] a,double[] b,int n) {
		double factor=0;
		int i,j,k;
		
		for(k=0; k<n-1; k++)
		{
			for(i=k+1; i<n; i++)
			{
				try
				{
					factor = a[i][k]/a[k][k];
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				for(j=k+1; j<n; j++)
				{
					a[i][j] = a[i][j] - (factor * a[k][j]);
				}
				b[i] = b[i] - (factor * b[k]);
			}
		}
		double[] x = new double[n];
		double sum;
		try
		{
			x[n-1] = b[n-1] / a[n-1][n-1];
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		for(i=n-2; i>=0; i--)
		{
			sum = b[i];
			for(j=i+1; j<n; j++)
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
