
public class Matrix {

	public double[][] multiplyMatrix(double[][] p, double[][] q){
		  int m0 = p.length;
		  int n0 = p[0].length;
		  
		  int m1 = q.length;
		  int n1 = q[0].length;
		  
		  
		  double[][] result = new double[m0][n1];
		  for (int i = 0; i < m0; i++){
			  for(int j = 0; j < n1; j++){
				result[i][j]= 0.0;  
			  }
		  }
		  for (int i = 0; i < m0; i++){
			  for(int j = 0; j < n1; j++){
				  for (int k = 0; k < n0; k++){
					  result[i][j] += p[i][k]*q[k][j];
				  }
			  }
		  }
		  
		  return result;
	  }
	
	public double[][] tMatrix(double[][] p){
		double[][] pNes = new double[p[0].length][p.length];
		  for(int i = 0; i < p.length; i++){
			  for(int j = 0; j < p[0].length; j++){
				  pNes[j][i] = p[i][j];
			  }
		  }
		
		return pNes;
	}
	
	public double[][] initMatrix(int n, int m){
		double[][] q = new double[n][m];
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				q[i][j]=0;
			}
		}
		return q;
	}
	
}
