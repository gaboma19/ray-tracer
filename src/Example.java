// A Simple example to show the use of of BufferedImage and saving it to a
// file 
//
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Example
{
	Scene s;
	
	public Example(Scene scene){
		s = scene;
	}
	
  public void takeDump()
  {
	RayCaster r = new RayCaster(s);
	//RayTracer r = new RayTracer(s);
    BufferedImage image = new BufferedImage( s.c.viewportWidth, s.c.viewportHeight, 
                                             BufferedImage.TYPE_INT_ARGB );
    //int test = 0;
    
    byte mask = (byte)(0xff);
    int[][][] colors = new int[s.c.viewportHeight][s.c.viewportWidth][3];
    for (int i=0; i < s.c.viewportHeight; ++i ) // row
      for (int j=0; j < s.c.viewportWidth; ++j) // col
      {
        colors[i][j]= r.castRay(j, i);
        colors[i][j][0] &= mask;
        colors[i][j][1] &= mask;
        colors[i][j][2] &= mask;
        // pack the rgb values into an int
        image.setRGB( j, i, ( 0xFF000000  )   |  // no transparency
                            ( colors[i][j][0]   << 16 )   |
                            ( colors[i][j][1]   <<  8 )   |
                              colors[i][j][2]            ); 
        
        //System.out.println(++test);
      }
    



    // Dumping the image to file
    File outputFile = new File( "dump.png" );
    try {
      javax.imageio.ImageIO.write( image, "PNG", outputFile );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
