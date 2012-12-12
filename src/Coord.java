//************************************************************************
// Coordinate Class
//
// Comments :
//   Stores the 3D coordinate (x,y,z)
//
// History :
//   30 Jan 2008. Created by Tai-Peng Tian (tiantp@gmail.com)
//   

public class Coord
{
  public double x, y, z;

  public Coord()
  {
    x = y = z = 0.0;
  }

  public Coord( double _x, double _y , double _z)
  {
    x = _x; 
    y = _y;
    z = _z;
  }
  public Coord crossProduct(Coord other){
	  Coord cross = new Coord();
	  cross.x = (this.y * other.z) - (this.z * other.y);
	  cross.y = (this.z * other.x) - (this.x * other.z);
	  cross.z = (this.x * other.y) - (this.y * other.x);
	  return cross;
  }
  
  public double angleBetween(Coord other){
	  if(Math.toDegrees(Math.acos(bounded(this.dotProduct(other) / this.norm() * other.norm()))) > 180.0){
		  return (360 - Math.toDegrees(Math.acos(bounded(this.dotProduct(other) / this.norm() * other.norm())))) * -1;
	  }
	  else{
		  return Math.toDegrees(Math.acos(bounded(this.dotProduct(other) / this.norm() * other.norm())));
	  }
  }
  
  public double norm(){
	  return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
  }
  
  public double dotProduct(Coord other){
	  return (this.x * other.x) + (this.y * other.y) + (this.z * other.z);
  }
  
  public double distanceBetween(Coord other){
	  return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) + Math.pow(this.z - other.z, 2));
  }
  
  public void normalize(){
	  double norm = this.norm();
	  this.x /= norm;
	  this.y /= norm;
	  this.z /= norm;
  }
  
  public void fromArray(double[] a){
	  this.x = a[0];
	  this.y = a[1];
	  this.z = a[2];
  }
  
  public void fromArray(double[][] a){
	  this.x = a[0][0];
	  this.y = a[1][0];
	  this.z = a[2][0];
  }
  
  public double[][] toArray(){
	  double[][] fucks = new double[4][1];
	  fucks[0][0] = this.x;
	  fucks[1][0] = this.y;
	  fucks[2][0] = this.z;
	  fucks[3][0] = 1;
	  return fucks;
  }
  
  public double[][] toArray3D(){
	  double[][] fucks = new double[3][1];
	  fucks[0][0] = this.x;
	  fucks[1][0] = this.y;
	  fucks[2][0] = this.z;
	  return fucks;
  }
  
  public void subtract(Coord b){
	  this.x -= b.x;
	  this.y -= b.y;
	  this.z -= b.z;
  }
  
  public Coord subtract(Coord a, Coord b){
	  Coord res = new Coord();
	  res.x = a.x - b.x;
	  res.y = a.y - b.y;
	  res.z = a.z - b.z;
	  return res;
  }
  
  public Coord multiply(Coord a, Coord b){
	  Coord res = new Coord();
	  res.x = a.x * b.x;
	  res.y = a.y * b.y;
	  res.z = a.z * b.z;
	  return res;
  }
  public Coord multiply(Coord a, Double b){
	  Coord res = new Coord();
	  res.x = a.x * b;
	  res.y = a.y * b;
	  res.z = a.z * b;
	  return res;
  }
  
  public void add(Coord b){
	  this.x += b.x;
	  this.y += b.y;
	  this.z += b.z;
  }
  
  public Coord add(Coord a, Coord b){
	  Coord res = new Coord();
	  res.x = a.x + b.x;
	  res.y = a.y + b.y;
	  res.z = a.z + b.z;
	  return res;
  }
  
  
   public void multiply(double b){
	  this.x*= b;
	  this.y *= b;
	  this.z *= b;
  }
  
  public void copy(Coord b){
	  this.x = b.x;
	  this.y = b.y;
	  this.z = b.z;
  }
  
  public double bounded (double d){
	  return Math.max(-1, Math.min(d, 1));
  }
  
 
  
  
}
