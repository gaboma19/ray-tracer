/*******************************************************************
  Camera setup 
*******************************************************************/
import java.util.*;
import javax.media.opengl.*;
import com.sun.opengl.util.GLUT;
import javax.media.opengl.glu.GLU;


public class Camera 
{
  private enum ProjectionType { ORTHOGRAPHIC, PERSPECTIVE }

  private int id;
  private double xRes, yRes;
  public int viewportWidth, viewportHeight;
  private double cop[], look[], up[];
  private double focalLength;
  private double nearClipDistance, farClipDistance;
  private ProjectionType projection;
  
  public Coord cCop, cLook, cUp, n, u, v, centerPosition;
  
  

  public Camera()
  {
    cop  = new double[3];
    look = new double[3];
    up   = new double[3];
    n = new Coord();
    u = new Coord();
    v = new Coord();
    
    cCop = new Coord();
    cLook = new Coord();
    cUp = new Coord();
    centerPosition= new Coord();
  }
 
  public boolean orthographic(){
	  if(projection == ProjectionType.ORTHOGRAPHIC){
		  return true;
	  }
	  else
		  return false;
  }
  
  public double[] getCop(){
	return cop;  
  }
  
  public double[] getLook(){
	  return look;
  }
  
  public double getFocal(){
	  return focalLength;
  }

  public void setResolution( StringTokenizer stok )
  {
    // Reads resolution values from input string. Called from
    // render.cpp:readModelFile().
    
    xRes = Double.parseDouble( stok.nextToken() );
    yRes = Double.parseDouble( stok.nextToken() );

  }

  public void setCamera( int w, int h, GL gl )
  {
    GLU glu = new GLU();
    double fovangle, fovw, fovh;

    // Set the viewport width and height
    gl.glViewport( 0, 0, viewportWidth, viewportHeight );
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();

    if( projection == ProjectionType.PERSPECTIVE ){

      // Compute the width and height of the nearPlane of the viewing volume
      // (Redbook page 126) 
      fovw = xRes * viewportWidth;
      fovh = yRes * viewportHeight;

      // Compute the field of view angle (Redbook page 126)
      fovangle = 2.0 * Math.atan2( fovh /2.0, focalLength ) * 180 / 3.1415928;
      
      // Setup the perspective camera (Redbook page 126)
      glu.gluPerspective(fovangle, fovw / fovh, 
                         nearClipDistance, farClipDistance);

    } else {

      // Compute the width and height of the nearPlane of the viewing volume
      // (Redbook page 127) 
      fovw = 0.5 * xRes * viewportWidth;
      fovh = 0.5 * yRes * viewportHeight;
      
      // Setup the orthographic camera (Redbook page 127-8)
      gl.glOrtho(-fovw,fovw, -fovh, fovh, nearClipDistance, farClipDistance);

    }
 
    gl.glMatrixMode( GL.GL_MODELVIEW );
    gl.glLoadIdentity();
   
    // Position and point the camera in the right direction (Redbook page
    // 119-21)
    glu.gluLookAt( cop[0],  cop[1],  cop[2], 
                  look[0], look[1], look[2], 
                    up[0],   up[1],   up[2]);
    
    
   
    // System.out.println( "Camera.setCamera " );
  }

  public void setViewport( StringTokenizer stok )
  {
    viewportWidth  = Integer.parseInt( stok.nextToken() );
    viewportHeight = Integer.parseInt( stok.nextToken() );
  }

  public void setParameters( StringTokenizer stok )  
  {
    String pt = stok.nextToken();
    if (pt.equals( "perspective" ))
      projection = ProjectionType.PERSPECTIVE;
    else if (pt.equals( "orthographic" ))
      projection = ProjectionType.ORTHOGRAPHIC;

    cop[0] = Double.parseDouble ( stok.nextToken() );
    cop[1] = Double.parseDouble ( stok.nextToken() );
    cop[2] = Double.parseDouble ( stok.nextToken() );

    look[0] = Double.parseDouble ( stok.nextToken() );
    look[1] = Double.parseDouble ( stok.nextToken() );
    look[2] = Double.parseDouble ( stok.nextToken() );
    
    up[0] = Double.parseDouble ( stok.nextToken() );
    up[1] = Double.parseDouble ( stok.nextToken() );
    up[2] = Double.parseDouble ( stok.nextToken() );

    focalLength      = Double.parseDouble ( stok.nextToken() );
    nearClipDistance = Double.parseDouble ( stok.nextToken() );
    farClipDistance  = Double.parseDouble ( stok.nextToken() );
    
    
    cCop.fromArray(cop);
    cLook.fromArray(look);
    cUp.fromArray(up);
    
    n.copy(cLook);
    n.subtract(cCop);
    n.normalize();
    
    
    u=n.crossProduct(cUp);
    u.normalize();
    
    System.out.println(n.x + " " + n.y + " " + n.z);
    
    
    v=u.crossProduct(n);
    v.normalize();
    
    
    System.out.println(u.x + " " + u.y + " " + u.z);
    System.out.println(v.x + " " + v.y + " " + v.z);
    centerPosition.copy(n);
    centerPosition.multiply(focalLength);
    centerPosition.add(cCop);
    
    

  }
  
  public double xRes(){
	  return xRes;
  }
  
  public double yRes(){
	  return yRes;
  }

  public void print()
  {
    System.out.println( "--------- Camera ---------" );
    System.out.print( "ID :" + id );
    System.out.print( " xRes:" + xRes + " yRes:" + yRes );
    System.out.print( " viewport w:" + viewportWidth + " h:" + viewportHeight);
    System.out.print( " cop:" + cop[0] + " " + cop[1] + " " +  cop[2] );
    System.out.print( " look:" + look[0] + " " + look[1] + " " +  look[2] );
    System.out.print( " up:" + up[0] + " " + up[1] + " " +  up[2] );
    System.out.print( " focal Length:" + focalLength );
    System.out.print( " near far Clip distance:" + nearClipDistance 
                                           + " " + farClipDistance);
    System.out.print( " projection type:");
    switch ( projection )
    {
      case ORTHOGRAPHIC : System.out.print( "Orthographic" ); break;
      case PERSPECTIVE  : System.out.print( "Perspective" ); break;
    }
    System.out.println();
  }



}


