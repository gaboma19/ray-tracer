import java.util.*;
import javax.media.opengl.GL;
import com.sun.opengl.util.GLUT;

public class Ellipsoid extends RenderObject
{
  private double x,y,z;	     // Center of the ellpsoid.
  private double rx,ry,rz;   // Radii of the ellipsoid.
  private GLUT glut;

  public Ellipsoid( StringTokenizer stok, int id_ )
  {
    glut = new GLUT();
    
    id = id_;

    stok.nextToken(); // strip away the token "mat"
    stok.nextToken(); // strip away the token "ID"
    matId = Integer.parseInt( stok.nextToken() );

    x  = Double.parseDouble( stok.nextToken() );
    y  = Double.parseDouble( stok.nextToken() );
    z  = Double.parseDouble( stok.nextToken() );
    rx = Double.parseDouble( stok.nextToken() );
    ry = Double.parseDouble( stok.nextToken() );
    rz = Double.parseDouble( stok.nextToken() );
  }

  public void render( GL gl, MaterialCollection materials )
  {
    materials.setMaterial( matId, gl);
    
    gl.glPushMatrix();
    gl.glTranslated(x,y,z);
    gl.glScaled(rx,ry,rz);
    glut.glutSolidSphere(1,40,40);
    gl.glPopMatrix();
  }

  public void print()
  {
    System.out.print( "Ellipsoid ID:" + id + " mat ID:" + matId );
    System.out.print( " x:" + x + " y:" + y + " z:" + z);
    System.out.print( " rx:" + rx + " ry:" + ry + " rz:" + rz);
    System.out.println();
  }
  
  public Depth getIntersect(double[][] q, double[][] p){
	  return new Depth(-1, -1);
  }

@Override
public double[][] getQ() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Coord getOrigin() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public double getRadius() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public Coord getNormalAt(Coord pt) {
	// TODO Auto-generated method stub
	return null;
}

}



