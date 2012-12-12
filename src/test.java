
public class test {
	
	
	double array0[][] = {{2,1},{3,4},{5, 6}};
	double array1[][] = {{1,3,6},{2,4,5}};
	double result[][];
	double result2[][];
	
	
	public static void main (String args[]){
		test t = new test();
		t.go();
		
		
	}
	
	public void go(){
		
		result = multiplyMatrix(array0, array1);
		result2 = tMatrix(result);
		for(int i = 0; i < result[0].length; i++) {
			  for(int j = 0; j < result.length; j++) {
			  System.out.print(" "+result[i][j]);
			  } 
			  System.out.println();
		}
		System.out.println();
		for(int i = 0; i < result2[0].length; i++) {
			  for(int j = 0; j < result2.length; j++) {
			  System.out.print(" "+result2[i][j]);
			  } 
			  System.out.println();
		}
		
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
}
